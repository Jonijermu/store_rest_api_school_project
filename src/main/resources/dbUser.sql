-- Use the project database
USE store;

-- Create the database user if it doesn't exist
CREATE USER IF NOT EXISTS 'dbuser'@'localhost' IDENTIFIED BY 'store';

-- Grant necessary privileges
GRANT SELECT, INSERT, UPDATE, DELETE, ALTER, CREATE, DROP, INDEX
      ON `store`.* TO 'dbuser'@'localhost';

-- Apply changes
FLUSH PRIVILEGES;