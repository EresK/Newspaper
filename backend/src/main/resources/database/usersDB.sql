INSERT INTO users (id, email, first_name, last_name)
VALUES (1, 'admin@email.com', 'admin_name', 'admin_surname')
ON CONFLICT DO NOTHING;