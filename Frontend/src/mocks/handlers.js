// src/mocks/handlers.js
import { rest } from "msw";

import products from "./products";
import searchProducts from "./searchProducts";
import cart from "./cart";
import address from "./address";
import payment from "./payment";

let requestUrl = "http://localhost:8080";

export const handlers = [
  rest.post(requestUrl + "/api/auth/signup", async (req, res, ctx) => {
    let userData = await req.json();
    let userId = Math.floor(Math.random() * 1000000);

    sessionStorage.setItem(
      "userData",
      JSON.stringify({
        ...userData,
        id: userId,
      })
    );

    return res(
      ctx.json({
        message: "User successfully registered with basic permissions!",
      })
    );
  }),

  rest.post(requestUrl + "/api/auth/signin", async (req, res, ctx) => {
    let userData = sessionStorage.getItem("userData");
    let request = await req.json();

    if (!userData) {
      return res(ctx.status(404));
    }

    userData = JSON.parse(userData);

    if (
      userData.username === request.username &&
      userData.password === request.password
    ) {
      return res(
        // Respond with a 200 status code
        ctx.status(200),
        ctx.json({
          id: userData.id,
          username: userData.username,
          email: userData.email,
          accessToken: "token",
        })
      );
    }
    return res(ctx.status(404)); // TODO: Check status.
  }),

  // example for replacement of json-server
  rest.get(requestUrl + "/products", (req, res, ctx) => {
    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json(products)
    );
  }),

  // example for replacement of json-server
  rest.get(requestUrl + "/shopcart", (req, res, ctx) => {
    // TODO: Maybe more complex checking?
    if (!sessionStorage.getItem("userData")) {
      return res(ctx.status(401));
    }

    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json(cart)
    );
  }),

  rest.get(requestUrl + "/search/name/:searchTerm", (req, res, ctx) => {
    const { searchTerm } = req.params;

    let matches = searchProducts.filter((product) => {
      return product.name.toLowerCase().includes(searchTerm.toLowerCase());
    });

    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json(matches)
    );
  }),

  rest.get(requestUrl + "/user/address", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(address));
  }),

  rest.post(requestUrl + "/user/address/add", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(1));
  }),

  rest.get(requestUrl + "/user/payment", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(payment));
  }),

  rest.post(requestUrl + "/user/payment/add", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(1));
  }),

  rest.post(requestUrl + "/order/add", (req, res, ctx) => {
    return res(ctx.status(200));
  }),
];
