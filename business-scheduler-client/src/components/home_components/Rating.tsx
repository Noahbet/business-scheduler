import './assets/star-empty.png';
import './assets/star-half.png';
import './assets/star-full.png';

function Rating({rating} : {rating:number}) {

  return (
   <p>
      {rating}
   </p>
  );
}

export default Rating;