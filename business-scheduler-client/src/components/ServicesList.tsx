import { useContext } from "react";

import { Link } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";

interface Service {
    serviceId: string,
    serviceName: string,
    cost: number,
}

function ServicesList({services} : {services:Array<Service>}) {  

    const { hasAuthority } = useContext(AuthContext);
  
    if (services.length > 3) {
        services = services.slice(0, 3);
    }

    return (
        <>
            {
                services.map((service:Service) => (
                    <Link className="border border-gray-500 bg-gray-300 hover:bg-gray-600 hover:text-white w-1/10 rounded p-3 m-3" to={"/service/add/" + service.serviceId}>
                        {service.serviceName}: ${service.cost}
                    </Link>
                ))
            }
        </>
    )
}
  
export default ServicesList;