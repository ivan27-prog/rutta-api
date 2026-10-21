// Importamos las funciones del CRUD desde los services
import {
  createProduct,
  getAllProducts,
  updateProductById,
  deleteProductById,
} from "../../services/product.service.js";
import {
  createDescription,
  updateDescriptionById,
} from "../../services/description.service.js";

// Elementos del DOM
const formProduct = document.querySelector("#formProducto");
const lista = document.querySelector("#listaProductos");
const alerta = document.querySelector("#alertaProducto");
const btnSubmit = document.querySelector("#btnSubmit");
const btnCancelarEdicion = document.querySelector("#btnCancelarEdicion");
const tituloFormulario = document.querySelector("#tituloFormulario");

// Guardamos en memoria la lista actual y el producto que se está editando (null si es nuevo)
let productosCache = [];
let productoEnEdicion = null;

// Muestra una alerta de Bootstrap (tipo: "success", "danger", "warning")
const mostrarAlerta = (mensaje, tipo) => {
  alerta.className = `alert alert-${tipo}`;
  alerta.textContent = mensaje;
};

// Sale del modo edición y limpia el formulario
const salirModoEdicion = () => {
  productoEnEdicion = null;
  formProduct.reset();
  tituloFormulario.textContent = "Añadir Nuevo Producto";
  btnSubmit.innerHTML = `<i class="bi bi-check2-circle me-1"></i> Guardar producto`;
  btnCancelarEdicion.classList.add("d-none");
};

btnCancelarEdicion.addEventListener("click", salirModoEdicion);

// Pinta en la tabla los productos que llegan de la API
const pintarProductos = async () => {
  const productos = await getAllProducts();

  if (!productos) {
    mostrarAlerta("No se pudieron cargar los productos desde la API.", "danger");
    return;
  }

  productosCache = productos;
  lista.innerHTML = "";

  if (productos.length === 0) {
    lista.innerHTML = `
      <tr>
        <td colspan="7" class="text-center text-muted py-4">
          No hay productos registrados todavía.
        </td>
      </tr>
    `;
    return;
  }

  productos.forEach((product) => {
    lista.insertAdjacentHTML(
      "beforeend",
      `
        <tr>
          <td class="fw-bold">#${product.id}</td>
          <td class="fw-semibold">${product.productName}</td>
          <td>$${Number(product.price).toFixed(2)}</td>
          <td><span class="badge-nivel">${product.level ?? "—"}</span></td>
          <td>${product.activity ?? "—"}</td>
          <td>${product.description?.descriptionText ?? "—"}</td>
          <td class="text-center text-nowrap">
            <button
              type="button"
              class="btn btn-sm btn-accion-editar me-1"
              data-accion="editar"
              data-id="${product.id}"
              title="Editar producto"
            >
              <i class="bi bi-pencil-square"></i>
            </button>
            <button
              type="button"
              class="btn btn-sm btn-accion-eliminar"
              data-accion="eliminar"
              data-id="${product.id}"
              title="Eliminar producto"
            >
              <i class="bi bi-trash3"></i>
            </button>
          </td>
        </tr>
      `,
    );
  });
};

// Eventos de los botones Editar (lapicito) y Eliminar (bote de basura) en la tabla
lista.addEventListener("click", async (e) => {
  const boton = e.target.closest("button[data-accion]");
  if (!boton) return;

  const id = Number(boton.dataset.id);
  const accion = boton.dataset.accion;
  const producto = productosCache.find((p) => p.id === id);
  if (!producto) return;

  // --- ACCIÓN: EDITAR (carga los datos en el formulario para hacer PUT) ---
  if (accion === "editar") {
    productoEnEdicion = producto;

    formProduct.elements.productName.value = producto.productName ?? "";
    formProduct.elements.price.value = producto.price ?? "";
    formProduct.elements.level.value = producto.level ?? "";
    formProduct.elements.activity.value = producto.activity ?? "";
    formProduct.elements.descriptionText.value =
      producto.description?.descriptionText ?? "";

    tituloFormulario.textContent = `Editando Producto #${producto.id}`;
    btnSubmit.innerHTML = `<i class="bi bi-pencil-square me-1"></i> Actualizar producto`;
    btnCancelarEdicion.classList.remove("d-none");

    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  // --- ACCIÓN: ELIMINAR (hace DELETE /api/product/{id}) ---
  if (accion === "eliminar") {
    const confirmar = confirm(
      `¿Seguro que deseas eliminar "${producto.productName}" (ID #${producto.id})?`,
    );
    if (!confirmar) return;

    await deleteProductById(id);

    // Si estaba en edición ese mismo producto, limpiamos el form
    if (productoEnEdicion?.id === id) {
      salirModoEdicion();
    }

    mostrarAlerta(`Producto "${producto.productName}" eliminado.`, "success");
    await pintarProductos();
  }
});

// Envío del formulario: hace POST si es nuevo o PUT si se está editando
formProduct.addEventListener("submit", async (e) => {
  e.preventDefault();

  const product = Object.fromEntries([...new FormData(formProduct)]);

  // Validaciones
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

  // Objeto con los nombres exactos de ProductRequest
  const productRequest = {
    productName: product.productName.trim(),
    price: Number(product.price),
    activity: product.activity,
    level: product.level,
  };

  btnSubmit.disabled = true;

  // --- MODO EDICIÓN (PUT) ---
  if (productoEnEdicion) {
    const id = productoEnEdicion.id;
    const productoActualizado = await updateProductById(id, productRequest);

    if (!productoActualizado) {
      mostrarAlerta("Error al actualizar el producto.", "danger");
      btnSubmit.disabled = false;
      return;
    }

    // Si el producto ya tenía descripción la actualizamos con PUT; si no tenía, la creamos con POST
    if (productoEnEdicion.description?.id) {
      await updateDescriptionById(productoEnEdicion.description.id, {
        descriptionText: product.descriptionText.trim(),
        productId: id,
      });
    } else {
      await createDescription({
        descriptionText: product.descriptionText.trim(),
        productId: id,
      });
    }

    mostrarAlerta(
      `Producto "${productoActualizado.productName}" (#${id}) actualizado correctamente.`,
      "success",
    );
    salirModoEdicion();
    btnSubmit.disabled = false;
    await pintarProductos();
    return;
  }

  // --- MODO CREACIÓN (POST) ---
  const productoCreado = await createProduct(productRequest);

  if (!productoCreado) {
    mostrarAlerta("Error al guardar el producto. Revisa la consola.", "danger");
    btnSubmit.disabled = false;
    return;
  }

  const descripcionCreada = await createDescription({
    descriptionText: product.descriptionText.trim(),
    productId: productoCreado.id,
  });

  if (descripcionCreada) {
    mostrarAlerta(
      `Producto "${productoCreado.productName}" guardado con id ${productoCreado.id}.`,
      "success",
    );
  } else {
    mostrarAlerta(
      `El producto se guardó con id ${productoCreado.id}, pero falló la descripción.`,
      "warning",
    );
  }

  formProduct.reset();
  btnSubmit.disabled = false;
  await pintarProductos();
});

// Al abrir la página cargamos los productos desde la API
pintarProductos();
