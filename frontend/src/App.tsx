import React, { useEffect, useState } from 'react'
import TaskList from './components/TaskList'
import TaskForm from './components/TaskForm'

interface Task {
  id: number;
  projectId: number;
  title: string;
  description?: string;
  status: string;
}

function App() {
  const [tasks, setTasks] = useState<Task[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  const fetchTasks = async () => {
    try {
      const response = await fetch('/api/tasks')
      if (!response.ok) throw new Error('Failed to fetch tasks')
      const data = await response.json()
      setTasks(data)
    } catch (err: any) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchTasks()
  }, [])

  const handleCreateTask = async (taskData: { title: string; description: string; projectId: number }) => {
    try {
      const response = await fetch('/api/tasks', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(taskData)
      })
      if (response.ok) {
        fetchTasks()
      }
    } catch (err) {
      console.error('Error creating task', err)
    }
  }

  const handleDeleteTask = async (id: number) => {
    try {
      const response = await fetch(`/api/tasks/${id}`, { method: 'DELETE' })
      if (response.ok) {
        setTasks(prev => prev.filter(t => t.id !== id))
      }
    } catch (err) {
      console.error('Error deleting task', err)
    }
  }

  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
      <h1>TaskFlow Dashboard</h1>
      <hr style={{ marginBottom: '2rem' }} />
      
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}
      
      <TaskForm onTaskCreated={handleCreateTask} />
      
      {loading ? (
        <p>Loading tasks...</p>
      ) : (
        <TaskList tasks={tasks} onDelete={handleDeleteTask} />
      )}
    </div>
  )
}

export default App
