import { render, screen, fireEvent } from '@testing-library/react'
import { describe, it, expect, vi } from 'vitest'
import TaskList from './TaskList'

describe('TaskList', () => {
  const tasks = [
    { id: 1, title: 'Task 1', description: 'Desc 1', status: 'pending' },
    { id: 2, title: 'Task 2', description: 'Desc 2', status: 'done' },
  ]

  it('renders a list of tasks', () => {
    render(<TaskList tasks={tasks} onDelete={() => {}} />)
    expect(screen.getByText('Task 1')).toBeInTheDocument()
    expect(screen.getByText('Task 2')).toBeInTheDocument()
    expect(screen.getAllByText(/Status:/)).toHaveLength(2)
    expect(screen.getByText('pending')).toBeInTheDocument()
  })

  it('calls onDelete when delete button is clicked', () => {
    const onDelete = vi.fn()
    render(<TaskList tasks={tasks} onDelete={onDelete} />)
    
    const deleteButtons = screen.getAllByText('Delete')
    fireEvent.click(deleteButtons[0])
    
    expect(onDelete).toHaveBeenCalledWith(1)
  })

  it('renders no tasks message when list is empty', () => {
    render(<TaskList tasks={[]} onDelete={() => {}} />)
    expect(screen.getByText('No tasks found.')).toBeInTheDocument()
  })
})
