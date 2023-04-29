const addressEndpoint = "http://localhost:8080/user/address"; // TODO: Check if we can get a proper REST API.

export function getAddress() {
  return fetch(addressEndpoint).then((data) => data.json());
}

export function addAddress(receiver, street, streetNumber, postalCode, city) {
  if (!receiver || !street || !streetNumber || !postalCode || !city) {
    console.log("One or more address values were not set.");
    return;
  }

  let body = JSON.stringify({
    receiver: receiver,
    street: street,
    street_nr: streetNumber,
    city: city,
    postalCode: postalCode,
    country: "Switzerland",
  });

  return fetch(addressEndpoint + "/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: body,
  }).then((response) => {
    if (!response.ok) {
      alert(`Error when adding address.`);
      console.log(`Could not add address ${body} to user.`);
      console.log(response);
    }
  });
}
