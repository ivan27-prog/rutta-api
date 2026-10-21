//todo Importamos las funciones que hacen las peticiones a la API
import { createProduct, getAllProducts } from "../../services/product.service.js";
import { createDescription } from "../../services/description.service.js";

//todo Seleccionamos el formulario, la tabla y la alerta
const formProduct = document.querySelector("#formProducto");
const lista = document.querySelector("#listaProductos");
const alerta = document.querySelector("#alertaProducto");
const btnSubmit = document.querySelector("#btnSubmit");

//todo Muestra una alerta de Bootstrap (tipo: "success", "danger", "warning")
const mostrarAlerta = (mensaje, tipo) => {
  alerta.className = `alert alert-${tipo}`;
  alerta.textContent = mensaje;
};

//todo Pinta en la tabla los productos que llegan de la API
const pintarProductos = async () => {
  const productos = await getAllProducts();

  // Si el service devolvió undefined, hubo un error en la petición
  if (!productos) {
    mostrarAlerta("No se pudieron cargar los productos desde la API.", "danger");
    return;
  }

  lista.innerHTML = "";
  productos.forEach((product) => {
    lista.insertAdjacentHTML(
      "beforeend",
      `
            <tr>
              <td>${product.id}</td>
              <td>${product.productName}</td>
              <td>$${Number(product.price).toFixed(2)}</td>
              <td>${product.level ?? "—"}</td>
              <td>${product.activity ?? "—"}</td>
              <td>${product.description?.descriptionText ?? "—"}</td>
            </tr>
     `,
    );
  });
};

//todo Agregamos un evento al botón de envío del formulario
formProduct.addEventListener("submit", async (e) => {
  //todo previenimos el comportamiento por defecto del formulario
  e.preventDefault();

  //todo convertimos los datos del formulario en un objeto (los "name" ya son los del DTO)
  const product = Object.fromEntries([...new FormData(formProduct)]);
  console.log(product);

  //TODO Validamos que todos los campos del formulario estén completos
  if (
    product.productName.trim() === "" ||
    product.price === "" ||
    product.level === "" ||
    product.activity === "" ||
    product.descriptionText.trim() === ""
  ) {
    mostrarAlerta("Por favor, complete todos los campos del formulario.", "warning");
    return;
  } else if (!isNaN(product.productName)) {
    mostrarAlerta("El nombre del producto no puede ser un número.", "warning");
    return;
  } else if (Number(product.price) <= 0) {
    mostrarAlerta("El precio debe ser mayor a 0.", "warning");
    return;
  } else if (product.descriptionText.trim().length < 2) {
    mostrarAlerta("La descripción debe tener entre 2 y 200 caracteres.", "warning");
    return;
  }

  //todo Armamos el objeto con los nombres EXACTOS de ProductRequest
  const productRequest = {
    productName: product.productName.trim(),
    price: Number(product.price), // BigDecimal en el back -> número en JSON
    activity: product.activity,
    level: product.level,
  };

  btnSubmit.disabled = true;

  //todo 1) Creamos el producto
  const productoCreado = await createProduct(productRequest);

  if (!productoCreado) {
    mostrarAlerta("Error al guardar el producto. Revisa la consola.", "danger");
    btnSubmit.disabled = false;
    return;
  }

  //todo 2) Creamos su descripción con el id que devolvió el back (DescriptionRequest)
  const descripcionCreada = await createDescription({
    descriptionText: product.descriptionText.trim(),
    productId: productoCreado.id,
  });

  if (descripcionCreada) {
    mostrarAlerta(`Producto "${productoCreado.productName}" guardado con id ${productoCreado.id}.`, "success");
  } else {
    mostrarAlerta(`El producto se guardó con id ${productoCreado.id}, pero falló la descripción.`, "warning");
  }

  formProduct.reset();
  btnSubmit.disabled = false;

  //todo Volvemos a pedir la lista para ver el producto nuevo
  await pintarProductos();
});

//todo Al abrir la página cargamos los productos desde la API
pintarProductos();
