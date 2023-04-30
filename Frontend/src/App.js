import { BrowserRouter, Routes, Route } from "react-router-dom";

import "./App.css";
import HeaderBar from "./components/HeaderBar/HeaderBar";
import ProductPage from "./components/ProductPage/ProductPage";
import HomePage from "./components/HomePage";
import SearchPage from "./components/SearchPage/SearchPage";
import ShoppingCart from "./components/ShoppingCart/ShoppingCart";
import { UserProvider } from "./components/UserContext";
import SignupLoginPage from "./components/SignupLogin/SignupLoginPage";

function App() {
  return (
    <div className="wrapper App">
      <BrowserRouter>
        <UserProvider>
          <HeaderBar />
          <Routes>
            <Route exact path="/" element={<HomePage />} />
            <Route
              exact
              path="/signup"
              element={<SignupLoginPage initiallyLogin={false} />}
            />
            <Route exact path="/login" element={<SignupLoginPage />} />
            <Route exact path="/cart" element={<ShoppingCart />} />
            <Route exact path="/search" element={<SearchPage />} />
            <Route exact path="/products" element={<ProductPage />} />
            <Route path="/products/:productId" element={<ProductPage />} />
          </Routes>
        </UserProvider>
      </BrowserRouter>
    </div>
  );
}

export default App;
