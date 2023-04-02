const userEndpointUrl = "http://localhost:3333/login";

export function signup(userData) {
  return;
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

export function login(username, password) {
  localStorage.setItem("bearerToken", "token");
  return;
  fetch(userEndpointUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username: username, password: password }),
  })
    .then((data) => {
      if (!data.ok) {
        throw Error("Login failed");
      }
    })
    .then((data) => data.json())
    .then((data) => localStorage.setItem("bearerToken", data.bearerToken))
    .catch((reason) => {
      alert("Unable to log in.");
      console.log(reason);
    });
}

export function logout() {
  localStorage.removeItem("bearerToken");
}

export function getToken() {
  return localStorage.getItem("bearerToken");
}
