USE kodilla_course;

INSERT INTO USERS(email, password_hash, first_name, last_name) VALUES
('john.smith@example.com',   'hash_placeholder_1', 'John',    'Smith'),
('curtis.wilson@example.com','hash_placeholder_2', 'Curtis',  'Wilson'),
('cathy.booker@example.com', 'hash_placeholder_3', 'Cathy',   'Booker'),
('marissa.cain@example.com', 'hash_placeholder_4', 'Marissa', 'Cain'),
('muriel.fulton@example.com','hash_placeholder_5', 'Muriel',  'Fulton');

INSERT INTO READERS(user_id, library_card_number) VALUES
(1, 'LC0001'),
(2, 'LC0002'),
(3, 'LC0003'),
(4, 'LC0004'),
(5, 'LC0005');

INSERT INTO BOOKS(title, author, published_year) VALUES
('The Stranger',              'Albert Camus',              1942),
('In Search of Lost Time',    'Marcel Proust',              1927),
('The Trial',                 'Franz Kafka',                1925),
('The Little Prince',         'Antoine de Saint-Exupery',   1943),
("Man's Fate",                'Andre Malraux',              1933);

INSERT INTO RENTS(reader_id, book_id, rent_date, due_date, return_date, status) VALUES
(1, 1, DATE_SUB(CURDATE(), INTERVAL 10 DAY), DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 10 DAY), INTERVAL 14 DAY), NULL,                                 'active'),
(1, 2, DATE_SUB(CURDATE(), INTERVAL 10 DAY), DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 10 DAY), INTERVAL 14 DAY), NULL,                                 'active'),
(1, 4, DATE_SUB(CURDATE(), INTERVAL 10 DAY), DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 10 DAY), INTERVAL 14 DAY), DATE_SUB(CURDATE(), INTERVAL 5 DAY),  'returned'),
(3, 1, DATE_SUB(CURDATE(), INTERVAL 8 DAY),  DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 8 DAY),  INTERVAL 14 DAY), NULL,                                 'active'),
(3, 5, DATE_SUB(CURDATE(), INTERVAL 4 DAY),  DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 4 DAY),  INTERVAL 14 DAY), DATE_SUB(CURDATE(), INTERVAL 2 DAY),  'returned'),
(4, 5, DATE_SUB(CURDATE(), INTERVAL 10 DAY), DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 10 DAY), INTERVAL 14 DAY), DATE_SUB(CURDATE(), INTERVAL 8 DAY),  'returned'),
(5, 5, DATE_SUB(CURDATE(), INTERVAL 8 DAY),  DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 8 DAY),  INTERVAL 14 DAY), NULL,                                 'active');

DROP PROCEDURE IF EXISTS GetReaderName;

DELIMITER $$

CREATE PROCEDURE GetReaderName(readerid INT)
BEGIN
   IF readerid <= 0 THEN
      SELECT 'Incorrect ID' AS MESSAGE;
   ELSE
      SELECT CONCAT(u.first_name, ' ', u.last_name) AS READER_NAME
      FROM READERS r
      JOIN USERS u ON r.user_id = u.user_id
      WHERE r.reader_id = readerid;
   END IF;
END $$

DELIMITER ;

ALTER TABLE BOOKS ADD BESTSELLER BOOLEAN DEFAULT 0;

DROP PROCEDURE IF EXISTS UpdateBestsellers;

DELIMITER $$

CREATE PROCEDURE UpdateBestsellers()
BEGIN
   DECLARE BOOKSRENTED, DAYS, BK_ID INT;
   DECLARE RENTSPERMONTH DECIMAL(5,2);
   DECLARE FINISHED INT DEFAULT 0;
   DECLARE ALL_BOOKS CURSOR FOR SELECT book_id FROM BOOKS;
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET FINISHED = 1;

   OPEN ALL_BOOKS;
   WHILE (FINISHED = 0) DO
      FETCH ALL_BOOKS INTO BK_ID;
      IF (FINISHED = 0) THEN
         SELECT COUNT(*) FROM RENTS
            WHERE book_id = BK_ID
               INTO BOOKSRENTED;

         SELECT DATEDIFF(MAX(rent_date), MIN(rent_date)) + 1 FROM RENTS
            WHERE book_id = BK_ID
               INTO DAYS;

         IF (BOOKSRENTED > 0 AND DAYS > 0) THEN
            SET RENTSPERMONTH = BOOKSRENTED / DAYS * 30;
         ELSE
            SET RENTSPERMONTH = 0;
         END IF;

         UPDATE BOOKS SET BESTSELLER = (RENTSPERMONTH > 2)
            WHERE book_id = BK_ID;
         COMMIT;
      END IF;
   END WHILE;

   CLOSE ALL_BOOKS;
END $$

DELIMITER ;
