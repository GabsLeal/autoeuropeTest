CREATE TABLE locations (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  inventory JSONB,
  availability JSONB,
  pricing JSONB
);

CREATE TABLE cars (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  brand VARCHAR(255) NOT NULL,
  location_id INT NOT NULL,
  FOREIGN KEY (location_id) REFERENCES locations (id)
);


CREATE TABLE car_availability (
  id SERIAL PRIMARY KEY,
  car_id INT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (car_id) REFERENCES cars (id)
);