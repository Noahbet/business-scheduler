import { useState, useContext, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";
import ValidationSummary from "./ValidationSummary";
import { saveAppointment } from "../services/appointmentAPI";
import { findById, saveService } from "../services/serviceAPI";
import { findByBusinessId } from "../services/appointmentAPI";
import AppointmentCard from "./profile_components/AppointmentCard";

const INITIAL_SERVICE = {
    serviceId: 0,
    businessId: 0,
    serviceName: "",
    totalTimeLength: 0,
    cost: 0
};

function ServiceForm() {
  const [service, setService] = useState(INITIAL_SERVICE);

  const auth = useContext( AuthContext );
  const [errors, setErrors] = useState([]);

  const navigate = useNavigate();
  const { businessId } = useParams();

  const handleChange = (evt:any) => {
    const nextService: any = { ...service };
    nextService[evt.target.name] = evt.target.value;
    nextService.businessId = businessId;
    console.log(nextService);
    setService(nextService);
  };


  const handleSubmit = (evt: any) => {
    evt.preventDefault();
    saveService(service).then((data) => {
      if (data?.errors) {
        setErrors(data.errors);
      } else {
        navigate("/", {
          state: { message: `Service saved!` },
        });
      }
    });
  };

  return (
    <div className="min-w-screen min-h-screen bg-gray-400">
      <ValidationSummary errors={errors} />
      <form onSubmit={handleSubmit}>
        <div className="flex flex-col items-center">
          <h2 className="text-xl font-bold p-5">
            New Appointment
          </h2>

          <label htmlFor="serviceName">Service Name: </label>
              <input
                className="border border-gray-600 rounded m-2 p-1 w-3/4"
                type="text"
                name="serviceName"
                id="serviceName"
                value={service.serviceName}
                onChange={handleChange}
                />
                <label htmlFor="totalTimeLength">Service Time Length(In Minutes): </label>
              <input
                className="border border-gray-600 rounded m-2 p-1 w-3/4"
                type="number"
                name="totalTimeLength"
                id="totalTimeLength"
                value={service.totalTimeLength}
                onChange={handleChange}
                />
                <label htmlFor="cost">Service Cost(In Dollars): </label>
              <input
                className="border border-gray-600 rounded m-2 p-1 w-3/4"
                type="number"
                name="cost"
                id="cost"
                value={service.cost}
                onChange={handleChange}
                />

          <div className="flex flex-wrap">
            <button type="submit" className="border border-gray-600 bg-maroon-200 text-white hover:bg-maroon-100 p-2 m-3 rounded">
              Save changes
            </button>
            <Link to="/" className="border border-gray-600 bg-maroon-200 text-white hover:bg-maroon-100 p-2 m-3 rounded">
              Cancel
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
}

export default ServiceForm;
