const addressAddEndpoint = "http://localhost:8080/user/address/add"; // TODO: Check if we can get a proper REST API.

export function addAddress(receiver, street, streetNumber, cityCode, city) {
  if (!receiver || !street || !streetNumber || cityCode || !city) {
    console.log("One or more address values were not set.");
    return;
  }

  let body = JSON.stringify({
    receiver: receiver,
    street: street,
    street_nr: streetNumber,
    city: city,
    cityCode: cityCode,
    country: "Switzerland",
  });

  return fetch(addressAddEndpoint, {
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
