export function getShoppingCartItems(user) {
  if (user === null) {
    // do something?
  }

  return fetch("http://localhost:3333/cart").then((data) => data.json());

  return new Promise((resolve) => {
    setTimeout(() => {
      resolve([
        {
          id: 123,
          name: "Item Name",
          amount: 2,
        },
      ]);
    }, 2000);
  });
}
