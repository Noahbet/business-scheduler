const url = process.env.REACT_APP_API_URL;

export async function login(credentials: object) {

  const init = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    },
    body: JSON.stringify(credentials)
  };

  const response = await fetch(url + '/login', init);
  if (response.status === 200) {
    const jwtTokenResponse = await response.json();
    localStorage.setItem('jwt_token', jwtTokenResponse.jwt_token);
    return makeUserFromJwt(jwtTokenResponse.jwt_token);
  } else {
    return Promise.reject('Unauthorized.');
  }
}

export async function register(credentials: object) {
  const init = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    },
    body: JSON.stringify(credentials)
  };

  const response = await fetch(url + '/register', init);
  if (response.status === 400) {
    const result: any = response.json();
    return { errors: result.messages };
  } else if (response.status !== 201) {
    return Promise.reject("Unexpected error, oops.");
  }
}

export async function refreshToken() {

  const jwtToken = localStorage.getItem('jwt_token');
  if (!jwtToken) {
    return Promise.reject('Unauthorized.')
  }

  const init = {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + jwtToken
    }
  }

  const response = await fetch(url + '/refresh-token', init);
  if (response.status === 200) {
    const jwtTokenResponse = await response.json();
    localStorage.setItem('jwt_token', jwtTokenResponse.jwt_token);
    return makeUserFromJwt(jwtTokenResponse.jwt_token);
  } else {
    localStorage.removeItem('jwt_token');
    return Promise.reject('Unauthorized.');
  }
}

export function logout() {
  localStorage.removeItem('jwt_token');
}

function makeUserFromJwt(jwtToken: string) {
  const jwtParts = jwtToken.split('.');
  if (jwtParts.length === 3) {
    const userData = atob(jwtParts[1]);
    const decodedToken = JSON.parse(userData);
    return {
      username: decodedToken.sub,
      authorities: decodedToken.authorities
    };
  }
}