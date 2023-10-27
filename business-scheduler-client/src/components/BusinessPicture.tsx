import image1 from "../assets/1.png"
import image2 from "../assets/2.png"
import image3 from "../assets/3.png"
import image4 from "../assets/4.png"
import image5 from "../assets/5.png"
import image6 from "../assets/6.png"


function BusinessPicture({businessId} : {businessId:number}) {

  let description: string = "picture of business " + businessId;

  let baseImageUrl = "https://images.unsplash.com/photo-1550136513-548af4445338?auto=format&fit=crop&q=80&w=2948&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"

  return (
   <p className="border border-gray-300 m-0">
      <img src={businessId === 1 ? image1 : businessId === 2 ? image2 : businessId === 3 ? image3 : businessId === 4 ? image4 : businessId === 5 ? image5 : businessId === 6 ? image6 : baseImageUrl} alt={description} className="border border-gray-300 rounded w-80"/>
   </p>
  );
}

export default BusinessPicture;