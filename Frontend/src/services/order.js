// TODO: Check if user needs to be passed or can be extracted from context.
// TODO: Connection error handling.

const orderEndpointUrl = "http://localhost:8080/order";

export function confirmOrder(shippingAddress) {
  let body = JSON.stringify({ shippingAddress: shippingAddress }); // TODO: Transform data as needed.
  console.log(body);

  return fetch(orderEndpointUrl + "/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: body,
  }).then((response) => {
    if (!response.ok) {
      alert(`Error when creating order.`);
      console.log(`Could not confirm order: ${body}.`);
      console.log(response);
    } else {
      alert("success");
    }
  });
}
