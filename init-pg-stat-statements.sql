-- Create extension in the application database
CREATE EXTENSION IF NOT EXISTS pg_stat_statements;

-- Grant necessary permissions (using default postgres user)
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO postgres;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO postgres;

-- Set search path
ALTER DATABASE patika_airport_reservation_system_db 
SET search_path TO "\$user", public, pg_catalog;
