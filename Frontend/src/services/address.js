const addressEndpoint = "http://localhost:8080/user/address"; // TODO: Check if we can get a proper REST API.

export function getAddresses() {
  return fetch(addressEndpoint).then((data) => data.json());
}

export function addAddress(receiver, street, streetNumber, postalCode, city) {
  let address = {
    receiver: receiver,
    street: street,
    street_nr: streetNumber,
    postal_code: postalCode,
    city: city,
  };

  if (
    !address.receiver ||
    !address.street ||
    !address.street_nr ||
    !address.postal_code ||
    !address.city
  ) {
    console.log("One or more address values were not set.");
    console.log(address);
    return;
  }

  let body = JSON.stringify({
    ...address,
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
