import searchProducts from "../mockdata/searchProducts";

const searchEndpointUrl = "http://localhost:3333/search/name/";

export function searchItems(searchTerm) {
  searchTerm = searchTerm.toLowerCase();

  if (searchTerm.trim().length === 0) {
    return searchProducts;
  }
  return searchProducts.filter((product) =>
    product.name.toLowerCase().includes(searchTerm)
  );

  return fetch(searchEndpointUrl + searchTerm).then((data) => data.json());
}
