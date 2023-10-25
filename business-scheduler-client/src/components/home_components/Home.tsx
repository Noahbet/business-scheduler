import { useState, useEffect, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import { findByQuery } from "../../services/businessAPI";
import BusinessCard from "./BusinessCard";
import AuthContext from "../../contexts/AuthContext";

function Home() {
  const [businesses, setBusinesses] = useState([]);
  const navigate = useNavigate();

  const { user } = useContext(AuthContext);

  const business = {
    businessId: 1,
    businessName: "Noah's One Stop Shop",
    category: "Professional",
    services: [{serviceId: 1, serviceName: "cut hair", cost: 30}, {serviceId: 2, serviceName: "cook food", cost: 20}, {serviceId: 3, serviceName: "other", cost: 10}],
    rating: 4.8
  }

  return (
    <div className="w-screen h-screen bg-gray-400">
        <ul className="w-screen flex flex-wrap items-center justify-between mx-auto p-5">
            <li className="w-1/3 flex justify-center items-center">
                <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl">
                    Health
                </button>
            </li>
            <li className="w-1/3 flex justify-center items-center">
            <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl">
                    Hair
                </button>
            </li>
            <li className="w-1/3 flex justify-center items-center">
            <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl">
                    Tattoo
                </button>
            </li>
        </ul>
        <ul className="w-screen flex flex-wrap items-center justify-between mx-auto p-5">
        <li className="w-1/3 flex justify-center items-center">
        <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl">
                    Pet
                </button>
            </li>
            <li className="w-1/3 flex justify-center items-center">
            <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl">
                    Cleaning
                </button>
            </li>
            <li className="w-1/3 flex justify-center items-center">
            <button className="whitespace-nowrap bg-maroon-200 hover:bg-maroon-100 text-gray-200 font-bold py-20 w-5/6 rounded text-xl">
                    Professional
                </button>
            </li>
        </ul>
        <BusinessCard key={business.businessId} business={business}></BusinessCard>
        <div>

        </div>
    </div>
  );
}

export default Home;
