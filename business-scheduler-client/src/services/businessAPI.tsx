const url = `${process.env.REACT_APP_API_URL}/business`;

export async function findById(businessId: number) {
  const response = await fetch(`${url}/${businessId}`);
  if (response.status === 200) {
    return await response.json();
  } else if (response.status === 404) {
    return Promise.reject(`Business for business ${businessId} could not be found.`);
  } else {
    return Promise.reject("Unexpected error :(");
  }
}

export async function findByCategory(category: string) {
  const response = await fetch(`${url}/category/${category}`);
  if (response.status === 200) {
    return await response.json();
  } else if (response.status === 404) {
    Promise.reject(`Businesses for category ${category} could not be found.`)
    return Promise.reject(`Businesses for category ${category} could not be found.`);
  } else {
    return Promise.reject("Unexpected error :(");
  }
}

export async function findByQuery(query: string) {
  const response = await fetch(`${url}/query/${query}`);
  if (response.status === 200) {
    return await response.json();
  } else if (response.status === 404) {
    Promise.reject(`Businesses for category ${query} could not be found.`)
    return Promise.reject(`Businesses for query "${query}" could not be found.`);
  } else {
    return Promise.reject("Unexpected error :(");
  }
}

export async function saveBusiness(business: any) {
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
    body: JSON.stringify(business),
  };

  if (business.businessId > 0) {
    init.method = "PUT";
    const response = await fetch(`${url}/${business.businessId}`, init);
    if (response.status === 400) {
      const result = await response.json();
      return { errors: result.messages };
    } else if (response.status === 404) {
      return Promise.reject(
        `Business id: ${business.businessId} could not be found.`
      );
    } else if (response.status !== 204) {
      return Promise.reject("Unexpected error, oops.");
    }
  } else {
    init.method = "POST";
    const response = await fetch(url, init);
    if (response.status === 201) {
      return await response.json();
    } else if (response.status === 400) {
      const result = await response.json();
      return { errors: result.messages };
    } else {
      return Promise.reject("Unexpected error, oops.");
    }
  }
}

export async function deleteBusiness(businessId: number) {
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

  const response = await fetch(`${url}/${businessId}`, init);
  if (response.status === 404) {
    return Promise.reject(`Business id: ${businessId} could not be found.`);
  } else if (response.status !== 204) {
    return Promise.reject("Unexpected error, oops.");
  }
}
