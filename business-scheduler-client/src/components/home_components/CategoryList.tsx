
function CategoryList({ onSearch } : {onSearch:Function}) {

  const handleSearch = (selection: string) => {
    onSearch(selection);
  };

  return (
    <div className="search-bar">
      <ul className="w-screen flex flex-wrap items-center justify-between mx-auto p-5">
                <li className="w-1/3 flex justify-center items-center">
                    <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl" onClick={() => { handleSearch("HEALTH"); }}>
                        Health
                    </button>
                </li>
                <li className="w-1/3 flex justify-center items-center">
                <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl" onClick={() => { handleSearch("HAIR"); }}>
                        Hair
                    </button>
                </li>
                <li className="w-1/3 flex justify-center items-center">
                <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl" onClick={() => { handleSearch("TATTOO"); }}>
                        Tattoo
                    </button>
                </li>
            </ul>
            <ul className="w-screen flex flex-wrap items-center justify-between mx-auto p-5">
            <li className="w-1/3 flex justify-center items-center">
            <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl" onClick={() => { handleSearch("PET"); }}>
                        Pet
                    </button>
                </li>
                <li className="w-1/3 flex justify-center items-center">
                <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl" onClick={() => { handleSearch("CLEANING"); }}>
                        Cleaning
                    </button>
                </li>
                <li className="w-1/3 flex justify-center items-center">
                <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl" onClick={() => { handleSearch("FOOD"); }}>
                        Food
                    </button>
                </li>
            </ul>
    </div>
  );
}

export default CategoryList;