// TODO: Check if user needs to be passed or can be extracted from context.

export function getShoppingCartItems(user) {
  if (!isValidUser(user)) {
    // do something?
  }

  return fetch("http://localhost:3333/cart").then((data) => data.json());
}

export function addItemToCart(itemData, user) {
  if (!isValidUser(user)) {
    // do something?
  }

  let body = JSON.stringify({ itemData }); // TODO: Transform data as needed.

  return fetch("http://localhost:3333/list", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: body,
  }).then((data) => {
    if (!data.ok) {
      alert(`Error when adding item ${itemData?.name} to cart.`);
      console.log(`Could not store item ${body} in cart.`);
    }
  });
}

function isValidUser(user) {
  return true; // return false if no (valid) user
}
