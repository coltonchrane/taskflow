-- V3__Seed_Data.sql

-- Insert Users
INSERT INTO users (username, email, password) VALUES 
('alice', 'alice@taskflow.com', 'password123'),
('bob', 'bob@taskflow.com', 'securepass'),
('charlie', 'charlie@taskflow.com', 'mypassword');

-- Insert Projects
INSERT INTO projects (name, description, owner_id) VALUES 
('TaskFlow Platform', 'Development of the core TaskFlow management tool.', 1),
('Mobile App Release', 'Finalize the Android and iOS release cycle.', 2),
('Infrastructure Upgrade', 'Migrating to container-orchestrated infrastructure.', 3);

-- Insert Project Members
INSERT INTO project_members (project_id, user_id, role) VALUES 
(1, 1, 'admin'),
(1, 2, 'developer'),
(1, 3, 'viewer'),
(2, 2, 'admin'),
(2, 1, 'developer'),
(3, 3, 'admin');

-- Insert Labels
INSERT INTO labels (name, color) VALUES 
('Bug', '#FF0000'),
('Feature', '#00FF00'),
('Urgent', '#FFA500'),
('Frontend', '#0000FF'),
('Backend', '#800080');

-- Insert Tasks
INSERT INTO tasks (project_id, assigned_to, title, description, status, priority, due_date) VALUES 
(1, 1, 'Fix Login Issue', 'Resolve the timeout in the login flow.', 'in_progress', 3, CURRENT_TIMESTAMP + INTERVAL '2 days'),
(1, 2, 'Implement Task Drag-and-Drop', 'Allow users to reorder tasks manually.', 'pending', 1, CURRENT_TIMESTAMP + INTERVAL '7 days'),
(1, 3, 'Code Review PR #42', 'Review the new API endpoints for task comments.', 'completed', 2, CURRENT_TIMESTAMP - INTERVAL '1 day'),
(2, 2, 'Publish to Play Store', 'Final APK upload and release notes.', 'pending', 3, CURRENT_TIMESTAMP + INTERVAL '3 days'),
(2, 1, 'Bug Bash', 'Organize a team session for bug hunting.', 'pending', 2, CURRENT_TIMESTAMP + INTERVAL '5 days');

-- Insert Task Labels
INSERT INTO task_labels (task_id, label_id) VALUES 
(1, 1), -- Bug
(1, 3), -- Urgent
(2, 2), -- Feature
(2, 4), -- Frontend
(4, 3); -- Urgent

-- Insert Comments
INSERT INTO comments (task_id, user_id, content) VALUES 
(1, 2, 'I noticed this too, happening mostly on Safari.'),
(1, 1, 'I think it’s a session expiry issue. Investigating now.'),
(2, 1, 'We should use dnd-kit for this in React.');

-- Insert Activity Log
INSERT INTO task_activity_log (task_id, user_id, action, old_value, new_value) VALUES 
(1, 1, 'status_change', 'pending', 'in_progress'),
(3, 3, 'status_change', 'in_progress', 'completed');
