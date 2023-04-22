import React from "react";

export default function SearchEntry({ query, onChange }) {
  return (
    <label>
      Search: <input value={query} onChange={onChange} />
    </label>
  );
}
