import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './App.css';
import HeaderBar from './components/HeaderBar';
import ProductPage from './components/ProductPage';
import HomePage from './components/HomePage';
import ShoppingCart from './components/ShoppingCart/ShoppingCart';


function App() {
  return (
    <div className="wrapper">
      <BrowserRouter>
        <HeaderBar />
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route exact path="/cart" element={<ShoppingCart />} />
          <Route path="/products/:productId" element={<ProductPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
