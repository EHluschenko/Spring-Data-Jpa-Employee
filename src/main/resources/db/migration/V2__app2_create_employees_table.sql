CREATE TABLE IF NOT EXISTS employees (
      Id SERIAL PRIMARY KEY,
       first_name VARCHAR(255) NOT NULL,
       last_name VARCHAR(255) NOT NULL,
       post VARCHAR(255) NOT NULL,
      number_phone VARCHAR(255) NOT NULL);
