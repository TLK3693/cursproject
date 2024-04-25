-- Database: ecommjava

-- DROP DATABASE IF EXISTS ecommjava;

CREATE DATABASE ecommjava
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
-- Создание таблицы категорий
CREATE TABLE IF NOT EXISTS category (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NULL
);

-- Вставка категорий по умолчанию
INSERT INTO category (name) VALUES
    ('Фрукты'),
    ('Овощи'),
    ('Мясо'),
    ('Рыба'),
    ('Молочные продукты'),
    ('Хлебобулочные изделия'),
    ('Напитки'),
    ('Сладости'),
    ('Другое');

-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    address VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    role VARCHAR(255) NULL,
    username VARCHAR(255) NULL,
    UNIQUE (username)
);

-- Вставка пользователей по умолчанию
INSERT INTO customer (address, email, password, role, username) VALUES
    ('123, Большая Садовая', 'admin@nyan.cat', '123', 'ROLE_ADMIN', 'admin'),
    ('765, Стачки', 'lisa@gmail.com', '765', 'ROLE_NORMAL', 'lisa');

-- Создание таблицы продуктов
CREATE TABLE IF NOT EXISTS product (
    product_id SERIAL PRIMARY KEY,
    description VARCHAR(255) NULL,
    image VARCHAR(255) NULL,
    name VARCHAR(255) NULL,
    price INT NULL,
    quantity INT NULL,
    discount INT NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    category_id INT NULL,
    customer_id INT NULL,
    FOREIGN KEY (category_id) REFERENCES category(category_id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Вставка продуктов по умолчанию
INSERT INTO product (description, image, name, price, quantity, discount, startDate, endDate, category_id) VALUES
    ('Свежий и сочный', 'https://freepngimg.com/save/9557-apple-fruit-transparent/744x744', 'Яблоко', 30, 40, 6, '2024-04-01', '2024-04-30', 1),
    ('Яйца', 'https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png', 'Яйца', 121, 90, 4, '2024-04-05', '2024-04-15', 9),
    ('Хрустящая корочка и вкус', 'https://www.nicepng.com/png/detail/268-2688633_pin-by-arkadiusz-kufel-on-maski-.png', 'Клубника', 300, 30, 8, '2024-04-10', '2024-04-20', 1),
    ('...', 'https://www.nicepng.com/png/detail/91-918572_zz-4934426h9bi80a-.png', 'Огурцы', 60, 50, 5, '2024-04-01', '2024-04-20', 2),
    ('Нежная и сочная', 'https://www.nicepng.com/png/detail/67-676751_raw-meat-png-clipart-meat-transparent-background.png', 'Говядина', 350, 20, 10, '2024-04-15', '2024-05-01', 3),
    ('Вкусная и питательная', 'https://www.nicepng.com/png/detail/85-851658_striped-bass-bass-fishing-tips-best-fishing-fly.png', 'Семга', 276, 15, 7, '2024-04-01', '2024-06-10', 4),
    ('Насыщенный', 'https://www.nicepng.com/png/detail/1006-10065711_lactantia-lactose-free-skim-milk-lactose-free-milk.png', 'Молоко', 60, 100, 0, '2024-05-11', '2024-04-22', 5);
