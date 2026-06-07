export const API_BASE_URL = "http://localhost:8080";

export function getToken() {
  return localStorage.getItem("token");
}

export function saveAuth(authData) {
  localStorage.setItem("token", authData.token);
  localStorage.setItem("role", authData.role);
  localStorage.setItem("id", authData.id);
  localStorage.setItem("name", authData.name);
  localStorage.setItem("email", authData.email);
}

export function clearAuth() {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  localStorage.removeItem("id");
  localStorage.removeItem("name");
  localStorage.removeItem("email");
}

export function getStoredAuth() {
  const token = localStorage.getItem("token");

  if (!token) {
    return null;
  }

  return {
    token,
    role: localStorage.getItem("role"),
    id: localStorage.getItem("id"),
    name: localStorage.getItem("name"),
    email: localStorage.getItem("email"),
  };
}

export async function apiFetch(path, options = {}) {
  const token = getToken();

  const headers = {
    "Content-Type": "application/json",
    ...(options.headers || {}),
  };

  if (token) {
    headers.Authorization = `Bearer ${token}`;
  }

  const response = await fetch(`${API_BASE_URL}${path}`, {
    ...options,
    headers,
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(
      errorText || `Request failed with status ${response.status}`
    );
  }

  if (response.status === 204) {
    return null;
  }

  return response.json();
}