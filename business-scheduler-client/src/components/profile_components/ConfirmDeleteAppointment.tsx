import { Link, useNavigate, useParams } from "react-router-dom";
import { deleteAppointment } from "../../services/appointmentAPI";

function ConfirmDeleteAppointment() {
  const navigate = useNavigate();
  const { appointmentId } = useParams();

  const handleSubmit = (evt: any) => {
    evt.preventDefault();
    deleteAppointment(Number(appointmentId)).then(() => {
      navigate("/profile", {
        state: { message: `Appointment: ${appointmentId} deleted!` },
      });
    });
  };

  return (
    <form onSubmit={handleSubmit} className="w-screen h-screen bg-gray-300">
      <div className="p-5">
        <h1 className="text-xl font-bold">Are you sure you want to delete appointment this appointment? This cannot be undone.
        </h1>
        <div className="flex flex-column items-center">
          <Link to="/profile" className="m-2 border border-maroon-200 p-1 rounded">
            Cancel
          </Link>
          <button type="submit" className="m-2 border border-maroon-200 p-1 rounded">
            Delete
          </button>
        </div>
      </div>
    </form>
  );
}

export default ConfirmDeleteAppointment;
