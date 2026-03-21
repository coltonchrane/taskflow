import React from 'react'
import { Task } from '../types'

interface TaskListProps {
  tasks: Task[];
  onDelete: (id: number) => void;
}

const getStatusColor = (status: string) => {
  switch (status.toLowerCase()) {
    case 'completed': return 'bg-green-100 text-green-800'
    case 'in_progress': return 'bg-blue-100 text-blue-800'
    case 'blocked': return 'bg-red-100 text-red-800'
    default: return 'bg-gray-100 text-gray-800'
  }
}

const getPriorityLabel = (priority: number) => {
  if (priority >= 2) return { label: 'High', color: 'text-red-600' }
  if (priority === 1) return { label: 'Medium', color: 'text-orange-600' }
  return { label: 'Low', color: 'text-gray-600' }
}

const TaskList: React.FC<TaskListProps> = ({ tasks, onDelete }) => {
  return (
    <div className="space-y-4">
      <h2 className="text-2xl font-bold text-gray-800">Your Tasks</h2>
      {tasks.length === 0 ? (
        <div className="text-center py-12 bg-white rounded-lg border-2 border-dashed border-gray-300">
          <p className="text-gray-500">No tasks found. Create one to get started!</p>
        </div>
      ) : (
        <div className="grid gap-4 grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          {tasks.map((task) => {
            const { label: priorityLabel, color: priorityColor } = getPriorityLabel(task.priority)
            
            return (
              <div key={task.id} className="bg-white p-5 rounded-lg shadow-sm border border-gray-200 hover:shadow-md transition-shadow">
                <div className="flex justify-between items-start mb-3">
                  <span className={`px-2 py-1 rounded-md text-xs font-medium uppercase tracking-wider ${getStatusColor(task.status)}`}>
                    {task.status.replace('_', ' ')}
                  </span>
                  <button 
                    onClick={() => onDelete(task.id)}
                    className="text-gray-400 hover:text-red-600 transition-colors p-1"
                    title="Delete Task"
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                    </svg>
                  </button>
                </div>
                
                <h3 className="text-lg font-semibold text-gray-900 mb-2 line-clamp-1" title={task.title}>
                  {task.title}
                </h3>
                
                <p className="text-gray-600 text-sm mb-4 line-clamp-2 h-10">
                  {task.description || "No description provided."}
                </p>
                
                <div className="flex justify-between items-center mt-auto pt-4 border-t border-gray-100">
                  <span className={`text-xs font-bold ${priorityColor}`}>
                    {priorityLabel} Priority
                  </span>
                  {task.dueDate && (
                    <span className="text-xs text-gray-500">
                      Due: {new Date(task.dueDate).toLocaleDateString()}
                    </span>
                  )}
                </div>
              </div>
            )
          })}
        </div>
      )}
    </div>
  )
}

export default TaskList
