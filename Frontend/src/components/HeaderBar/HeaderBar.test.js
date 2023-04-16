import { fireEvent, render, screen } from "@testing-library/react";
import HeaderBar from "./HeaderBar";
import App from "../../App";
const { Router } = require("react-router-dom");

const { createMemoryHistory } = require("history");

test("renders the log in button in the header bar when not logged in", () => {
  render(<App />);
  expect(screen.getByRole("button", { name: "Log In" })).toBeInTheDocument();
});

// test("renders the log out button in the header bar when logged in", () => {
//   localStorage.setItem("bearerToken", "test");
//   localStorage.setItem("userData", JSON.stringify({ username: "testuser" }));
//   render(<App />);
//   expect(screen.getByRole("button", { name: "Log Out" })).toBeInTheDocument();
// });

test("clicking the MarketMate link returns to the homepage", () => {
  const history = createMemoryHistory();
  render(
    <Router location={history.location} navigator={history}>
      <HeaderBar />
    </Router>
  );
  fireEvent.click(screen.getByAltText("Logo - Links to Homepage"));
  expect(history.location.pathname).toBe("/");
});

// test("clicking the cart link routes to the shopping cart", () => {
//   const history = createMemoryHistory();
//   render(
//     <Router location={history.location} navigator={history}>
//       <HeaderBar />
//     </Router>
//   );
//   fireEvent.click(screen.getByText("Shopping Cart"));
//   expect(history.location.pathname).toBe("/cart");
// });
