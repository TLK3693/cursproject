# SQL configs
SET SQL_MODE ='IGNORE_SPACE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

# create database and use it
CREATE DATABASE IF NOT EXISTS ecommjava;
USE ecommjava;

# create the category table
CREATE TABLE IF NOT EXISTS CATEGORY(
category_id int unique key not null auto_increment primary key,
name        varchar(255) null
);

# Set auto increment for category_id
# ALTER TABLE CATEGORY MODIFY category_id INT AUTO_INCREMENT;

# insert default categories
INSERT INTO CATEGORY(name) VALUES ('Фрукты'),
                                  ('Овощи'),
                                  ('Мясо'),
                                  ("Рыба"),
                                  ('Молочные продукты'),
                                  ("Хлебобулочные изделия"),
                                  ("Напитки"),
                                  ("Сладости"),
                                  ("Другое");

# create the customer table
CREATE TABLE IF NOT EXISTS CUSTOMER(
id       int unique key not null auto_increment primary key,
address  varchar(255) null,
email    varchar(255) null,
password varchar(255) null,
role     varchar(255) null,
username varchar(255) null,
UNIQUE (username)
);

# insert default customers
INSERT INTO CUSTOMER(address, email, password, role, username) VALUES
                                                                   ('123, Большая Садовая', 'admin@nyan.cat', '123', 'ROLE_ADMIN', 'admin'),
                                                                   ('765, Стачки', 'lisa@gmail.com', '765', 'ROLE_NORMAL', 'lisa');

# create the product table
CREATE TABLE IF NOT EXISTS PRODUCT(
product_id  int unique key not null auto_increment primary key,
description varchar(255) null,
image       varchar(255) null,
name        varchar(255) null,
price       int null,
quantity    int null,
discount    int null,
startDate   date null,
endDate     date null,
category_id int null,
customer_id int null
);

# insert default products
INSERT INTO PRODUCT(description, image, name, price, quantity, discount, startDate, endDate ,category_id) VALUES
                                                                                        ('Свежий и сочный', 'https://freepngimg.com/save/9557-apple-fruit-transparent/744x744', 'Яблоко', 30, 40, 6,'2024-04-01','2024-04-30', 1),
                                                                                        ('Яйца', 'https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png', 'Яйца', 121, 90, 4,'2024-04-05','2024-04-15', 9),
                                                                                        ('Хрустящая корочка и вкус', 'https://www.nicepng.com/png/detail/268-2688633_pin-by-arkadiusz-kufel-on-maski-.png', 'Клубника', 300 , 30, 8, '2024-04-10', '2024-04-20', 1),
                                                                                        ('...', 'https://www.nicepng.com/png/detail/91-918572_zz-4934426h9bi80a-.png', 'Огурцы', 60, 50, 5, '2024-04-01', '2024-04-20', 2),
                                                                                        ('Нежная и сочная', 'https://www.nicepng.com/png/detail/67-676751_raw-meat-png-clipart-meat-transparent-background.png', 'Говядина', 350, 20, 10, '2024-04-15', '2024-05-01', 3),
                                                                                        ('Вкусная и питательная', 'https://www.nicepng.com/png/detail/85-851658_striped-bass-bass-fishing-tips-best-fishing-fly.png', 'Семга', 276, 15, 7, '2024-04-01', '2024-06-10', 4),
                                                                                        ('Насыщенный', 'https://www.nicepng.com/png/detail/1006-10065711_lactantia-lactose-free-skim-milk-lactose-free-milk.png', 'Молоко', 60, 100, 0, '2024-05-11', '2024-04-22', 5);

# create indexes
CREATE INDEX FK7u438kvwr308xcwr4wbx36uiw
    ON PRODUCT (category_id);

CREATE INDEX FKt23apo8r9s2hse1dkt95ig0w5
    ON PRODUCT (customer_id);
