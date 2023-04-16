const searchEndpointUrl = "http://localhost:8080/search/name/";

export function searchItems(searchTerm) {
  return fetch(searchEndpointUrl + searchTerm).then((data) => data.json());
}
