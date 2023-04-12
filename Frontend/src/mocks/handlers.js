// src/mocks/handlers.js
import { rest } from "msw";

import testuser from "./testuser";
import products from "./products";
import cart from "./cart";

let requestUrl = "http://localhost:8080";

export const handlers = [
  rest.post(requestUrl + "/login", (req, res, ctx) => {
    // Persist user's authentication in the session
    sessionStorage.setItem("is-authenticated", "true");

    return res(
      // Respond with a 200 status code
      ctx.status(200),
      ctx.json(testuser)
    );
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

  /* rest.get(requestUrl + "/user", (req, res, ctx) => {
    // Check if the user is authenticated in this session
    const isAuthenticated = sessionStorage.getItem("is-authenticated");

    if (!isAuthenticated) {
      // If not authenticated, respond with a 403 error
      return res(
        ctx.status(403),
        ctx.json({
          errorMessage: "Not authorized",
        })
      );
    }

    // If authenticated, return a mocked user details
    return res(
      ctx.status(200),
      ctx.json({
        username: "admin",
      })
    );
  }), */
];
