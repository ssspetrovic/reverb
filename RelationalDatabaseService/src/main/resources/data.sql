-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Carol', 'Cole', 'donna16@example.org', '444-987-9456x677', '633 Joshua Vista Apt. 196', 'Lake Jessicamouth', 'NV', '04093', 'Christmas Island', '1992-09-16');
--
-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Brian', 'Morris', 'albert00@example.org', '+1-644-602-0576x55338', '790 Perez Courts', 'Copelandland', 'ID', '61719', 'Mali', '1955-11-06');
--
-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Robert', 'Atkins', 'wardangela@example.org', '4403016109', '00921 Michael Unions', 'New Laurenport', 'ID', '80492', 'Luxembourg', '1991-02-11');
--
-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Adam', 'Lawrence', 'joshuasanchez@example.org', '327.895.6912x024', '447 Alicia View Apt. 486', 'Danielleville', 'PR', '64578', 'Libyan Arab Jamahiriya', '1991-11-15');
--
-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Mary', 'Salazar', 'sperez@example.com', '921.520.7941', '8450 Andrews Mount', 'New Robert', 'WI', '51370', 'Swaziland', '1973-06-07');
--
-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Thomas', 'Stevens', 'lisawilliams@example.org', '001-408-612-8189x593', '74912 Miguel Crescent', 'East Kenneth', 'MN', '09403', 'British Virgin Islands', '1977-07-03');
--
-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Benjamin', 'Zuniga', 'robertharris@example.com', '887-490-9122', '957 Anderson Lake Suite 348', 'Sandersmouth', 'TN', '10261', 'Norfolk Island', '1997-05-26');
--
-- INSERT INTO users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
-- VALUES ('Carolyn', 'Mendoza', 'smithbrianna@example.net', '6082582962', '15192 Lewis Land', 'Laurafurt', 'FM', '55021', 'Kiribati', '2003-03-20');

COPY users (first_name, last_name, email, phone_number, address, city, state, zip_code, country, date_of_birth)
    FROM '/tmp/users.csv'
    DELIMITER ','
    CSV HEADER;