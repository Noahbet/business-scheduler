
function BusinessPicture({businessId} : {businessId:number}) {

  let description: string = "picture of business " + businessId;


  return (
   <p className="border border-gray-500 w-1/3">
      <img src="" alt={description} />
   </p>
  );
}

export default BusinessPicture;