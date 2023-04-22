import React from "react";
import { useState } from "react";
import { searchItems } from "../../services/search";
import SearchEntry from "./SearchEntry";
import SearchResult from "./SearchResult";

export default function SearchPage() {
  const [query, setQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);

  function loadAndShowResults(name) {
    setQuery(name);
    let trimmed = name.trim();
    if (trimmed) {
      searchItems(trimmed).then((data) => {
        setSearchResults(data);
      });
    }
  }

  const params = new Proxy(new URLSearchParams(window.location.search), {
    get: (searchParams, prop) => searchParams.get(prop),
  });

  if (params.name && !query) {
    loadAndShowResults(params.name);
  }

  function handleChange(e) {
    loadAndShowResults(e.target.value);
  }

  return (
    <>
      <SearchEntry query={query} onChange={handleChange} />
      <hr />
      <div style={{ display: "flex", flexDirection: "row", flexWrap: "wrap" }}>
        {searchResults.map((productData) => (
          <SearchResult productData={productData} key={productData.id} />
        ))}
      </div>
    </>
  );
}
