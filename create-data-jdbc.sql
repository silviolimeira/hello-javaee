CREATE TABLE public sl_call (
  id SERIAL PRIMARY KEY,
  issue VARCHAR (50) UNIQUE NOT NULL,
  message VARCHAR (50) UNIQUE NOT NULL,
  status VARCHAR (50) UNIQUE NOT NULL
);




