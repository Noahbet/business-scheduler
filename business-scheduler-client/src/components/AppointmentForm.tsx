import { useState, useContext, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";
import ValidationSummary from "./ValidationSummary";
import { saveAppointment } from "../services/appointmentAPI";
import { findById } from "../services/serviceAPI";
import { findByBusinessId } from "../services/appointmentAPI";
import AppointmentCard from "./profile_components/AppointmentCard";

const INITIAL_APPOINTMENT = {
  appointmentId: 0,
  customerId: 0,
  customerUser: "",
  businessId: 0,
  serviceId: 0,
  appointmentDateTime: "2023-10-12T16:35:00"
};

function AppointmentForm() {
  const [appointment, setAppointment] = useState(INITIAL_APPOINTMENT);
  const [service, setService] = useState({ serviceId: 0, businessId: 0,  serviceName: "", cost: 0, totalTimeLength: 0 });
  const [appointments, setAppointments] = useState<Array<any>>([]);

  const auth = useContext( AuthContext );
  const [errors, setErrors] = useState([]);

  const navigate = useNavigate();
  const { serviceId } = useParams();

  useEffect(() => {
    findById(Number(serviceId))
      .then((data: any) => setService(data))
      .catch((err) =>
        navigate("/error", {
          state: { message: err },
        })
      );
  }, [serviceId, navigate]);

  useEffect(() => {
    if (service.businessId) {
      findByBusinessId(service.businessId)
        .then((data) => setAppointments(data))
        .catch((err) =>
         console.log(err)
        );
    }
  }, [service.businessId, navigate]);

  const handleChange = (evt:any) => {
    const nextAppointment: any = { ...appointment };
    nextAppointment[evt.target.name] = evt.target.value;
    nextAppointment.customerId = auth?.user.userId;
    nextAppointment.serviceId = service.serviceId;
    nextAppointment.businessId = service.businessId;
    console.log(nextAppointment);
    setAppointment(nextAppointment);
  };


  const handleSubmit = (evt: any) => {
    evt.preventDefault();
    saveAppointment(appointment).then((data) => {
      if (data?.errors) {
        setErrors(data.errors);
      } else {
        navigate("/", {
          state: { message: `Appointment saved!` },
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

          <label htmlFor="appointmentDateTime">Service: {service.serviceName} <br/> Service cost: ${service.cost} <br/>
          Service time length: {service.totalTimeLength} <br/> Appointment Date and Time:</label>
              <input
                className="border border-gray-600 rounded m-2 p-1 w-3/4"
                type="datetime-local"
                name="appointmentDateTime"
                id="appointmentDateTime"
                value={appointment.appointmentDateTime}
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
          <div className="flex flex-wrap items-center">
                {appointments == null || appointments.length === 0 ? (
                    <p className="m-5 font-bold">
                        No appointments here
                    </p>
                ) : (
                    appointments.map((appointment: any) => <AppointmentCard key={appointment.appointmentId} appointment={appointment} />)
                )}
          </div>
        </div>
      </form>
    </div>
  );
}

export default AppointmentForm;
