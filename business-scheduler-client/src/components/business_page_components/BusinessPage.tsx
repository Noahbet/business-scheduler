import { useContext, useEffect, useState } from "react";
import { useNavigate, Link, useParams } from "react-router-dom";
import { findById } from "../../services/businessAPI";

import AuthContext from "../../contexts/AuthContext";
import BusinessPicture from "../BusinessPicture";
import ServicesList from "../ServicesList";
import BusinessHours from "./BusinessHours";

function BusinessPage() {
    const [business, setBusiness] = useState({businessId: 0, ownerId: 0, businessName: "", availability: {mondayStart: '00:00:00', mondayEnd: '00:00:00', mondayBreakStart: '00:00:00', mondayBreakEnd: '00:00:00', 
    tuesdayStart: '00:00:00', tuesdayEnd: '00:00:00', tuesdayBreakStart: '00:00:00', tuesdayBreakEnd: '00:00:00', 
    wednesdayStart: '00:00:00', wednesdayEnd: '00:00:00', wednesdayBreakStart: '00:00:00', wednesdayBreakEnd: '00:00:00', 
    thursdayStart: '00:00:00', thursdayEnd: '00:00:00', thursdayBreakStart: '00:00:00', thursdayBreakEnd: '00:00:00', 
    fridayStart: '00:00:00', fridayEnd: '00:00:00', fridayBreakStart: '00:00:00', fridayBreakEnd: '00:00:00', 
    saturdayStart: '00:00:00', saturdayEnd: '00:00:00', saturdayBreakStart: '00:00:00', saturdayBreakEnd: '00:00:00', 
    sundayStart: '00:00:00', sundayEnd: '00:00:00', sundayBreakStart: '00:00:00', sundayBreakEnd: '00:00:00',
  }, services:[{serviceId: 0, serviceName: "", cost: 0, totalTimeLength: 0}] });
    const auth = useContext( AuthContext );
    const navigate = useNavigate();
    const { businessId } = useParams();

  useEffect(() => {
    if (businessId) {
      findById(Number(businessId))
        .then((data) => setBusiness(data))
        .catch((err) =>
          navigate("/error", {
            state: { message: err },
          })
        );
    }
  }, [businessId, navigate]);

  return (
    <div className="w-4/5 border border-maroon-200 bg-gray-300 flex flex-col items-center justify-between mx-auto m-4 rounded">
       <BusinessPicture businessId={business.businessId}></BusinessPicture>
       <div className="flex flex-col items-center justify-between">
          <p className="text-maroon-200 font-bold text-l m-2">
            {business.businessName}
          </p>
          <BusinessHours availability={business.availability}></BusinessHours>
          <p className="text-maroon-200 font-bold text-l m-1">
            Service Name
          </p>
          <p className="text-maroon-200 m-1">
          </p>
      </div> 
      <ServicesList services={business.services}></ServicesList>
      {auth?.hasAuthority("OWNER") && auth?.user.userId == business.ownerId ?
        <>
            <Link to={'/service/add/' + businessId} className="border border-gray-500 bg-maroon-200 hover:bg-maroon-100 p-2 m-2 rounded text-white">Add Service</Link>
        </>
        :
        <></>
      }
    </div>
  );
}

export default BusinessPage;
