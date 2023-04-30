const paymentEndpointUrl = "http://localhost:8080/user/payment";

export function getPaymentMethods(userToken = "") {
  return fetch(paymentEndpointUrl).then((data) => data.json());
}

export function addPaymentMethod(paymentMethod, userToken = "") {
  let body = JSON.stringify(paymentMethod);
  console.log(body);

  return fetch(paymentEndpointUrl + "/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: body,
  }).then((response) => {
    if (!response.ok) {
      alert(`Error when adding payment.`);
      console.log(`Could not add payment: ${body}.`);
      console.log(response);
    }
  });
}
