use business_scheduler;

insert into app_user (username, password_hash, enabled)
		values
	('Gian@email.com', '$2a$10$WUOrbvCW6CCXJu7kfhrvq.GW88WPLjGet0M6l9SxuEoASxt69fD/2', 1),
    ('Coco@email.com', '$2a$10$WUOrbvCW6CCXJu7kfhrvq.GW88WPLjGet0M6l9SxuEoASxt69fD/2', 1),
    ('Alyssa@email.com', '$2a$10$WUOrbvCW6CCXJu7kfhrvq.GW88WPLjGet0M6l9SxuEoASxt69fD/2', 1),
    ('Zoe@email.com', '$2a$10$WUOrbvCW6CCXJu7kfhrvq.GW88WPLjGet0M6l9SxuEoASxt69fD/2', 1),
    ('Lux@email.com', '$2a$10$WUOrbvCW6CCXJu7kfhrvq.GW88WPLjGet0M6l9SxuEoASxt69fD/2', 1),
    ('Adrian@email.com', '$2a$10$WUOrbvCW6CCXJu7kfhrvq.GW88WPLjGet0M6l9SxuEoASxt69fD/2', 1),
    ('Gabriel@email.com', '$2a$10$WUOrbvCW6CCXJu7kfhrvq.GW88WPLjGet0M6l9SxuEoASxt69fD/2', 1);


insert into notification (sender_id, receiver_id, message) values
    (2, 1, "Sorry, I'm sick"),
    (3, 1, "I don't like you"),
    (4, 5, 'Family member emergency'),
    (7, 1, 'I had a flat tire'),
    (1, 2, "I'm in the hospital");

insert into business (business_name, owner_id, category_id) values
	('Dental Art Studios', 2, 1),
	("Chop Shop", 3, 2),
	("Inked", 4, 3),
	("Classy Cut Dog Grooming", 5, 4),
	("Broom Service", 6, 5),
	("Joe's Italian Restaurant", 7, 6);
    
insert into rating (app_user_id, business_id, rating_value) values
	(1, 1, 1),
    (2, 1, 5);

insert into service (service_name, business_id, total_service_length, cost) values
    ('Routine Cleaning', 1, 30, 50.00),
	('Deep Cleaning', 1, 60, 100.00),
    ('Root Canal', 1, 45, 125.00),
    
	("Men's Haircut", 2, 45, 25.00),
    ("Women's Haircut", 2, 50, 25.00),
    ('Eyebrow Waxing', 2, 5, 10.00),
    
    ("Large Tattoo", 3, 300, 500.00),
    ("Medium Tattoo", 3, 150, 250.00),
    ('Small Tattoo', 3, 45, 100.00),
    
    ("Doggy Cleaning", 4, 45, 50.00),
    ("Puppy Haircut", 4, 45, 60.00),
    
    ("Surface Cleaning", 5, 120, 125.00),
    ("Deep Cleaning", 5, 240, 300.00),
    ('Sanatizing Cleaning', 5, 360, 500.00),
    
    ('Meal Reservation', 6, 60, 20.00),
    ('Party Room Reservation', 6, 150, 100.00);
    
INSERT INTO appointment (date_time, service_id, business_id, customer_id)
VALUES
	('2023-10-12 16:00:00', 10, 4, 1),
    ('2023-10-12 16:35:00', 4, 2, 1),
    ('2023-10-11 15:30:00', 3, 1, 1),
	('2023-10-11 15:30:00', 6, 2, 1),
	('2023-10-12 16:35:00', 4, 2, 2),
    ('2023-10-11 15:30:00', 2, 6, 3),
	('2023-10-11 15:30:00', 6, 2, 4),
    ('2023-10-12 16:35:00', 3, 1, 5),
	('2023-10-12 16:35:00', 5, 2, 6),
    ('2023-10-11 15:30:00', 3, 1, 7),
	('2023-10-11 15:30:00', 6, 2, 1),
    ('2023-10-12 16:35:00', 15, 6, 2),
	('2023-10-12 16:35:00', 5, 2, 3),
    ('2023-10-11 15:30:00', 1, 1, 4),
	('2023-10-11 15:30:00', 4, 2, 5),
    ('2023-10-12 16:35:00', 1, 1, 6);
    
INSERT INTO app_user_role (app_user_id, app_role_id)
VALUES
	(1, 1),
	(2, 1),
    (3, 1),
	(4, 1),
    (5, 1),
	(6, 1),
    (7, 1),
	(2, 2),
    (3, 2),
	(4, 2),
    (5, 2),
	(6, 2),
    (7, 2);
    
INSERT INTO availability (business_id, monday_start, monday_end, monday_break_start, monday_break_end,
                         tuesday_start, tuesday_end, tuesday_break_start, tuesday_break_end,
                         wednesday_start, wednesday_end, wednesday_break_start, wednesday_break_end,
                         thursday_start, thursday_end, thursday_break_start, thursday_break_end,
                         friday_start, friday_end, friday_break_start, friday_break_end,
                         saturday_start, saturday_end, saturday_break_start, saturday_break_end,
                         sunday_start, sunday_end, sunday_break_start, sunday_break_end)
VALUES
	(1, '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:30:00', '17:30:00', '12:30:00', '13:30:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00'),
	(2, '09:00:00', '18:00:00', '12:30:00', '13:30:00', '09:30:00', '18:30:00', '13:00:00', '14:00:00', '09:00:00', '18:00:00', '12:30:00', '13:30:00', '09:00:00', '18:00:00', '12:30:00', '13:30:00', '09:00:00', '18:00:00', '12:30:00', '13:30:00', '09:00:00', '18:00:00', '12:30:00', '13:30:00', '09:00:00', '18:00:00', '12:30:00', '13:30:00'),
	(3, '08:30:00', '16:30:00', '12:00:00', '13:00:00', '08:30:00', '17:00:00', '12:30:00', '13:30:00', '08:00:00', '16:00:00', '12:00:00', '13:00:00', '08:00:00', '16:00:00', '12:00:00', '13:00:00', '08:00:00', '16:00:00', '12:00:00', '13:00:00', '08:00:00', '16:00:00', '12:00:00', '13:00:00', '08:00:00', '16:00:00', '12:00:00', '13:00:00'),
	(4, '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:30:00', '17:30:00', '12:30:00', '13:30:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:30:00', '17:30:00', '12:30:00', '13:30:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00', '08:30:00', '17:30:00', '12:30:00', '13:30:00', '08:00:00', '17:00:00', '12:00:00', '13:00:00'),
    (5, '09:00:00', '18:00:00', '13:00:00', '14:00:00', '09:30:00', '18:30:00', '13:30:00', '14:30:00', '09:00:00', '18:00:00', '13:00:00', '14:00:00', '09:30:00', '18:30:00', '13:30:00', '14:30:00', '09:00:00', '18:00:00', '13:00:00', '14:00:00', '09:30:00', '18:30:00', '13:30:00', '14:30:00', '09:00:00', '18:00:00', '13:00:00', '14:00:00'),
    (6, '08:00:00', '16:00:00', '12:30:00', '13:30:00', '08:00:00', '16:00:00', '12:30:00', '13:30:00', '08:00:00', '16:00:00', '12:30:00', '13:30:00', '08:00:00', '16:00:00', '12:30:00', '13:30:00', '08:00:00', '16:00:00', '12:30:00', '13:30:00', '08:00:00', '16:00:00', '12:30:00', '13:30:00', '08:00:00', '16:00:00', '12:30:00', '13:30:00');
    
    
    