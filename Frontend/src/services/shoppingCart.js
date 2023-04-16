// TODO: Check if user needs to be passed or can be extracted from context.
// TODO: Connection error handling.

const cartEndpointUrl = "http://localhost:8080/cart";

export function getShoppingCartItems(user) {
  if (!isValidUser(user)) {
    // do something?
  }

  return fetch(cartEndpointUrl).then((data) => data.json());
}

export function addItemToCart(itemData, user) {
  if (!isValidUser(user)) {
    // do something?
  }

  let body = JSON.stringify(itemData); // TODO: Transform data as needed.
  console.log(body);

  return fetch(cartEndpointUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: body,
  }).then((response) => {
    if (!response.ok) {
      alert(`Error when adding item to cart.`);
      console.log(`Could not store item ${body} in cart.`);
      console.log(response);
    } else {
      alert("success");
    }
    // Add item to header shopping cart indicator?
  });
}

function isValidUser(user) {
  return true; // return false if no (valid) user
}
