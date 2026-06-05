import { Routes, Route, Navigate } from 'react-router-dom'
import { auth } from './api'
import Login from './pages/Login'
import RegisterUser from './pages/RegisterUser'
import RegisterTrainer from './pages/RegisterTrainer'
import UserDashboard from './pages/UserDashboard'
import TrainerDashboard from './pages/TrainerDashboard'

function ProtectedRoute({ 
  children, 
  allowedRole 
}: { 
  children: React.ReactNode
  allowedRole: "USER" | "TRAINER" 
}) {
  if (!auth.isAuthenticated()) {
    return <Navigate to="/login" replace />
  }
  
  const role = auth.getRole()
  if (role !== allowedRole) {
    return <Navigate to={role === "USER" ? "/dashboard" : "/trainer"} replace />
  }
  
  return <>{children}</>
}

function PublicRoute({ children }: { children: React.ReactNode }) {
  if (auth.isAuthenticated()) {
    const role = auth.getRole()
    return <Navigate to={role === "USER" ? "/dashboard" : "/trainer"} replace />
  }
  return <>{children}</>
}

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/login" replace />} />
      
      <Route 
        path="/login" 
        element={
          <PublicRoute>
            <Login />
          </PublicRoute>
        } 
      />
      
      <Route 
        path="/register-user" 
        element={
          <PublicRoute>
            <RegisterUser />
          </PublicRoute>
        } 
      />
      
      <Route 
        path="/register-trainer" 
        element={
          <PublicRoute>
            <RegisterTrainer />
          </PublicRoute>
        } 
      />
      
      <Route 
        path="/dashboard" 
        element={
          <ProtectedRoute allowedRole="USER">
            <UserDashboard />
          </ProtectedRoute>
        } 
      />
      
      <Route 
        path="/trainer" 
        element={
          <ProtectedRoute allowedRole="TRAINER">
            <TrainerDashboard />
          </ProtectedRoute>
        } 
      />
      
      <Route path="*" element={<Navigate to="/login" replace />} />
    </Routes>
  )
}
