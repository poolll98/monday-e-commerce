// src/mocks/handlers.js
import { rest } from "msw";

import testuser from "./testuser";
import products from "./products";
import cart from "./cart";

let requestUrl = "http://localhost:8080";

export const handlers = [
  rest.post(requestUrl + "/api/auth/signup", (req, res, ctx) => {
    let userId = (Math.random() * 1000000).floor();

    sessionStorage.setItem("userData", {
      ...req.json(),
      id: userId,
    });

    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json({
        message: "User successfully registered with basic permissions!",
        id: userId,
      })
    );
  }),

  rest.post(requestUrl + "/api/auth/signin", (req, res, ctx) => {
    let userData = sessionStorage.getItem("userData");
    let request = req.json();

    if (
      userData.username == request.username &&
      userData.password == request.password
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
  rest.get("http://localhost:3333/products", (req, res, ctx) => {
    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json(products)
    );
  }),

  // example for replacement of json-server
  rest.get("http://localhost:3333/cart", (req, res, ctx) => {
    // Check if user is logged in.
    if (!sessionStorage.getItem("is-authenticated")) {
      return res(ctx.status(401));
    }

    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json(cart)
    );
  }),

  // example for replacement of json-server
  rest.get("http://localhost:3333/search/name/:searchTerm", (req, res, ctx) => {
    const { searchTerm } = req.params;

    let matches = products.filter((products) =>
      products.name.toLowerCase().includes(searchTerm)
    );

    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json(matches)
    );
  }),
];
