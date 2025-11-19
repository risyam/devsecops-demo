-- Insert Users with UUID v4
-- Note: In real deployment, users will be created via OAuth2 login
-- These are sample users for testing purposes
-- All users have USER role

-- Sample Users (using UUID v4 as primary keys)
INSERT INTO users (id, email, name, role, active, created_at, updated_at, google_id) VALUES ('550e8400-e29b-41d4-a716-446655440001', 'user1@expense.com', 'John Doe', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'google-user1-123');

INSERT INTO users (id, email, name, role, active, created_at, updated_at, google_id) VALUES ('550e8400-e29b-41d4-a716-446655440002', 'user2@expense.com', 'Jane Smith', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'google-user2-123');

INSERT INTO users (id, email, name, role, active, created_at, updated_at, google_id) VALUES ('550e8400-e29b-41d4-a716-446655440003', 'user3@expense.com', 'Alice Johnson', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'google-user3-123');

INSERT INTO users (id, email, name, role, active, created_at, updated_at, google_id) VALUES ('550e8400-e29b-41d4-a716-446655440004', 'user4@expense.com', 'Bob Brown', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'google-user4-123');

-- Insert Expense Categories (each user has their own categories)
-- User 1's categories
INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440001', 'Food', '550e8400-e29b-41d4-a716-446655440001');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440002', 'Transport', '550e8400-e29b-41d4-a716-446655440001');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440003', 'Utilities', '550e8400-e29b-41d4-a716-446655440001');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440004', 'Housing', '550e8400-e29b-41d4-a716-446655440001');

-- User 2's categories
INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440011', 'Food', '550e8400-e29b-41d4-a716-446655440002');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440012', 'Transport', '550e8400-e29b-41d4-a716-446655440002');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440013', 'Insurance', '550e8400-e29b-41d4-a716-446655440002');

-- User 3's categories
INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440021', 'Food', '550e8400-e29b-41d4-a716-446655440003');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440022', 'Transport', '550e8400-e29b-41d4-a716-446655440003');

-- User 4's categories
INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440031', 'Food', '550e8400-e29b-41d4-a716-446655440004');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440032', 'Transport', '550e8400-e29b-41d4-a716-446655440004');

INSERT INTO expense_category (id, name, user_id) VALUES ('750e8400-e29b-41d4-a716-446655440033', 'Entertainment', '550e8400-e29b-41d4-a716-446655440004');

-- Insert Expenses (using UUID v4)
-- User 1's expenses
INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440001', 'Office supplies', 250.00, '2024-10-01', '750e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440002', 'Office rent', 2000.00, '2024-10-01', '750e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440003', 'Electricity bill', 150.00, '2024-10-02', '750e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- User 2's expenses
INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440011', 'Team lunch', 150.00, '2024-10-02', '750e8400-e29b-41d4-a716-446655440011', '550e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440012', 'Conference travel', 500.00, '2024-10-03', '750e8400-e29b-41d4-a716-446655440012', '550e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440013', 'Health insurance premium', 300.00, '2024-10-01', '750e8400-e29b-41d4-a716-446655440013', '550e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- User 3's expenses
INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440021', 'Client lunch', 75.00, '2024-10-05', '750e8400-e29b-41d4-a716-446655440021', '550e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440022', 'Taxi to meeting', 25.00, '2024-10-06', '750e8400-e29b-41d4-a716-446655440022', '550e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440023', 'Office snacks', 45.00, '2024-10-15', '750e8400-e29b-41d4-a716-446655440021', '550e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- User 4's expenses
INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440031', 'Parking fees', 20.00, '2024-10-07', '750e8400-e29b-41d4-a716-446655440032', '550e8400-e29b-41d4-a716-446655440004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440032', 'Team celebration', 100.00, '2024-10-08', '750e8400-e29b-41d4-a716-446655440033', '550e8400-e29b-41d4-a716-446655440004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expense (id, description, amount, date, category_id, user_id, created_at, updated_at) VALUES ('850e8400-e29b-41d4-a716-446655440033', 'Coffee meeting', 35.00, '2024-10-12', '750e8400-e29b-41d4-a716-446655440031', '550e8400-e29b-41d4-a716-446655440004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
