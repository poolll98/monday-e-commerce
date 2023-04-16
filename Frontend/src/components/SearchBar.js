import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import { searchItems } from "../services/search";

function SearchBar({ query, onChange }) {
  return (
    <label>
      Search: <input value={query} onChange={onChange} />
    </label>
  );
}

function List({ items }) {
  // TODO: Handle missing images.
  return (
    <table>
      <tbody>
        {items.map((productData) => (
          <div key={productData.id}>
            <h3>
              <Link to={`/products/${productData.id}`}>{productData.name}</Link>
            </h3>
            <p>{productData.description}</p>
            <img src={productData.media[0]} alt={productData.description} />
            <hr />
          </div>
        ))}
      </tbody>
    </table>
  );
}

export default function FilterableList() {
  const [query, setQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);

  function handleChange(e) {
    setQuery(e.target.value);
    let trimmed = e.target.value.trim();
    if (trimmed) {
      searchItems(trimmed).then((data) => {
        setSearchResults(data);
        console.log(data);
      });
    }
  }

  return (
    <>
      <SearchBar query={query} onChange={handleChange} />
      <hr />
      {searchResults ? <List items={searchResults} /> : null}
    </>
  );
}
