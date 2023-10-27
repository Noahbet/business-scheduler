function ValidationSummary({ errors = [] } : {errors:Array<string>}) {
  if (!errors || errors.length === 0) {
    return null;
  }

  return (
    <div>
      {
        !errors || errors == null || errors.at(0) === "" || errors.length === 0 ? (
          <div></div>
        ) : (
          <div className="p-4">
            <h5>Failed due to the following:</h5>
            <ul className="flex flex-col items-center">
              {errors.map((err, index) => (
                <li key={index}>{err}</li>
              ))}
            </ul>
          </div>
        )
      }
    </div>
  );
}

export default ValidationSummary;
