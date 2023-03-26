import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './App.css';
import HeaderBar from './components/HeaderBar';
import ProductPage from './components/ProductPage/ListProducts';
import ProductDetail from './components/ProductPage/ProductPage';
import HomePage from './components/HomePage';
import ShoppingCart from './components/ShoppingCart/ShoppingCart';
import { UserProvider } from './components/UserContext';

import "./App.css";
import HeaderBar from "./components/HeaderBar";
import ProductPage from "./components/ProductPage";
import HomePage from "./components/HomePage";
import ShoppingCart from "./components/ShoppingCart/ShoppingCart";
import { UserProvider } from "./components/UserContext";

function App() {
  return (
    <div className="wrapper App">
      <BrowserRouter>
        <UserProvider>
          <HeaderBar />
          <Routes>
            <Route exact path="/" element={<HomePage />} />
            <Route exact path="/cart" element={<ShoppingCart />} />
            <Route exact path="/products" element={<ProductPage />} />
            <Route path="/products/:productId" element={<ProductDetail />} />
          </Routes>
        </UserProvider>
      </BrowserRouter>
    </div>
  );
}

export default App;
