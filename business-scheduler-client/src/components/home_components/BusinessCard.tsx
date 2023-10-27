import { useContext } from "react";
import { Link } from "react-router-dom";

import AuthContext from "../../contexts/AuthContext";
import Rating from "./Rating";
import BusinessPicture from "../BusinessPicture"; 
import ServicesList from "../ServicesList";

function BusinessCard({ business } : {business:any}) {

  const auth = useContext( AuthContext );
  const businessLink = "/business/" + business.businessId;

  return (
    <div className="w-screen border border-maroon-200 bg-gray-300 flex flex-wrap items-center justify-between mx-auto p-10 w-5/6 m-2 rounded">
       <div className="flex flex-wrap items-center justify-left">
        <div className="w-1/3 flex flex-col">
          <Link className="text-maroon-200 font-bold text-xl hover:text-maroon-100" to={businessLink}>
            {business.businessName}
          </Link>
          <p className="text-maroon-200">
            {business.category}
          </p>
          <Rating rating={business.rating}></Rating>
        </div>
        <div className="flex flex-wrap items-center">
          <ServicesList services={business.services}></ServicesList>
        </div>
      </div>
      <div className="justify-right">
          <BusinessPicture businessId={business.businessId}></BusinessPicture>
        </div>
    </div>
  );
}

export default BusinessCard;
