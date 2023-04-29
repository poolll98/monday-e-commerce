import { render, screen } from "@testing-library/react";
import App from "./App";

test("renders the log in button in the header bar when not logged in", () => {
  render(<App />);
  expect(
    screen.getByRole("button", { name: "Not Existing" })
  ).toBeInTheDocument();
});
