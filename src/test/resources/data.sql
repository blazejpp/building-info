create table building (
  id bigint not null auto_increment,
  address varchar(255),
  name varchar(255),
  primary key (id)
);

CREATE TABLE floor (
	id bigint not null auto_increment,
	number bigint not null,
	building_id bigint not null,
	primary key (id)
);

CREATE TABLE room (
	id bigint not null auto_increment,
	floor_id bigint not null,
	height decimal(10,0) not null,
	floor_area decimal(10,0) not null,
	number bigint not null,
	lighting_power decimal(10,0) not null,
	heating_power decimal(10,0) not null,
	primary key (id)
);

insert into building(address, name)
            values  ('Średnia 45', 'Gimnazjum im. Jana Węglarza'),
                    ('Berdychowo 21', 'Sklep monopolowy Radość'),
                    ('Krótka 37', 'Synagoga');

insert into floor(number, building_id)
            values  (1, 1),
                    (2, 1),
                    (3, 1),
                    (1, 2),
                    (1, 3),
                    (2, 3),
                    (3, 3),
                    (4, 3),
                    (5, 3);

insert into room (id, floor_id, height, floor_area, number, lighting_power, heating_power)
            values  (1, 1, 3, 10, 1, 11, 2802),
                    (2, 1, 3, 10, 2, 12, 2803),
                    (3, 1, 3, 10, 3, 13, 2804),
                    (4, 2, 3, 10, 101, 82, 3593),
                    (5, 2, 3, 10, 102, 83, 3594),
                    (6, 2, 3, 10, 103, 84, 3595),
                    (7, 3, 3, 10, 201, 153, 4384),
                    (8, 3, 3, 10, 202, 154, 4385),
                    (9, 3, 3, 10, 203, 154, 4385),
                    (10, 4, 3, 40, 1, 44, 19605),
                    (11, 4, 3, 50, 2, 54, 24505),
                    (12, 5, 12, 200, 0, 204, 238005),
                    (13, 6, 3, 30, 1, 35, 18906),
                    (14, 6, 3, 45, 2, 51, 28357),
                    (15, 6, 3, 20, 3, 26, 12608),
                    (16, 7, 3, 40, 4, 48, 28010),
                    (17, 8, 3, 30, 5, 39, 23112),
                    (18, 9, 3, 20, 6, 31, 16814);