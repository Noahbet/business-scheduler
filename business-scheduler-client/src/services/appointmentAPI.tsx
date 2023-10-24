const url = `${process.env.REACT_APP_API_URL}/appointment`;

export async function findByUserId(userId: number) {
    const response = await fetch(`${url}/${userId}`);
    if (response.status === 200) {
      return response.json();
    } else if (response.status === 404) {
      return Promise.reject(`Appintments for userId ${userId} could not be found.`);
    } else {
      return Promise.reject("Unexpected error :(");
    }
  }

export async function saveAppointment(appointment: any) {
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
        method: "POST",
        body: JSON.stringify(appointment),
    };
    const response = await fetch(url, init);
    if (response.status === 201) {
        return response.json();
    } else if (response.status === 400) {
        const result = await response.json();
        return { errors: result.messages };
    } else {
        return Promise.reject("Unexpected error, oops.");
    }
}

export async function deleteAppointment(appointmentId: number) {
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
  
    const response = await fetch(`${url}/${appointmentId}`, init);
    if (response.status === 404) {
      return Promise.reject(`Appointment id: ${appointmentId} could not be found.`);
    } else if (response.status !== 204) {
      return Promise.reject("Unexpected error, oops.");
    }
  }
  