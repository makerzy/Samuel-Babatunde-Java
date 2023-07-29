use northwind;

-- 1. What are the categories of products in the database? 
select
  distinct category
from
  products p;

-- 2. What products are made by Dell?
select
  *
from
  products p
where
  product_name like 'dell%';

-- 3. List all the orders shipped to Pennsylvania.
select
  *
from
  orders o
where
  ship_state = 'Pennsylvania';

-- 4. List the first name and last name of all employees with last names that start with the letter W.
select
  e.first_name,
  e.last_name
from
  employees e
where
  e.last_name like 'W%';

-- 5. List all customers from zip codes that start with 55.
select
  *
from
  customers c
where
  c.postal_code like '55%';

-- 6. List all customers from zip codes that end with 0.
select
  *
from
  customers c
where
  c.postal_code like '%0';

-- 7. List the first name, last name, and email for all customers with a ".org" email address.
select
  c.first_name,
  c.last_name,
  c.email
from
  customers c
where
  c.email like '%.org';

-- 8. List the first name, last name, and phone number for all customers from the 202 area code.
select
  c.first_name,
  c.last_name,
  c.phone
from
  customers c
where
  c.phone like '1-(202)%';

-- 9. List the first name, last name, and phone number for all customers from the 202 area code, ordered by last name, first name.
select
  c.first_name,
  c.last_name,
  c.phone
from
  customers c
where
  c.phone like '1-(202)%'
order by
  c.last_name,
  c.first_name asc;