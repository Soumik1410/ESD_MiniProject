insert into departments (department_id, name, capacity) values
(100, 'Faculty', 200),
(101, 'Administration', 100),
(102, 'Security', 50),
(103, 'Health', 20);

insert into employees(employee_id, first_name, last_name, email, title, photograph_path, department, password) values
(10000000, 'Soumik', 'Pal', 'soumik@academics.org', 'Professor', null, 100, '$2a$10$4kA6Njs00Rcj2C/j4pgFq.PH4nzUupRdsfUJjSq8XF4vhVi3PN0UO'),
(10000001, 'Poulami', 'Pal', 'poulami28@academics.org', 'Assistant Professor', null, 100, '$2a$10$4kA6Njs00Rcj2C/j4pgFq.PH4nzUupRdsfUJjSq8XF4vhVi3PN0UO'),
(10000002, 'Tanusri', 'Pal', 'tanusri@academics.org', 'Assistant Professor', null, 100, '$2a$10$4kA6Njs00Rcj2C/j4pgFq.PH4nzUupRdsfUJjSq8XF4vhVi3PN0UO'),
(10000003, 'Prasanta', 'Pal', 'prasanta@academics.org', 'Registrar', null, 101, '$2a$10$4kA6Njs00Rcj2C/j4pgFq.PH4nzUupRdsfUJjSq8XF4vhVi3PN0UO');

insert into employee_salary (id, employee_id, payment_date, amount, description) values
(1000, 10000000, date_sub(curdate(), interval 90 day), 50000.00, null),
(1001, 10000001, date_sub(curdate(), interval 90 day), 40000.00, null),
(1002, 10000002, date_sub(curdate(), interval 90 day), 40000.00, null),
(1003, 10000003, date_sub(curdate(), interval 90 day), 40000.00, null),
(1004, 10000000, date_sub(curdate(), interval 60 day), 75000.00, 'Quarterly Bonus of 25000.00'),
(1005, 10000001, date_sub(curdate(), interval 60 day), 65000.00, 'Quarterly Bonus of 25000.00'),
(1006, 10000002, date_sub(curdate(), interval 60 day), 65000.00, 'Quarterly Bonus of 25000.00'),
(1007, 10000003, date_sub(curdate(), interval 60 day), 65000.00, 'Quarterly Bonus of 25000.00'),
(1008, 10000000, date_sub(curdate(), interval 30 day), 50000.00, null),
(1009, 10000001, date_sub(curdate(), interval 30 day), 40000.00, null),
(1010, 10000002, date_sub(curdate(), interval 30 day), 40000.00, null),
(1011, 10000003, date_sub(curdate(), interval 30 day), 40000.00, null),
(1012, 10000000, date_sub(curdate(), interval 1 day), 60000.00, 'Performance Bonus of 10000.00'),
(1013, 10000001, date_sub(curdate(), interval 1 day), 40000.00, null),
(1014, 10000002, date_sub(curdate(), interval 1 day), 40000.00, null),
(1015, 10000003, date_sub(curdate(), interval 1 day), 40000.00, null);