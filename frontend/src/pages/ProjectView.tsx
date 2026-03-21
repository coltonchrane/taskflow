import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { api } from '../api/client'
import { Task, Project } from '../types'
import TaskList from '../components/TaskList'
import TaskForm from '../components/TaskForm'

const ProjectView: React.FC = () => {
  const { id } = useParams<{ id: string }>()
  const navigate = useNavigate()
  const [project, setProject] = useState<Project | null>(null)
  const [tasks, setTasks] = useState<Task[]>([])
  const [loading, setLoading] = useState(true)

  const fetchProjectData = async () => {
    try {
      setLoading(true)
      const [projData, tasksData] = await Promise.all([
        api.get<Project>(`/projects/${id}`),
        api.get<Task[]>(`/tasks?projectId=${id}`)
      ])
      
      setProject(projData)
      setTasks(tasksData)
    } catch (err) {
      console.error('Failed to fetch project data', err)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchProjectData()
  }, [id])

  const handleCreateTask = async (taskData: any) => {
    try {
      await api.post('/tasks', { ...taskData, projectId: Number(id) })
      fetchProjectData()
    } catch (err) {
      console.error('Error creating task', err)
    }
  }

  const handleDeleteTask = async (taskId: number) => {
    try {
      await api.delete(`/tasks/${taskId}`)
      setTasks(prev => prev.filter(t => t.id !== taskId))
    } catch (err) {
      console.error('Error deleting task', err)
    }
  }

  if (loading) return <div className="text-center py-10">Loading project...</div>
  if (!project) return <div className="text-center py-10">Project not found</div>

  return (
    <div className="space-y-8">
      <div className="flex items-center space-x-2 text-sm text-gray-500 mb-2">
         <button onClick={() => navigate('/')} className="hover:text-blue-600">Projects</button>
         <span>/</span>
         <span className="text-gray-900 font-medium">{project.name}</span>
      </div>

      <div className="bg-white p-8 rounded-2xl shadow-sm border border-gray-100">
        <h1 className="text-3xl font-extrabold text-gray-900 mb-3">{project.name}</h1>
        <p className="text-gray-600 max-w-2xl">{project.description}</p>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <div className="lg:col-span-2">
          <TaskList tasks={tasks} onDelete={handleDeleteTask} />
        </div>
        <div>
          <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-200 sticky top-6">
            <h2 className="text-lg font-bold text-gray-900 mb-4">Add Task</h2>
            <TaskForm onTaskCreated={handleCreateTask} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProjectView
