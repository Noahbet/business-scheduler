drop database if exists business_scheduler_test;
create database business_scheduler_test;
use business_scheduler_test;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled bit not null default(1)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

create table business_category (
	category_id int primary key auto_increment,
	category varchar(100) not null
);

create table business (
	business_id int primary key auto_increment,
	business_name varchar(100) not null,
	owner_id int not null,
    category_id int not null,
    constraint fk_business_app_user_id
        foreign key (owner_id)
        references app_user(app_user_id),
	constraint fk_business_category_id
        foreign key (category_id)
        references business_category(category_id)
);

create table rating (
	app_user_id int not null,
    business_id int not null,
    rating_value double not null,
    constraint max_value_constraint check (rating_value <= 5.0),
	constraint fk_rating_app_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_rating_business_id
        foreign key (business_id)
        references business(business_id)
);

create table availability (
	availability_id int primary key auto_increment,
    business_id int not null,
    monday_start time,
    monday_end time,
    monday_break_start time,
    monday_break_end time,
    tuesday_start time,
    tuesday_end time,
    tuesday_break_start time,
    tuesday_break_end time,
    wednesday_start time,
    wednesday_end time,
    wednesday_break_start time,
    wednesday_break_end time,
    thursday_start time,
    thursday_end time,
    thursday_break_start time,
    thursday_break_end time,
    friday_start time,
    friday_end time,
    friday_break_start time,
    friday_break_end time,
    saturday_start time,
    saturday_end time,
    saturday_break_start time,
    saturday_break_end time,
    sunday_start time,
    sunday_end time,
    sunday_break_start time,
    sunday_break_end time,
    constraint fk_availability_business_id
        foreign key (business_id)
        references business(business_id)
);

create table service (
	service_id int primary key auto_increment,
    service_name varchar(100) not null,
    business_id int not null,
    total_service_length int not null,
    cost decimal(10, 2) not null,
    constraint fk_business_business_id
        foreign key (business_id)
        references business(business_id)
);

create table appointment (
	appointment_id int primary key auto_increment,
	date_time datetime not null,
    service_id int not null,
    business_id int not null,
    customer_id int not null,
	constraint fk_appointment_service_service_id
        foreign key (service_id)
        references service(service_id),
	constraint fk_appointment_business_id
        foreign key (business_id)
        references business(business_id),
	constraint fk_appointment_app_user_id
        foreign key (customer_id)
        references app_user(app_user_id)
);

create table notification (
	notification_id int primary key auto_increment,
	sender_id int not null,
    receiver_id int not null,
    message varchar(250) not null,
    constraint fk_notification_sender_id
        foreign key (sender_id)
        references app_user(app_user_id),
	constraint fk_notification_receiver_id
        foreign key (receiver_id)
        references app_user(app_user_id)
);
    
delimiter //

create procedure set_known_good_state()
begin

	delete from appointment;
	alter table appointment auto_increment = 1;
	delete from service;
	alter table service auto_increment = 1;
	delete from rating;
	alter table rating auto_increment = 1;
    delete from availability;
	alter table availability auto_increment = 1;
	delete from business;
	alter table business auto_increment = 1;
    delete from business_category;
	alter table business_category auto_increment = 1;
    delete from notification;
	alter table notification auto_increment = 1;
    delete from app_user_role;
	alter table app_user_role auto_increment = 1;
    delete from app_role;
	alter table app_role auto_increment = 1;
    delete from app_user;
	alter table app_user auto_increment = 1;

insert into app_role (`name`) values
		('TEST_ROLE_1'),
		('TEST_ROLE_2');

	-- passwords are set to "P@ssw0rd!"
	insert into app_user (username, password_hash, enabled)
		values
		('appuser1@app.com', 'password_hash_1', 1),
		('appuser2@app.com', 'password_hash_2', 1),
		('appuser3@app.com', 'password_hash_3', 1);


	insert into app_user_role
		values
		(1, 1),
		(2, 2);
    
insert into notification (sender_id, receiver_id, message) values
    (1, 2, 'Sorry, I sick'),
    (2, 3, 'I in hospital'),
    (3, 2, "I don't like you");

insert into business_category (category) values
    ('RETAIL'),
    ('RESTAURANT'),
    ('SERVICE');

insert into business (business_name, owner_id, category_id) values
    ('Business 1', 1, 1),
    ('Business 2', 2, 2),
    ('Business 3', 3, 3);
    
insert into rating (app_user_id, business_id, rating_value) values
	(1, 1, 1),
    (3, 1, 5),
    (1, 2, 1),
    (1, 3, 1);

insert into service (service_name, business_id, total_service_length, cost) values
    ('Service A', 1, 70, 50.00),
    ('Service B', 1, 50, 35.00),
    ('Dish 1', 2, 30, 20.00),
    ('Dish 2', 2, 40, 25.00),
    ('Cleaning', 3, 130, 80.00);
    
INSERT INTO appointment (date_time, service_id, business_id, customer_id)
VALUES
    ('2023-10-11 15:30:00', 1, 1, 1);

    
end //

delimiter ;
