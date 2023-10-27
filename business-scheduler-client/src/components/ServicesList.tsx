import { useContext, useState, useEffect } from "react";
import { Link } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";

function ServicesList({ services } : {services:Array<any>}, { amount } : {amount:number}) {  
    const [serviceList, setServices] = useState([{ serviceId: 0, serviceName: "", cost: 0, totalTimeLength: 0 }]);

    const auth = useContext(AuthContext);
    
    useEffect(() => {
        if (services && amount) {
            const slicedServices = services.slice(0, amount - 1);
            if (slicedServices) {
                setServices(slicedServices);
            } else {
               console.log(slicedServices)
            }
        }
    }, [services, amount]);

    return (
        <div className="flex flex-col">
            {services.map((service:any) => (
                <Link key={service.serviceId} className="border border-gray-500 bg-gray-300 hover:bg-gray-600 hover:text-white w-1/10 rounded p-3 m-3" to={"/appointment/add/" + service.serviceId}>
                    {service.serviceName}: ${service.cost}
                    <br />
                    Time length: {service.totalTimeLength} minutes
                </Link>
            ))}
        </div>
    )
}
  
export default ServicesList;