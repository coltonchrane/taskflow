import React from 'react'

interface Task {
  id: number;
  title: string;
  status: string;
  description?: string;
}

interface TaskListProps {
  tasks: Task[];
  onDelete: (id: number) => void;
}

const TaskList: React.FC<TaskListProps> = ({ tasks, onDelete }) => {
  return (
    <div className="task-list">
      <h2>Tasks</h2>
      {tasks.length === 0 ? (
        <p>No tasks found.</p>
      ) : (
        <div style={{ display: 'grid', gap: '1rem' }}>
          {tasks.map((task) => (
            <div key={task.id} style={{ border: '1px solid #ccc', padding: '1rem', borderRadius: '8px' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                <h3>{task.title}</h3>
                <button onClick={() => onDelete(task.id)} style={{ color: 'red' }}>Delete</button>
              </div>
              <p>{task.description}</p>
              <span>Status: <strong>{task.status}</strong></span>
            </div>
          ))}
        </div>
      )}
    </div>
  )
}

export default TaskList
