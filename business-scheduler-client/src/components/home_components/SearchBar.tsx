import React, { useState } from 'react';

function SearchBar({ onSearch } : {onSearch:Function}) {
  const [query, setQuery] = useState('');

  const handleSearch = () => {
    onSearch(query);
  };

  return (
    <div className="search-bar">
      <input
        type="text"
        placeholder="Search..."
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        className='rounded border border-maroon-200 m-4 p-2'
      />
      <button className="m-2 bg-maroon-200 hover:bg-maroon-100 text-gray-200 hover:text-black rounded p-2" onClick={handleSearch}>Search</button>
    </div>
  );
}

export default SearchBar;