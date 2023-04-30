const addressEndpoint = "http://localhost:8080/user/address"; // TODO: Check if we can get a proper REST API.

export function getAddresses() {
  return fetch(addressEndpoint).then((data) => data.json());
}

export function addAddress(address) {
  if (
    !address.receiver ||
    !address.street ||
    !address.streetNumber ||
    !address.postalCode ||
    !address.city
  ) {
    console.log("One or more address values were not set.");
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
