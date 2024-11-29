create table departments (
	department_id int primary key,
	name varchar(100) not null,
	capacity int not null
);

create table employees (
	employee_id int primary key,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(100) unique not null,
	title varchar(500),
	photograph_path varchar(500),
	department int,
	password varchar(255) not null,
	foreign key (department) references departments(department_id)
);

create table employee_salary (
	id int primary key,
	employee_id int,
	payment_date date not null,
	amount decimal(10, 2) not null,
	description varchar(1000),
	foreign key (employee_id) references employees (employee_id)
);