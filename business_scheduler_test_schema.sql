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
    rating_value int not null,
    constraint max_value_constraint check (rating_value <= 5),
	constraint fk_rating_app_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_rating_business_id
        foreign key (business_id)
        references business(business_id)
);

create table business_hours (
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
    constraint fk_business_hours_business_id
        foreign key (business_id)
        references business(business_id)
);

create table timeslot (
	timeslot_id int primary key auto_increment,
	timeslot time not null
);

create table service (
	service_id int primary key auto_increment,
    service_name varchar(100) not null,
    business_id int not null,
    service_length int not null,
    downtime int,
    total_service_length int as (service_length + downtime),
    cost decimal(10, 2) not null,
    constraint fk_business_business_id
        foreign key (business_id)
        references business(business_id)
);

create table appointment (
	appointment_id int primary key auto_increment,
	`date` date not null,
    timeslot_id int not null,
    service_id int not null,
    business_id int not null,
    customer_id int not null,
    constraint fk_timeslot_service_timeslot_id
        foreign key (timeslot_id)
        references timeslot(timeslot_id),
	constraint fk_timeslot_service_service_id
        foreign key (service_id)
        references service(service_id),
	constraint fk_timeslot_service_business_id
        foreign key (business_id)
        references business(business_id),
	constraint fk_timeslot_service_app_user_id
        foreign key (customer_id)
        references app_user(app_user_id)
);

create table notification (
	sender_id int not null,
    reciever_id int not null,
    message varchar(250) not null,
    constraint fk_notification_sender_id
        foreign key (sender_id)
        references app_user(app_user_id),
	constraint fk_notification_reciever_id
        foreign key (reciever_id)
        references app_user(app_user_id)
);

insert into timeslot (timeslot)
values
    ('00:00:00'),
    ('00:15:00'),
    ('00:30:00'),
    ('00:45:00'),
    ('01:00:00'),
    ('01:15:00'),
    ('01:30:00'),
    ('01:45:00'),
    ('02:00:00'),
    ('02:15:00'),
    ('02:30:00'),
    ('02:45:00'),
    ('03:00:00'),
    ('03:15:00'),
    ('03:30:00'),
    ('03:45:00'),
    ('04:00:00'),
    ('04:15:00'),
    ('04:30:00'),
    ('04:45:00'),
    ('05:00:00'),
    ('05:15:00'),
    ('05:30:00'),
    ('05:45:00'),
    ('06:00:00'),
    ('06:15:00'),
    ('06:30:00'),
    ('06:45:00'),
    ('07:00:00'),
    ('07:15:00'),
    ('07:30:00'),
    ('07:45:00'),
    ('08:00:00'),
    ('08:15:00'),
    ('08:30:00'),
    ('08:45:00'),
    ('09:00:00'),
    ('09:15:00'),
    ('09:30:00'),
    ('09:45:00'),
    ('10:00:00'),
    ('10:15:00'),
    ('10:30:00'),
    ('10:45:00'),
    ('11:00:00'),
    ('11:15:00'),
    ('11:30:00'),
    ('11:45:00'),
    ('12:00:00'),
    ('12:15:00'),
    ('12:30:00'),
    ('12:45:00'),
    ('13:00:00'),
    ('13:15:00'),
    ('13:30:00'),
    ('13:45:00'),
    ('14:00:00'),
    ('14:15:00'),
    ('14:30:00'),
    ('14:45:00'),
    ('15:00:00'),
    ('15:15:00'),
    ('15:30:00'),
    ('15:45:00'),
    ('16:00:00'),
    ('16:15:00'),
    ('16:30:00'),
    ('16:45:00'),
    ('17:00:00'),
    ('17:15:00'),
    ('17:30:00'),
    ('17:45:00'),
    ('18:00:00'),
    ('18:15:00'),
    ('18:30:00'),
    ('18:45:00'),
    ('19:00:00'),
    ('19:15:00'),
    ('19:30:00'),
    ('19:45:00'),
    ('20:00:00'),
    ('20:15:00'),
    ('20:30:00'),
    ('20:45:00'),
    ('21:00:00'),
    ('21:15:00'),
    ('21:30:00'),
    ('21:45:00'),
    ('22:00:00'),
    ('22:15:00'),
    ('22:30:00'),
    ('22:45:00'),
    ('23:00:00'),
    ('23:15:00'),
    ('23:30:00'),
    ('23:45:00');
    
delimiter //

create procedure set_known_good_state()
begin

	delete from appointment;
	alter table appointment auto_increment = 1;
	delete from service;
	alter table service auto_increment = 1;
	delete from rating;
	alter table rating auto_increment = 1;
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

insert into app_user (username, password_hash, enabled) values
    ('user1', 'hash1', 1),
    ('user2', 'hash2', 1),
    ('user3', 'hash3', 1);

insert into app_role (name) values
    ('ROLE_ADMIN'),
    ('ROLE_USER'),
    ('ROLE_BUSINESS_OWNER');

insert into app_user_role (app_user_id, app_role_id) values
    (1, 1),
    (2, 2),
    (3, 3);
    
insert into notification (sender_id, reciever_id, message) values
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

insert into service (service_name, business_id, service_length, downtime, cost) values
    ('Service A', 1, 60, 10, 50.00),
    ('Service B', 1, 45, 5, 35.00),
    ('Dish 1', 2, 30, 0, 20.00),
    ('Dish 2', 2, 40, 0, 25.00),
    ('Cleaning', 3, 120, 10, 80.00);
    
INSERT INTO appointment (`date`, timeslot_id, service_id, business_id, customer_id)
VALUES
    ('2023-10-11', 1, 1, 1, 2);

    
end //

delimiter ;
