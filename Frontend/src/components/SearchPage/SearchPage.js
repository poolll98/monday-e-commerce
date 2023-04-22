import React from "react";
import { useState } from "react";
import { searchItems } from "../../services/search";
import SearchEntry from "./SearchEntry";
import SearchResult from "./SearchResult";

export default function SearchPage() {
  const [query, setQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);

  function handleChange(e) {
    setQuery(e.target.value);
    let trimmed = e.target.value.trim();
    // TODO: Don't search on every keystroke.
    if (trimmed) {
      searchItems(trimmed).then((data) => {
        setSearchResults(data);
        console.log(data);
      });
    }
  }

  return (
    <>
      <SearchEntry query={query} onChange={handleChange} />
      <hr />
      {searchResults.map((productData) => (
        <SearchResult productData={productData} />
      ))}
    </>
  );
}
