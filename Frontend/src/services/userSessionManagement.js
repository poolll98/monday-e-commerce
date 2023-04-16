const userEndpointUrl = "http://localhost:8080/api/auth/"; // TODO: Load from config file/environment.

export function signup(userData) {
  fetch(userEndpointUrl + "signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userData),
  }).then((data) => {
    if (data.ok) {
      login(userData.username, userData.password);
    } else {
      alert("Signup not successful.");
    }
  });
}

export function login(username, password) {
  fetch(userEndpointUrl + "signin", {
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
    .then((data) => {
      setToken(data.accessToken);
      setUserData(data);
    })
    .catch((reason) => {
      alert("Unable to log in.");
      console.log(reason);
    });
}

export function logout() {
  localStorage.removeItem("bearerToken");
}

function setToken(token) {
  localStorage.setItem("bearerToken", token);
}

export function getToken() {
  return localStorage.getItem("bearerToken");
}

function setUserData(userData) {
  localStorage.setItem("userData", userData);
}

export function getUserData() {
  let data = localStorage.getItem("userData");
  return !data
    ? {} // TODO: Load data from backend.
    : { userId: data.id, username: data.username, email: data.email };
}
