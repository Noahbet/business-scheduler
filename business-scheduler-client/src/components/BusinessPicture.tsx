
function BusinessPicture({businessId} : {businessId:number}) {

  let description: string = "picture of business " + businessId;


  return (
   <p className="border border-gray-300 m-0">
      <img src="https://images.unsplash.com/photo-1550136513-548af4445338?auto=format&fit=crop&q=80&w=2948&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt={description} className="border border-gray-300 rounded w-80"/>
   </p>
  );
}

export default BusinessPicture;