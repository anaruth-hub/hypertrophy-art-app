import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { authApi, auth } from '../api'

export default function Login() {
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setError('')
    setLoading(true)

    try {
      const response = await authApi.login({ email, password })
      auth.saveAuth(response)
      
      if (response.role === 'USER') {
        navigate('/dashboard')
      } else {
        navigate('/trainer')
      }
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Error al iniciar sesion')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container" style={{ maxWidth: '400px', marginTop: '4rem' }}>
      <div className="card">
        <h1 className="text-2xl font-bold text-center mb-6">Fitness Tracker</h1>
        <h2 className="text-lg font-semibold text-center mb-4">Iniciar Sesion</h2>
        
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="label" htmlFor="email">Email</label>
            <input
              id="email"
              type="email"
              className="input"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              placeholder="tu@email.com"
            />
          </div>
          
          <div className="form-group">
            <label className="label" htmlFor="password">Contrasena</label>
            <input
              id="password"
              type="password"
              className="input"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              placeholder="********"
            />
          </div>
          
          {error && <p className="error-message">{error}</p>}
          
          <button 
            type="submit" 
            className="btn btn-primary" 
            style={{ width: '100%', marginTop: '1rem' }}
            disabled={loading}
          >
            {loading ? 'Cargando...' : 'Entrar'}
          </button>
        </form>
        
        <div className="text-center mt-4 text-sm text-muted">
          <p>No tienes cuenta?</p>
          <div className="flex justify-between gap-4 mt-4">
            <Link to="/register-user" className="btn btn-secondary" style={{ flex: 1 }}>
              Registrar Usuario
            </Link>
            <Link to="/register-trainer" className="btn btn-secondary" style={{ flex: 1 }}>
              Registrar Entrenador
            </Link>
          </div>
        </div>
      </div>
    </div>
  )
}
