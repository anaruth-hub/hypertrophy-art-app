import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { trainerApi, auth, SupervisedUser, ProgressSummary } from '../api'

export default function TrainerDashboard() {
  const navigate = useNavigate()
  const [users, setUsers] = useState<SupervisedUser[]>([])
  const [selectedUser, setSelectedUser] = useState<SupervisedUser | null>(null)
  const [userProgress, setUserProgress] = useState<ProgressSummary | null>(null)
  const [loading, setLoading] = useState(true)
  const [progressLoading, setProgressLoading] = useState(false)
  const [error, setError] = useState('')
  
  // Form state
  const [recDate, setRecDate] = useState(() => new Date().toISOString().split('T')[0])
  const [recMessage, setRecMessage] = useState('')
  const [submitting, setSubmitting] = useState(false)
  const [submitSuccess, setSubmitSuccess] = useState(false)
  const [submitError, setSubmitError] = useState('')

  const userInfo = auth.getUserInfo()

  useEffect(() => {
    loadUsers()
  }, [])

  const loadUsers = async () => {
    setLoading(true)
    setError('')
    try {
      const data = await trainerApi.getSupervisedUsers()
      setUsers(Array.isArray(data) ? data : [])
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Error al cargar usuarios')
    } finally {
      setLoading(false)
    }
  }

  const selectUser = async (user: SupervisedUser) => {
    setSelectedUser(user)
    setUserProgress(null)
    setProgressLoading(true)
    setSubmitSuccess(false)
    setSubmitError('')
    
    try {
      const progress = await trainerApi.getUserProgress(user.id)
      setUserProgress(progress)
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Error al cargar progreso')
    } finally {
      setProgressLoading(false)
    }
  }

  const handleSubmitRecommendation = async (e: React.FormEvent) => {
    e.preventDefault()
    if (!selectedUser) return
    
    setSubmitting(true)
    setSubmitError('')
    setSubmitSuccess(false)
    
    try {
      await trainerApi.createRecommendation(selectedUser.id, {
        date: recDate,
        message: recMessage,
      })
      setSubmitSuccess(true)
      setRecMessage('')
    } catch (err) {
      setSubmitError(err instanceof Error ? err.message : 'Error al crear recomendacion')
    } finally {
      setSubmitting(false)
    }
  }

  const handleLogout = () => {
    auth.logout()
    navigate('/login')
  }

  return (
    <div>
      <header>
        <div className="header-content">
          <h1 className="text-xl font-bold">Fitness Tracker - Entrenador</h1>
          <div className="flex items-center gap-4">
            <span className="text-sm text-muted">Hola, {userInfo.name}</span>
            <button onClick={handleLogout} className="btn btn-destructive">
              Cerrar Sesion
            </button>
          </div>
        </div>
      </header>

      <div className="container">
        {loading && <div className="loading">Cargando usuarios...</div>}
        {error && <p className="error-message">{error}</p>}

        {!loading && !error && (
          <div className="grid grid-cols-2 gap-4" style={{ gridTemplateColumns: '1fr 2fr' }}>
            {/* Users List */}
            <div className="card">
              <h2 className="text-lg font-semibold mb-4">Mis Usuarios Supervisados</h2>
              {users.length > 0 ? (
                <ul className="list">
                  {users.map((user) => (
                    <li
                      key={user.id}
                      className={`list-item ${selectedUser?.id === user.id ? 'selected' : ''}`}
                      onClick={() => selectUser(user)}
                    >
                      <p className="font-semibold">{user.name}</p>
                      <p className="text-sm text-muted">{user.email}</p>
                    </li>
                  ))}
                </ul>
              ) : (
                <p className="text-muted">No tienes usuarios supervisados</p>
              )}
            </div>

            {/* Selected User Details */}
            <div>
              {selectedUser ? (
                <>
                  {/* User Progress */}
                  <div className="card mb-4">
                    <h2 className="text-lg font-semibold mb-4">
                      Progreso de {selectedUser.name}
                    </h2>
                    
                    {progressLoading && <div className="loading">Cargando progreso...</div>}
                    
                    {!progressLoading && userProgress && (
                      <div className="grid grid-cols-2 gap-4">
                        <div className="card">
                          <span className="text-sm text-muted">Total Entrenamientos</span>
                          <p className="text-2xl font-bold">{userProgress.totalWorkouts || 0}</p>
                        </div>
                        <div className="card">
                          <span className="text-sm text-muted">Duracion Total</span>
                          <p className="text-2xl font-bold">{userProgress.totalDuration || 0} min</p>
                        </div>
                        <div className="card">
                          <span className="text-sm text-muted">Duracion Promedio</span>
                          <p className="text-2xl font-bold">{userProgress.averageDuration || 0} min</p>
                        </div>
                        <div className="card">
                          <span className="text-sm text-muted">Ultimo Entrenamiento</span>
                          <p className="text-lg font-semibold">
                            {userProgress.lastWorkoutDate || 'Sin registro'}
                          </p>
                        </div>
                      </div>
                    )}

                    {!progressLoading && !userProgress && (
                      <p className="text-muted">No hay datos de progreso disponibles</p>
                    )}
                  </div>

                  {/* Create Recommendation Form */}
                  <div className="card">
                    <h2 className="text-lg font-semibold mb-4">
                      Crear Recomendacion para {selectedUser.name}
                    </h2>
                    
                    <form onSubmit={handleSubmitRecommendation}>
                      <div className="form-group">
                        <label className="label" htmlFor="recDate">Fecha</label>
                        <input
                          id="recDate"
                          type="date"
                          className="input"
                          value={recDate}
                          onChange={(e) => setRecDate(e.target.value)}
                          required
                        />
                      </div>
                      
                      <div className="form-group">
                        <label className="label" htmlFor="recMessage">Mensaje</label>
                        <textarea
                          id="recMessage"
                          className="textarea"
                          value={recMessage}
                          onChange={(e) => setRecMessage(e.target.value)}
                          required
                          placeholder="Escribe tu recomendacion para el usuario..."
                          rows={4}
                        />
                      </div>
                      
                      {submitError && <p className="error-message">{submitError}</p>}
                      {submitSuccess && (
                        <p className="success-message">Recomendacion creada exitosamente</p>
                      )}
                      
                      <button
                        type="submit"
                        className="btn btn-primary"
                        disabled={submitting}
                      >
                        {submitting ? 'Enviando...' : 'Enviar Recomendacion'}
                      </button>
                    </form>
                  </div>
                </>
              ) : (
                <div className="card text-center">
                  <p className="text-muted">Selecciona un usuario para ver su progreso</p>
                </div>
              )}
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
