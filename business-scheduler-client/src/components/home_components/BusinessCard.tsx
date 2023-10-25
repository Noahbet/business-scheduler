import { useContext } from "react";
import { Link } from "react-router-dom";

import AuthContext from "../../contexts/AuthContext";
import Rating from "./Rating";
import BusinessPicture from "./BusinessPicture"; 
import ServicesList from "../ServicesList";

function BusinessCard({business} : {business:any}) {

  const { user, hasAuthority } = useContext(AuthContext);

  return (
    <div className="w-screen border border-maroon-200 bg-gray-300 flex flex-wrap items-center justify-between mx-auto p-10 w-5/6 rounded">
       <div className="flex flex-wrap items-center justify-between">
        <div className="w-1/3 p-0">
          <p className="text-maroon-200 font-bold text-xl">
            {business.businessName}
          </p>
          <p className="text-maroon-200">
            {business.category}
          </p>
          <Rating rating={business.rating}></Rating>
        </div>
        <BusinessPicture businessId={business.businessId}></BusinessPicture>
      </div> 
      <div className="flex flex-wrap items-center justify-between">
        <ServicesList services={business.services}></ServicesList>
      </div>
    </div>
  );
}

export default BusinessCard;
