-- first create two dbs personalExpenseTracker and personalExpenseTrackerTest (for tests) then these 2 tables in each db.

CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS expenses (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    date DATE NOT NULL,
    categoryId int NOT NULL REFERENCES categories(id) ON DELETE RESTRICT
);

