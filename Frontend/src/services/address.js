const addressAddEndpoint = "http://localhost:8080/user/address/add"; // TODO: Check if we can get a proper REST API.

export function addAddress(street, streetNumber, cityCode, city, country) {
  let body = JSON.stringify({
    city: city,
    country: country,
    street: street,
    street_nr: streetNumber,
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
