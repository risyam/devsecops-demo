-- Populate categories data for testing purpose
INSERT INTO expense_category (name) VALUES ('Food');
INSERT INTO expense_category (name) VALUES ('Education');
INSERT INTO expense_category (name) VALUES ('Entertainment');
INSERT INTO expense_category (name) VALUES ('Transport');
INSERT INTO expense_category (name) VALUES ('Health');
INSERT INTO expense_category (name) VALUES ('Utilities');
INSERT INTO expense_category (name) VALUES ('Medicine');
INSERT INTO expense_category (name) VALUES ('Housing');
INSERT INTO expense_category (name) VALUES ('Insurance');
INSERT INTO expense_category (name) VALUES ('Savings');

-- Populate expenses data for testing purpose 
INSERT INTO expense (description, amount, date, category_id) VALUES ('Lunch', 50.00, '2024-10-01', 1);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Grocery shopping', 120.00, '2024-10-02', 1);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Tuition fee', 200.00, '2024-10-03', 2);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Movie ticket', 15.00, '2024-10-04', 3);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Bus fare', 10.00, '2024-10-05', 4);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Doctor consultation', 60.00, '2024-10-06', 5);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Electricity bill', 80.00, '2024-10-07', 6);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Medicine purchase', 40.00, '2024-10-08', 7);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Rent payment', 1000.00, '2024-10-09', 8);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Car insurance', 120.00, '2024-10-10', 9);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Health insurance', 150.00, '2024-10-11', 9);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Dining out', 45.00, '2024-10-12', 1);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Fitness subscription', 30.00, '2024-10-13', 5);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Concert ticket', 70.00, '2024-10-14', 3);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Gasoline', 50.00, '2024-10-15', 4);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Savings deposit', 200.00, '2024-10-16', 10);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Lunch at cafe', 30.00, '2024-11-01', 1);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Textbook purchase', 75.00, '2024-11-02', 2);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Movie night', 25.00, '2024-11-03', 3);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Taxi ride', 20.00, '2024-11-04', 4);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Wellness checkup', 45.00, '2024-11-05', 5);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Internet bill', 40.00, '2024-11-06', 6);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Antibiotics prescription', 50.00, '2024-11-07', 7);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Mortgage payment', 1500.00, '2024-11-08', 8);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Life insurance', 100.00, '2024-11-09', 9);
INSERT INTO expense (description, amount, date, category_id) VALUES ('Retirement savings', 300.00, '2024-11-10', 10);
