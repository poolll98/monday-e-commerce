import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function SearchBar() {
  const [query, setQuery] = useState("");
  const navigate = useNavigate();

  function handleChange(e) {
    setQuery(e.target.value.trim());
  }

  function handleKeyPress(event) {
    if (event.key === "Enter") {
      navigate(`/search?name=${query}`, { replace: true });
    }
  }

  return (
    <label>
      Search:{" "}
      <input value={query} onChange={handleChange} onKeyDown={handleKeyPress} />
    </label>
  );
}
