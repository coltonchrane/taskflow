export interface User {
  id: number;
  username: string;
  email: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Label {
  id: number;
  name: string;
  color: string;
}

export interface Attachment {
  id: number;
  fileName: string;
  filePath: string;
  fileType: string;
  createdAt: string;
}

export interface Comment {
  id: number;
  content: string;
  author: User;
  createdAt: string;
}

export type TaskStatus = 'pending' | 'in_progress' | 'completed' | 'blocked';

export interface Task {
  id: number;
  projectId: number;
  title: string;
  description?: string;
  status: TaskStatus;
  priority: number;
  dueDate?: string;
  assignedTo?: User;
  labels: Label[];
  comments: Comment[];
  attachments: Attachment[];
  createdAt: string;
  updatedAt: string;
}

export interface Project {
  id: number;
  name: string;
  description?: string;
  owner: User;
  createdAt: string;
  updatedAt: string;
}
