const API_BASE_URL = "http://localhost:8080"

interface FetchOptions extends RequestInit {
  skipAuth?: boolean
}

export async function apiFetch<T>(
  endpoint: string,
  options: FetchOptions = {}
): Promise<T> {
  const { skipAuth = false, ...fetchOptions } = options
  
  const headers: HeadersInit = {
    "Content-Type": "application/json",
    ...fetchOptions.headers,
  }

  if (!skipAuth) {
    const token = localStorage.getItem("token")
    if (token) {
      ;(headers as Record<string, string>)["Authorization"] = `Bearer ${token}`
    }
  }

  const url = `${API_BASE_URL}${endpoint}`
  console.log(`[v0] API Request: ${fetchOptions.method || 'GET'} ${url}`)
  if (fetchOptions.body) {
    console.log(`[v0] Request Body:`, fetchOptions.body)
  }

  let response: Response
  try {
    response = await fetch(url, {
      ...fetchOptions,
      headers,
    })
  } catch (networkError) {
    console.error(`[v0] Network Error:`, networkError)
    throw new Error(`Network error: Unable to connect to ${API_BASE_URL}. Make sure your backend is running and CORS is configured.`)
  }

  console.log(`[v0] API Response: ${response.status} ${response.statusText}`)

  if (!response.ok) {
    const errorText = await response.text()
    console.error(`[v0] API Error Response:`, errorText)
    let errorMessage = `Error: ${response.status}`
    try {
      const errorData = JSON.parse(errorText)
      errorMessage = errorData.message || errorData.error || errorMessage
    } catch {
      if (errorText) errorMessage = errorText
    }
    throw new Error(errorMessage)
  }

  // Handle empty responses
  const text = await response.text()
  if (!text) {
    console.log(`[v0] Empty response body`)
    return {} as T
  }
  
  console.log(`[v0] Response Body:`, text)
  return JSON.parse(text) as T
}

// Auth types
export interface LoginRequest {
  email: string
  password: string
}

export interface LoginResponse {
  token: string
  role: "USER" | "TRAINER"
  id: string
  name: string
  email: string
}

export interface RegisterUserRequest {
  name: string
  email: string
  password: string
  mode: "SUPERVISED" | "SELF_MANAGED"
}

export interface RegisterTrainerRequest {
  name: string
  email: string
  password: string
}

// User types
export interface User {
  id: string
  name: string
  email: string
  role: string
  mode?: string
}

// Progress types
export interface ProgressSummary {
  userId: string
  userName?: string
  totalWorkouts?: number
  totalDuration?: number
  averageDuration?: number
  lastWorkoutDate?: string
  progressEntries?: ProgressEntry[]
}

export interface ProgressEntry {
  id: string
  date: string
  duration: number
  notes?: string
}

// Recommendation types
export interface Recommendation {
  id: string
  date: string
  message: string
  trainerName?: string
  createdAt?: string
}

export interface CreateRecommendationRequest {
  date: string
  message: string
}

// Supervised user type
export interface SupervisedUser {
  id: string
  name: string
  email: string
}

// Auth API
export const authApi = {
  login: (data: LoginRequest) =>
    apiFetch<LoginResponse>("/api/auth/login", {
      method: "POST",
      body: JSON.stringify(data),
      skipAuth: true,
    }),

  registerUser: (data: RegisterUserRequest) =>
    apiFetch<void>("/api/auth/register-user", {
      method: "POST",
      body: JSON.stringify(data),
      skipAuth: true,
    }),

  registerTrainer: (data: RegisterTrainerRequest) =>
    apiFetch<void>("/api/auth/register-trainer", {
      method: "POST",
      body: JSON.stringify(data),
      skipAuth: true,
    }),
}

// User API
export const userApi = {
  getMe: () => apiFetch<User>("/api/users/me"),
  
  getProgressSummary: () => apiFetch<ProgressSummary>("/api/progress-summary/me"),
  
  getRecommendations: () => apiFetch<Recommendation[]>("/api/recommendations/me"),
}

// Trainer API
export const trainerApi = {
  getSupervisedUsers: () => apiFetch<SupervisedUser[]>("/api/trainers/me/users"),
  
  getUserProgress: (userId: string) =>
    apiFetch<ProgressSummary>(`/api/progress-summary/trainers/me/users/${userId}/progress`),
  
  createRecommendation: (userId: string, data: CreateRecommendationRequest) =>
    apiFetch<Recommendation>(`/api/recommendations/trainers/me/users/${userId}`, {
      method: "POST",
      body: JSON.stringify(data),
    }),
}

// Auth helpers
export const auth = {
  isAuthenticated: () => !!localStorage.getItem("token"),
  
  getRole: () => localStorage.getItem("role") as "USER" | "TRAINER" | null,
  
  getUserInfo: () => ({
    id: localStorage.getItem("id"),
    name: localStorage.getItem("name"),
    email: localStorage.getItem("email"),
    role: localStorage.getItem("role"),
  }),
  
  saveAuth: (data: LoginResponse) => {
    localStorage.setItem("token", data.token)
    localStorage.setItem("role", data.role)
    localStorage.setItem("id", data.id)
    localStorage.setItem("name", data.name)
    localStorage.setItem("email", data.email)
  },
  
  logout: () => {
    localStorage.removeItem("token")
    localStorage.removeItem("role")
    localStorage.removeItem("id")
    localStorage.removeItem("name")
    localStorage.removeItem("email")
  },
}
