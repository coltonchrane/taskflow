import React, { useState } from 'react'

interface TaskFormProps {
  onTaskCreated: (task: { title: string; description: string; projectId: number }) => void;
}

const TaskForm: React.FC<TaskFormProps> = ({ onTaskCreated }) => {
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (!title) return
    onTaskCreated({ title, description, projectId: 1 }) // Default to project 1
    setTitle('')
    setDescription('')
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: '2rem', display: 'flex', flexDirection: 'column', gap: '0.5rem', maxWidth: '400px' }}>
      <h2>Create New Task</h2>
      <input 
        type="text" 
        placeholder="Task Title" 
        value={title} 
        onChange={(e) => setTitle(e.target.value)} 
        required 
      />
      <textarea 
        placeholder="Task Description" 
        value={description} 
        onChange={(e) => setDescription(e.target.value)} 
      />
      <button type="submit" style={{ padding: '0.5rem', cursor: 'pointer', background: '#007bff', color: 'white', border: 'none', borderRadius: '4px' }}>
        Add Task
      </button>
    </form>
  )
}

export default TaskForm
