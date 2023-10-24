const url = `${process.env.REACT_APP_API_URL}/business`;

export async function searchByUserId(userId: number) {
    const response = await fetch(`${url}/${userId}`);
    if (response.status === 200) {
        return response.json();
    } else if (response.status === 404) {
        return Promise.reject(`User ${userId} could not be found.`);
    } else {
        return Promise.reject("Unexpected error :(");
    }
}

export async function addNotification(notification: any) {
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
        body: JSON.stringify(notification),
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

export async function deleteNotification(notificationId: number) {
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

    const response = await fetch(`${url}/${notificationId}`, init);
    if (response.status === 404) {
        return Promise.reject(`Notification id: ${notificationId} could not be found.`);
    } else if (response.status !== 204) {
        return Promise.reject("Unexpected error, oops.");
    }
}
