import { render, screen, waitFor } from '@testing-library/react'
import { describe, it, expect, vi, beforeEach } from 'vitest'
import { MemoryRouter, Route, Routes } from 'react-router-dom'
import ProjectView from './ProjectView'
import { api } from '../api/client'

// Mock the API client
vi.mock('../api/client', () => ({
  api: {
    get: vi.fn(),
    post: vi.fn(),
    delete: vi.fn()
  }
}))

describe('ProjectView', () => {
  const mockProject = {
    id: 1,
    name: 'Test Project',
    description: 'Test Description',
    createdAt: new Date().toISOString()
  }

  const mockTasks = [
    { 
      id: 1, 
      projectId: 1, 
      title: 'Task 1', 
      status: 'pending', 
      priority: 0,
      labels: [],
      comments: [],
      attachments: [],
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    }
  ]

  beforeEach(() => {
    vi.resetAllMocks()
  })

  it('renders project details and tasks', async () => {
    vi.mocked(api.get).mockImplementation((path: string) => {
      if (path.includes('/projects/1')) return Promise.resolve(mockProject)
      if (path.includes('/tasks?projectId=1')) return Promise.resolve(mockTasks)
      return Promise.reject(new Error('Unknown path'))
    })

    render(
      <MemoryRouter initialEntries={['/project/1']}>
        <Routes>
          <Route path="/project/:id" element={<ProjectView />} />
        </Routes>
      </MemoryRouter>
    )

    // Wait for loading to finish
    await waitFor(() => {
      expect(screen.queryByText(/Loading project.../i)).not.toBeInTheDocument()
    })

    // Should find the project name in both breadcrumbs and header
    const titleElements = screen.getAllByText('Test Project')
    expect(titleElements.length).toBeGreaterThanOrEqual(1)
    
    expect(screen.getByText('Test Description')).toBeInTheDocument()
    expect(screen.getByText('Task 1')).toBeInTheDocument()
    
    // Verify API calls
    expect(api.get).toHaveBeenCalledWith('/projects/1')
    expect(api.get).toHaveBeenCalledWith('/tasks?projectId=1')
  })

  it('renders not found when project does not exist', async () => {
    // Suppress console.error for expected failure in test
    const consoleSpy = vi.spyOn(console, 'error').mockImplementation(() => {})
    
    vi.mocked(api.get).mockImplementation(() => Promise.reject(new Error('Not Found')))

    render(
      <MemoryRouter initialEntries={['/project/999']}>
        <Routes>
          <Route path="/project/:id" element={<ProjectView />} />
        </Routes>
      </MemoryRouter>
    )

    await waitFor(() => {
      expect(screen.getByText(/Project not found/i)).toBeInTheDocument()
    })
    
    consoleSpy.mockRestore()
  })
})
