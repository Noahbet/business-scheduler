const url = `${process.env.REACT_APP_API_URL}/service`;

export async function findById(serviceId: number) {
  const response = await fetch(`${url}/${serviceId}`);
  if (response.status === 200) {
    return await response.json();
  } else if (response.status === 404) {
    return Promise.reject(`Service for service id ${serviceId} could not be found.`);
  } else {
    return Promise.reject("Unexpected error :(");
  }
}

export async function saveService(service: any) {
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
    method: "",
    body: JSON.stringify(service),
  };

  if (service.serviceId > 0) {
    init.method = "PUT";
    const response = await fetch(`${url}/${service.serviceId}`, init);
    if (response.status === 400) {
      const result = await response.json();
      return { errors: result.messages };
    } else if (response.status === 404) {
      return Promise.reject(
        `Service id: ${service.serviceId} could not be found.`
      );
    } else if (response.status !== 204) {
      return Promise.reject("Unexpected error, oops.");
    }
  } else {
    init.method = "POST";
    const response = await fetch(url, init);
    if (response.status === 201) {
      console.log(response)
      return await response.json();
    } else if (response.status === 400) {
      const result = await response.json();
      return { errors: result.messages };
    } else {
      return Promise.reject("Unexpected error, oops.");
    }
  }
}

export async function deleteService(serviceId: number) {
  const jwtToken = localStorage.getItem("jwt_token");
  if (!jwtToken) {
    return Promise.reject("Unauthorized.");
  }
    
  const init = {
    method: "DELETE",
    headers: {
      "Authorization": "Bearer " + jwtToken
    },
  };

  const response = await fetch(`${url}/${serviceId}`, init);
  if (response.status === 404) {
    return Promise.reject(`Service id: ${serviceId} could not be found.`);
  } else if (response.status !== 204) {
    return Promise.reject("Unexpected error, oops.");
  }
}
