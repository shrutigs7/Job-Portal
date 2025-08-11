// src/api/auth.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081';

export const loginUser = async (email, password) => {
  try {
    const response = await axios.post(
      'http://localhost:8081/users/signin',
      { email, password },
      {
        headers: {
          'Content-Type': 'application/json',
        },
        withCredentials: false // Set true only if you use cookies
      }
    );
    console.log(response.data);
    return response.data;

  } catch (error) {
    throw error.response ? error.response.data : new Error("Network Error");
  }
};
