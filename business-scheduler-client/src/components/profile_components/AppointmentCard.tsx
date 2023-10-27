import { useContext, useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { findById } from "../../services/businessAPI";

import AuthContext from "../../contexts/AuthContext";

function AppointmentCard({ appointment } : {appointment:any}) {
  const [business, setBusiness] = useState({ businessName: "", ownerId: 0, services: [{serviceId: 0, serviceName: "", businessId: 0, totalTimeLength: 0, cost: 0 }] });
  const [service, setService] = useState({serviceId: 0, serviceName: "", businessId: 0, totalTimeLength: 0, cost: 0 });

  const auth = useContext( AuthContext );
  const navigate = useNavigate();

    useEffect(() => {
        findById(appointment.businessId)
        .then((data: any) => setBusiness(data))
        .catch((err) =>
            navigate("/error", {
            state: { message: err },
            })
        );
    }, [navigate, appointment.businessId]);

    useEffect(() => {
      if (business && appointment.serviceId) {
          const matchingService = business.services.find((serv: any) => serv.serviceId === appointment.serviceId);
          if (matchingService) {
              setService(matchingService);
          }
      }
  }, [business, appointment.serviceId, navigate]);

  return (
    <div className="w-screen h-200% bg-gray-400">
      <div className="w-3/4 border border-maroon-200 bg-gray-300 flex flex-column items-center justify-between mx-auto p-5 m-4 rounded">
        <div className="flex flex-wrap items-center justify-between">
            <p className="text-maroon-200 font-bold text-l m-5">
              {auth?.user.userId === business.ownerId ?
              appointment.customerUser : business.businessName}
            </p>
            <p className="text-maroon-200 font-bold text-l m-5">
              {service.serviceName}
            </p>
            <p className="text-maroon-200 m-5">
              {appointment.appointmentDateTime}
            </p>
        </div> 
        <div className="flex flex-col items-center justify-between">
            <Link className="bg-maroon-200 text-white text-xl font-bold p-3 rounded" to={`/appointment/delete/${appointment.appointmentId}`}>
              Delete
            </Link>
        </div> 
      </div>
    </div>

  );
}

export default AppointmentCard;
