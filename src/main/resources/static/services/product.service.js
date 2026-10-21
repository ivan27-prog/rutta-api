// Ruta ProductController: @RequestMapping("/api/product")
const BASE_URL = "http://localhost:8080/api/product";

// POST /api/product -> recibe ProductRequest { productName, price, activity, level }
export const createProduct = async function (productObject) {
  try {
    const response = await fetch(`${BASE_URL}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(productObject),
    });
    if (!response.ok)
      throw Error(`Error al crear el producto: ${response.status}`);
    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error("Fallo en la peticion: ", error.message);
  }
};

// GET /api/product -> devuelve la lista de productos
export const getAllProducts = async function () {
  try {
    const response = await fetch(`${BASE_URL}`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok)
      throw Error(`Error al obtener los productos: ${response.status}`);
    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error("Fallo en la peticion: ", error.message);
  }
};
