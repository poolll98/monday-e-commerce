import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './App.css';
import HeaderBar from './Components/HeaderBar';
import ProductPage from './Components/ProductPage';
import HomePage from './Components/HomePage';


function App() {
  return (
    <div className="wrapper">
      <BrowserRouter>
        <HeaderBar />
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route path="/product" element={<ProductPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
