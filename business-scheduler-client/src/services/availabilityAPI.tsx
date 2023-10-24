const url = `${process.env.REACT_APP_API_URL}/availability`;


export async function saveService(availability: any) {
  const jwtToken = localStorage.getItem("jwt_token");
  if (!jwtToken) {
    return Promise.reject("Unauthorized.");
  }

  const init = {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      Authorization: "Bearer " + jwtToken,
    },
    method: "PUT",
    body: JSON.stringify(availability),
  };
  const response = await fetch(`${url}/${availability.businessId}`, init);
  if (response.status === 400) {
    const result = await response.json();
    return { errors: result.messages };
  } else if (response.status === 404) {
    return Promise.reject(
      `Availability for Business id: ${availability.businessId} could not be found.`
    );
  } else if (response.status !== 204) {
    return Promise.reject("Unexpected error, oops.");
  }
}