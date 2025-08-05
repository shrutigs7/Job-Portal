// src/api/auth.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081';

export const loginUser = async (email, password) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/signin`, {
      email,
      password,
    });
    console.log(response.data);
    return response.data; 
    
  } catch (error) {
    throw error.response ? error.response.data : new Error("Network Error");
  }
};
