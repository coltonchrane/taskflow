import { render, screen, fireEvent } from '@testing-library/react'
import { describe, it, expect, vi } from 'vitest'
import TaskList from './TaskList'
import { Task } from '../types'

describe('TaskList', () => {
  const tasks: Task[] = [
    { 
      id: 1, 
      projectId: 1,
      title: 'Task 1', 
      description: 'Desc 1', 
      status: 'pending', 
      priority: 0,
      labels: [],
      comments: [],
      attachments: [],
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    },
    { 
      id: 2, 
      projectId: 1,
      title: 'Task 2', 
      description: 'Desc 2', 
      status: 'completed', 
      priority: 2,
      labels: [],
      comments: [],
      attachments: [],
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    },
  ]

  it('renders a list of tasks', () => {
    render(<TaskList tasks={tasks} onDelete={() => {}} />)
    expect(screen.getByText('Task 1')).toBeInTheDocument()
    expect(screen.getByText('Task 2')).toBeInTheDocument()
    expect(screen.getByText('pending')).toBeInTheDocument()
    expect(screen.getByText('completed')).toBeInTheDocument()
    expect(screen.getByText('Low Priority')).toBeInTheDocument()
    expect(screen.getByText('High Priority')).toBeInTheDocument()
  })

  it('calls onDelete when delete button is clicked', () => {
    const onDelete = vi.fn()
    render(<TaskList tasks={tasks} onDelete={onDelete} />)
    
    const deleteButtons = screen.getAllByTitle('Delete Task')
    fireEvent.click(deleteButtons[0])
    
    expect(onDelete).toHaveBeenCalledWith(1)
  })

  it('renders no tasks message when list is empty', () => {
    render(<TaskList tasks={[]} onDelete={() => {}} />)
    expect(screen.getByText(/No tasks found/i)).toBeInTheDocument()
  })
})
