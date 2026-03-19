-- V2__Expand_Schema.sql

-- Add columns to tasks
ALTER TABLE tasks ADD COLUMN priority INT DEFAULT 0;
ALTER TABLE tasks ADD COLUMN due_date TIMESTAMP;

-- Create labels table
CREATE TABLE labels (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    color VARCHAR(20) DEFAULT '#000000',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create task_labels many-to-many bridge table
CREATE TABLE task_labels (
    task_id INT NOT NULL,
    label_id INT NOT NULL,
    PRIMARY KEY (task_id, label_id),
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (label_id) REFERENCES labels(id) ON DELETE CASCADE
);

-- Create activity log table
CREATE TABLE task_activity_log (
    id SERIAL PRIMARY KEY,
    task_id INT NOT NULL,
    user_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    old_value TEXT,
    new_value TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Indexes for performance
CREATE INDEX idx_labels_name ON labels(name);
CREATE INDEX idx_task_labels_task ON task_labels(task_id);
CREATE INDEX idx_task_activity_task ON task_activity_log(task_id);
