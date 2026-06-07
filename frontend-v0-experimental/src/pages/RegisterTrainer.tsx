import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { authApi } from '../api'

export default function RegisterTrainer() {
  const navigate = useNavigate()
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState(false)

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setError('')
    setLoading(true)

    try {
      await authApi.registerTrainer({ name, email, password })
      setSuccess(true)
      setTimeout(() => navigate('/login'), 2000)
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Error al registrar entrenador')
    } finally {
      setLoading(false)
    }
  }

  if (success) {
    return (
      <div className="container" style={{ maxWidth: '400px', marginTop: '4rem' }}>
        <div className="card text-center">
          <h2 className="text-lg font-semibold mb-4">Registro Exitoso</h2>
          <p className="text-muted">Redirigiendo al login...</p>
        </div>
      </div>
    )
  }

  return (
    <div className="container" style={{ maxWidth: '400px', marginTop: '4rem' }}>
      <div className="card">
        <h1 className="text-2xl font-bold text-center mb-6">Fitness Tracker</h1>
        <h2 className="text-lg font-semibold text-center mb-4">Registrar Entrenador</h2>
        
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="label" htmlFor="name">Nombre</label>
            <input
              id="name"
              type="text"
              className="input"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
              placeholder="Tu nombre"
            />
          </div>
          
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
              minLength={6}
            />
          </div>
          
          {error && <p className="error-message">{error}</p>}
          
          <button 
            type="submit" 
            className="btn btn-primary" 
            style={{ width: '100%', marginTop: '1rem' }}
            disabled={loading}
          >
            {loading ? 'Registrando...' : 'Registrar'}
          </button>
        </form>
        
        <div className="text-center mt-4">
          <Link to="/login" className="text-sm text-muted">
            Ya tienes cuenta? Inicia sesion
          </Link>
        </div>
      </div>
    </div>
  )
}
