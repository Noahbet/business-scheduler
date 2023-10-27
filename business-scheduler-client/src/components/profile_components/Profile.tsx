import { useState, useEffect, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import { findByUserId } from "../../services/appointmentAPI";
import { findById } from "../../services/businessAPI";
import AuthContext from "../../contexts/AuthContext";
import AppointmentCard from "./AppointmentCard";

interface appointment {
    appointmentId: number,
    customerId: number,
    customerUse: string,
    businessId: number,
    serviceId: number,
    appointmentDateTime: Date
}

async function getAppointmentsByUserId(userId: number) {
    let i = 1;
    while(true){
        let business = await findById(i)
        if (business.ownerId == userId) {
            return business.appointments;
        } else {continue;}
    }
}

function Profile() {
    const [appointments, setAppointments] = useState<Array<appointment>>([]);
    const [businessAppointments, setBusinessAppointments] = useState<Array<appointment>>([]);


    const auth = useContext( AuthContext );
    const navigate = useNavigate();
    
    useEffect(() => {
        getAppointmentsByUserId(auth?.user.userId)
          .then((data: Array<any>) => setBusinessAppointments(data))
          .catch((err) =>
            navigate("/error", {
              state: { message: err },
            })
          );
      }, [auth?.hasAuthority("OWNER")]);

    useEffect(() => {
        findByUserId(auth?.user.userId)
          .then((data: Array<any>) => setAppointments(data))
          .catch((err) =>
            navigate("/error", {
              state: { message: err },
            })
          );
      }, [navigate, auth?.user.userId]);

    return (
        <div className="w-screen h-screen bg-gray-400 flex flex-col">
            <div className="flex flex-col w-screen m-2">
                {!auth?.hasAuthority("OWNER") && (
                    <div className="w-screen flex flex-col items-center p-1">
                        <Link to="/business/add" className="bg-gray-500 border border-gray-600 rounded p-2 m-2">
                            Add Business
                        </Link>
                    </div>
                )}
            </div>
            <h2 className="font-bold text-bold m-7 mx-20">Appointments by you</h2>
            <div className="flex flex-wrap items-center">
                {appointments == null || appointments.length === 0 ? (
                    <p className="m-5 font-bold">
                        No appointments here
                    </p>
                ) : (
                    appointments.map((appointment: any) => <AppointmentCard key={appointment.appointmentId} appointment={appointment} />)
                )}
            </div>
            {auth?.hasAuthority("OWNER") && (
                <>
                    <h2 className="font-bold text-bold m-7 mx-20">Appointments for you</h2>
                    <div className="flex flex-wrap items-center">
                        {businessAppointments == null || businessAppointments.length === 0 ? (
                            <p className="m-5 font-bold">
                                No appointments here
                            </p>
                        ) : (
                            businessAppointments.map((appointment: any) => <AppointmentCard key={appointment.appointmentId} appointment={appointment} />)
                        )}
                    </div>
                </>
             )}
        </div>
    );
}

export default Profile;
