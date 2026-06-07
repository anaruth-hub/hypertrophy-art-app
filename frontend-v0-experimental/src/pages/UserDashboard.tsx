import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { userApi, auth, User, ProgressSummary, Recommendation } from '../api'

export default function UserDashboard() {
  const navigate = useNavigate()
  const [activeTab, setActiveTab] = useState<'profile' | 'progress' | 'recommendations'>('profile')
  const [user, setUser] = useState<User | null>(null)
  const [progress, setProgress] = useState<ProgressSummary | null>(null)
  const [recommendations, setRecommendations] = useState<Recommendation[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  const userInfo = auth.getUserInfo()

  useEffect(() => {
    loadData()
  }, [])

  const loadData = async () => {
    setLoading(true)
    setError('')
    try {
      const [userData, progressData, recommendationsData] = await Promise.all([
        userApi.getMe(),
        userApi.getProgressSummary(),
        userApi.getRecommendations(),
      ])
      setUser(userData)
      setProgress(progressData)
      setRecommendations(Array.isArray(recommendationsData) ? recommendationsData : [])
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Error al cargar datos')
    } finally {
      setLoading(false)
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
          <h1 className="text-xl font-bold">Fitness Tracker</h1>
          <div className="flex items-center gap-4">
            <span className="text-sm text-muted">Hola, {userInfo.name}</span>
            <button onClick={handleLogout} className="btn btn-destructive">
              Cerrar Sesion
            </button>
          </div>
        </div>
      </header>

      <div className="container">
        <div className="tabs">
          <button
            className={`tab ${activeTab === 'profile' ? 'active' : ''}`}
            onClick={() => setActiveTab('profile')}
          >
            Mi Perfil
          </button>
          <button
            className={`tab ${activeTab === 'progress' ? 'active' : ''}`}
            onClick={() => setActiveTab('progress')}
          >
            Mi Progreso
          </button>
          <button
            className={`tab ${activeTab === 'recommendations' ? 'active' : ''}`}
            onClick={() => setActiveTab('recommendations')}
          >
            Recomendaciones
          </button>
        </div>

        {loading && <div className="loading">Cargando...</div>}
        {error && <p className="error-message">{error}</p>}

        {!loading && !error && (
          <>
            {activeTab === 'profile' && user && (
              <div className="card">
                <h2 className="text-lg font-semibold mb-4">Mi Perfil</h2>
                <div className="grid gap-4">
                  <div>
                    <span className="text-sm text-muted">Nombre</span>
                    <p className="font-semibold">{user.name}</p>
                  </div>
                  <div>
                    <span className="text-sm text-muted">Email</span>
                    <p className="font-semibold">{user.email}</p>
                  </div>
                  <div>
                    <span className="text-sm text-muted">Rol</span>
                    <p><span className="badge">{user.role}</span></p>
                  </div>
                  {user.mode && (
                    <div>
                      <span className="text-sm text-muted">Modo</span>
                      <p><span className="badge badge-primary">{user.mode}</span></p>
                    </div>
                  )}
                </div>
              </div>
            )}

            {activeTab === 'progress' && (
              <div className="card">
                <h2 className="text-lg font-semibold mb-4">Mi Progreso</h2>
                {progress ? (
                  <div className="grid grid-cols-2 gap-4">
                    <div className="card">
                      <span className="text-sm text-muted">Total Entrenamientos</span>
                      <p className="text-2xl font-bold">{progress.totalWorkouts || 0}</p>
                    </div>
                    <div className="card">
                      <span className="text-sm text-muted">Duracion Total</span>
                      <p className="text-2xl font-bold">{progress.totalDuration || 0} min</p>
                    </div>
                    <div className="card">
                      <span className="text-sm text-muted">Duracion Promedio</span>
                      <p className="text-2xl font-bold">{progress.averageDuration || 0} min</p>
                    </div>
                    <div className="card">
                      <span className="text-sm text-muted">Ultimo Entrenamiento</span>
                      <p className="text-lg font-semibold">
                        {progress.lastWorkoutDate || 'Sin registro'}
                      </p>
                    </div>
                  </div>
                ) : (
                  <p className="text-muted">No hay datos de progreso disponibles</p>
                )}

                {progress?.progressEntries && progress.progressEntries.length > 0 && (
                  <div className="mt-4">
                    <h3 className="font-semibold mb-4">Historial de Entrenamientos</h3>
                    <ul className="list">
                      {progress.progressEntries.map((entry) => (
                        <li key={entry.id} className="list-item">
                          <div className="flex justify-between items-center">
                            <span className="font-semibold">{entry.date}</span>
                            <span className="badge">{entry.duration} min</span>
                          </div>
                          {entry.notes && (
                            <p className="text-sm text-muted mt-4">{entry.notes}</p>
                          )}
                        </li>
                      ))}
                    </ul>
                  </div>
                )}
              </div>
            )}

            {activeTab === 'recommendations' && (
              <div className="card">
                <h2 className="text-lg font-semibold mb-4">Mis Recomendaciones</h2>
                {recommendations.length > 0 ? (
                  <ul className="list">
                    {recommendations.map((rec) => (
                      <li key={rec.id} className="list-item">
                        <div className="flex justify-between items-center mb-4">
                          <span className="badge">{rec.date}</span>
                          {rec.trainerName && (
                            <span className="text-sm text-muted">Por: {rec.trainerName}</span>
                          )}
                        </div>
                        <p>{rec.message}</p>
                      </li>
                    ))}
                  </ul>
                ) : (
                  <p className="text-muted">No tienes recomendaciones todavia</p>
                )}
              </div>
            )}
          </>
        )}
      </div>
    </div>
  )
}
