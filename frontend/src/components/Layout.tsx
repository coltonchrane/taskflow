import React, { useEffect, useState } from 'react'
import { Outlet } from 'react-router-dom'
import Navigation from './Navigation'
import { User } from '../types'

const Layout: React.FC = () => {
  const [user, setUser] = useState<User | null>(null)

  useEffect(() => {
    const savedUser = localStorage.getItem('taskflow_user')
    if (savedUser) {
      setUser(JSON.parse(savedUser))
    }
  }, [])

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden">
      {/* Sidebar */}
      <aside className="w-64 bg-white border-r border-gray-200 hidden md:flex flex-col">
        <div className="p-6 border-b border-gray-100">
          <h1 className="text-2xl font-bold text-blue-600 tracking-tight">TaskFlow</h1>
        </div>
        <div className="flex-1 overflow-y-auto p-4">
          <Navigation />
        </div>
        <div className="p-4 border-t border-gray-100 bg-gray-50">
          <div className="flex items-center">
            <div className="h-10 w-10 rounded-full bg-blue-600 flex items-center justify-center text-white text-sm font-bold shadow-sm">
              {user ? user.username[0].toUpperCase() : '?'}
            </div>
            <div className="ml-3 overflow-hidden">
              <p className="text-sm font-bold text-gray-900 truncate">{user ? user.username : 'Guest'}</p>
              <p className="text-[10px] text-gray-500 uppercase tracking-wider font-semibold">
                {user ? 'Logged In' : 'No User Selected'}
              </p>
            </div>
          </div>
        </div>
      </aside>

      {/* Main Content */}
      <div className="flex-1 flex flex-col min-w-0 overflow-hidden">
        <header className="bg-white border-b border-gray-200 h-16 flex items-center px-6 md:hidden">
           <h1 className="text-xl font-bold text-blue-600">TaskFlow</h1>
        </header>
        
        <main className="flex-1 overflow-y-auto p-6 lg:p-10">
          <div className="max-w-6xl mx-auto">
            {!user && window.location.pathname !== '/admin' && (
               <div className="mb-6 bg-amber-50 border border-amber-200 p-4 rounded-lg flex items-center justify-between">
                  <span className="text-amber-800 text-sm font-medium">You are currently in guest mode. Visit the Admin page to select a user.</span>
                  <a href="/admin" className="text-amber-900 text-sm font-bold underline hover:no-underline">Go to Admin →</a>
               </div>
            )}
            <Outlet />
          </div>
        </main>
      </div>
    </div>
  );
};

export default Layout;
