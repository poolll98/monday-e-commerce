const userEndpointUrl = "http://localhost:3333/login";

export function signup(userData) {
  fetch(userEndpointUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userData),
  })
    .then((data) => data.json())
    .then((data) => localStorage.setItem("bearerToken", data.bearerToken));
}

export function login(credentials) {
  fetch(userEndpointUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(credentials),
  })
    .then((data) => data.json())
    .then((data) => localStorage.setItem("bearerToken", data.bearerToken));
}
