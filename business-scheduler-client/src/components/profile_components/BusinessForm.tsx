import { useState, useEffect, useContext } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import { findById, saveBusiness } from "../../services/businessAPI";

import AuthContext from "../../contexts/AuthContext";
import ValidationSummary from "../ValidationSummary";

const INITIAL_BUSINESS = {
  businessId: 0,
  businessName: "",
  availability: {mondayStart: '00:00:00', mondayEnd: '00:00:00', mondayBreakStart: '00:00:00', mondayBreakEnd: '00:00:00', 
    tuesdayStart: '00:00:00', tuesdayEnd: '00:00:00', tuesdayBreakStart: '00:00:00', tuesdayBreakEnd: '00:00:00', 
    wednesdayStart: '00:00:00', wednesdayEnd: '00:00:00', wednesdayBreakStart: '00:00:00', wednesdayBreakEnd: '00:00:00', 
    thursdayStart: '00:00:00', thursdayEnd: '00:00:00', thursdayBreakStart: '00:00:00', thursdayBreakEnd: '00:00:00', 
    fridayStart: '00:00:00', fridayEnd: '00:00:00', fridayBreakStart: '00:00:00', fridayBreakEnd: '00:00:00', 
    saturdayStart: '00:00:00', saturdayEnd: '00:00:00', saturdayBreakStart: '00:00:00', saturdayBreakEnd: '00:00:00', 
    sundayStart: '00:00:00', sundayEnd: '00:00:00', sundayBreakStart: '00:00:00', sundayBreakEnd: '00:00:00',
  },
  ownerId: 0,
  category: " ",
  services: [{ serviceId: 0, serviceName: "", cost: 0, totalTimeLength: 0, businessId: 0 }],
  appointments: [{}],
};

function BusinessForm() {
  const [business, setBusiness] = useState(INITIAL_BUSINESS);
  const [availability, setAvailability] = useState(INITIAL_BUSINESS.availability);
  const auth = useContext( AuthContext );
  const [errors, setErrors] = useState([]);

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

  const handleChange = (evt:any) => {
    const nextBusiness: any = { ...business };
    nextBusiness[evt.target.name] = evt.target.value;
    nextBusiness.ownerId = auth?.user.userId;
    nextBusiness.availability = availability;
    console.log(nextBusiness);
    setBusiness(nextBusiness);
  };

  const handleChangeAvail = (evt:any) => {
    const nextAvailability: any = { ...availability };
    nextAvailability[evt.target.name] = evt.target.value;
    business.availability = nextAvailability;
    console.log(nextAvailability);
    setAvailability(nextAvailability);
  };

  const handleSubmit = (evt: any) => {
    evt.preventDefault();
    saveBusiness(business).then((data) => {
      if (data?.errors) {
        setErrors(data.errors);
      } else {
        navigate("/", {
          state: { message: `${business.businessName} saved!` },
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
            {businessId ? `Edit ${business.businessName}` : "New Business"}
          </h2>

          <div className="flex flex-wrap justify-center items-center">
            <div className="flex flex-col w-1/3 m-5">
              <label htmlFor="businessName">Business Name:</label>
              <input
                className="border border-gray-600 rounded m-2 p-1"
                type="text"
                name="businessName"
                id="businessName"
                value={business.businessName}
                onChange={handleChange}
              />
                <label htmlFor="mondayStart">Monday Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  type="time"
                  name="mondayStart"
                  id="mondayStart"
                  value={business.availability.mondayStart}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="mondayBreakStart">Monday Break Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  type="time"
                  name="mondayBreakStart"
                  id="mondayBreakStart"
                  value={business.availability.mondayBreakStart}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="tuesdayStart">Tuesday Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="tuesdayStart"
                  id="tuesdayStart"
                  value={business.availability.tuesdayStart}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="tuesdayBreakStart">Tuesday Break Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="tuesdayBreakStart"
                  id="tuesdayBreakStart"
                  value={business.availability.tuesdayBreakStart}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="wednesdayStart">Wednesday Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="wednesdayStart"
                  id="wednesdayStart"
                  value={business.availability.wednesdayStart}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="wednesdayBreakStart">Wednesday Break Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="wednesdayBreakStart"
                  id="wednesdayBreakStart"
                  value={business.availability.wednesdayBreakStart}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="thursdayStart">Thursday Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="thursdayStart"
                  id="thursdayStart"
                  value={business.availability.thursdayStart}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="thursdayBreakStart">Thursday Break Start Time</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="thursdayBreakStart"
                  id="thursdayBreakStart"
                  value={business.availability.thursdayBreakStart}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="fridayStart">Friday Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="fridayStart"
                  id="fridayStart"
                  value={business.availability.fridayStart}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="fridayBreakStart">Friday Break Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="fridayBreakStart"
                  id="fridayBreakStart"
                  value={business.availability.fridayBreakStart}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="saturdayStart">Saturday Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="saturdayStart"
                  id="saturdayStart"
                  value={business.availability.saturdayStart}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="saturdayBreakStart">Saturday Break Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="saturdayBreakStart"
                  id="saturdayBreakStart"
                  value={business.availability.saturdayBreakStart}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="sundayStart">Sunday Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="sundayStart"
                  id="sundayStart"
                  value={business.availability.sundayStart}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="sundayBreakStart">Sunday Break Start Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="sundayBreakStart"
                  id="sundayBreakStart"
                  value={business.availability.sundayBreakStart}
                  onChange={handleChangeAvail}
                /> 
            </div>


            <div className="flex flex-col w-1/3">
              <label htmlFor="category">Category:</label>
              <select name="category" id="category" onChange={handleChange} value={business.category} className="border border-gray-600 rounded m-2 p-1">
                <option value=" " className="text-gray-200">Please Select</option>
                <option value="HEALTH">HEALTH</option>
                <option value="HAIR">HAIR</option>
                <option value="TATTOO">TATTOO</option>
                <option value="PET">PET</option>
                <option value="CLEANING">CLEANING</option>
                <option value="FOOD">FOOD</option>
              </select>
                <label htmlFor="mondayEnd">Monday End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="mondayEnd"
                  id="mondayEnd"
                  value={business.availability.mondayEnd}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="mondayBreakEnd">Monday Break End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="mondayBreakEnd"
                  id="mondayBreakEnd"
                  value={business.availability.mondayBreakEnd}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="tuesdayEnd">Tuesday End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="tuesdayEnd"
                  id="tuesdayEnd"
                  value={business.availability.tuesdayEnd}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="tuesdayBreakEnd">Tuesday Break End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="tuesdayBreakEnd"
                  id="tuesdayBreakEnd"
                  value={business.availability.tuesdayBreakEnd}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="wednesdayEnd">Wednesday End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="wednesdayEnd"
                  id="wednesdayEnd"
                  value={business.availability.wednesdayEnd}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="wednesdayBreakEnd">Wednesday Break End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="wednesdayBreakEnd"
                  id="wednesdayBreakEnd"
                  value={business.availability.wednesdayBreakEnd}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="thursdayEnd">Thursday End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="thursdayEnd"
                  id="thursdayEnd"
                  value={business.availability.thursdayEnd}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="thursdayBreakEnd">Thursday Break End Time</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="thursdayBreakEnd"
                  id="thursdayBreakEnd"
                  value={business.availability.thursdayBreakEnd}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="fridayEnd">Friday End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="fridayEnd"
                  id="fridayEnd"
                  value={business.availability.fridayEnd}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="fridayBreakEnd">Friday Break End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="fridayBreakEnd"
                  id="fridayBreakEnd"
                  value={business.availability.fridayBreakEnd}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="saturdayEnd">Saturday End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="saturdayEnd"
                  id="saturdayEnd"
                  value={business.availability.saturdayEnd}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="saturdayBreakEnd">Saturday Break End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="saturdayBreakEnd"
                  id="saturdayBreakEnd"
                  value={business.availability.saturdayBreakEnd}
                  onChange={handleChangeAvail}
                />
              <label htmlFor="sundayEnd">Sunday End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="sundayEnd"
                  id="sundayEnd"
                  value={business.availability.sundayEnd}
                  onChange={handleChangeAvail}
                />
                <label htmlFor="sundayBreakEnd">Sunday Break End Time:</label>
                <input
                  className="border border-gray-600 rounded m-2 p-1"
                  
                  type="time"
                  name="sundayBreakEnd"
                  id="sundayBreakEnd"
                  value={business.availability.sundayBreakEnd}
                  onChange={handleChangeAvail}
                /> 
            </div>

          </div>
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

export default BusinessForm;
