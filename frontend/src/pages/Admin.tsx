import React, { useEffect, useState } from 'react'
import { api } from '../api/client'
import { User } from '../types'

const Admin: React.FC = () => {
  const [users, setUsers] = useState<User[]>([])
  const [currentUser, setCurrentUser] = useState<User | null>(null)

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const data = await api.get<User[]>('/users')
        setUsers(data)
      } catch (err) {
        console.error('Failed to fetch users', err)
      }
    }

    const savedUser = localStorage.getItem('taskflow_user')
    if (savedUser) {
      setCurrentUser(JSON.parse(savedUser))
    }

    fetchUsers()
  }, [])

  const selectUser = (user: User) => {
    localStorage.setItem('taskflow_user', JSON.stringify(user))
    setCurrentUser(user)
    window.location.reload() // Force reload to update all components with new user context
  }

  return (
    <div className="space-y-8">
      <div className="bg-white p-8 rounded-2xl shadow-sm border border-gray-100">
        <h1 className="text-3xl font-extrabold text-gray-900 mb-3">Admin Panel</h1>
        <p className="text-gray-600">Switch between users to simulate different perspectives.</p>
      </div>

      <div className="bg-white rounded-xl shadow-sm border border-gray-200 overflow-hidden">
        <div className="p-6 border-b border-gray-100 bg-gray-50">
          <h2 className="text-lg font-bold text-gray-900">Available Users</h2>
        </div>
        <div className="divide-y divide-gray-100">
          {users.map((user) => (
            <div key={user.id} className="p-4 flex items-center justify-between hover:bg-gray-50 transition-colors">
              <div className="flex items-center">
                <div className="h-10 w-10 rounded-full bg-blue-100 flex items-center justify-center text-blue-600 font-bold">
                  {user.username[0].toUpperCase()}
                </div>
                <div className="ml-4">
                  <p className="text-sm font-bold text-gray-900">{user.username}</p>
                  <p className="text-xs text-gray-500">{user.email}</p>
                </div>
              </div>
              <button
                onClick={() => selectUser(user)}
                className={`px-4 py-2 rounded-md text-sm font-medium transition-all ${
                  currentUser?.id === user.id
                    ? 'bg-green-100 text-green-700 border border-green-200'
                    : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
                }`}
              >
                {currentUser?.id === user.id ? 'Active User' : 'Switch To'}
              </button>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default Admin
