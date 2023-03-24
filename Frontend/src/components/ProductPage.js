import React from 'react';
import { useParams } from 'react-router-dom';

export default function ProductPage() {
  const { productId } = useParams();

  return <h2>Product Page for product with ID {productId}</h2>;
}