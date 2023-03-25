export function getShoppingCartItems(user) {
  if (user === null) {
    // do something?
  }

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
