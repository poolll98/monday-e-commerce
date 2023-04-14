import React from "react";
import { useState } from 'react';
import productData from "../mockdata/products";
import { Link } from "react-router-dom";

function SearchBar({ query, onChange }) {
  return (
    <label>
      Search:{' '}
      <input
        value={query}
        onChange={onChange}
      />
    </label>
  );
}

function List({ items }) {
  return ( 
    <table>
      <tbody> 
        {items.map(productData => (
          <div key={productData.id}>
            <h3>
            <Link to={`/products/${productData.id}`}>{productData.name}</Link>
            </h3>

            <p>{productData.description}</p>

            <img src={productData.img} alt={productData.description}/>
            <hr />
          </div>
        ))}
      </tbody>
    </table> 
  );
}

function filterItems(items, query) {
  query = query.toLowerCase();
  return items.filter(item =>
    item.name.split(' ').some(word =>
      word.toLowerCase().startsWith(query)
    )
  );
}

export default function FilterableList() {
  const [query, setQuery] = useState('');
  const results = filterItems(productData, query);

  function handleChange(e) {
    setQuery(e.target.value);
  }

  return (
    <>
      <SearchBar
        query={query}
        onChange={handleChange}
      />
      <hr />
      <List items={results} />
    </>
  );
}

