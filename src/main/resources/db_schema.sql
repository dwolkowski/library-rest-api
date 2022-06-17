CREATE TABLE Employees
(
    `employee_id`     INT(10) NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(20) NOT NULL,
    `last_name`       VARCHAR(20) NOT NULL,
    `position`        VARCHAR(20) NOT NULL,
    `boss_id`         INT(10) NULL,
    `salary`          INT(10) NOT NULL,
    `employment_date` DATE        NOT NULL,
    `phone_number`    VARCHAR(11) NOT NULL,
    `address`         VARCHAR(60) NOT NULL,
    `email`           VARCHAR(30) NOT NULL,
    PRIMARY KEY (`employee_id`),
    FOREIGN KEY (boss_id) REFERENCES Employees (employee_id)
);

CREATE TABLE Readers
(
    `reader_id`    INT(10) NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(20) NOT NULL,
    `last_name`    VARCHAR(20) NOT NULL,
    `birth_date`   DATE        NOT NULL,
    `phone_number` VARCHAR(11) NOT NULL,
    `address`      VARCHAR(60) NOT NULL,
    `email`        VARCHAR(30) NULL,
    PRIMARY KEY (`reader_id`)
);

CREATE TABLE Accounts
(
    `reader_id` INT(10) NULL,
    `username`  VARCHAR(40)  NOT NULL,
    `password`  VARCHAR(100) NOT NULL,
    FOREIGN KEY (`reader_id`) REFERENCES Readers (`reader_id`)
);

CREATE TABLE Authors
(
    `author_id`   INT(10) NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(20) NOT NULL,
    `last_name`   VARCHAR(20) NOT NULL,
    `birth_date`  DATE        NOT NULL,
    `nationality` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`author_id`)
);

CREATE TABLE Books
(
    `book_id`      INT(10) NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(40)   NOT NULL,
    `publisher`    VARCHAR(40)   NOT NULL,
    `release_date` DATE          NOT NULL,
    `language`     VARCHAR(40)   NOT NULL,
    `available`    BOOLEAN       NOT NULL,
    `reader_id`    INT(10) NULL,
    `price`        DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (`book_id`),
    FOREIGN KEY (`reader_id`) REFERENCES Readers (`reader_id`)
);

CREATE TABLE Books_Authors
(
    `book_id`   INT(10) NOT NULL,
    `author_id` INT(10) NOT NULL,
    FOREIGN KEY (`book_id`) REFERENCES Books (`book_id`),
    FOREIGN KEY (`author_id`) REFERENCES Authors (author_id)
);

CREATE TABLE Reviews
(
    `review_id` INT(10) NOT NULL AUTO_INCREMENT,
    `book_id`   INT(10) NOT NULL,
    `reader_id` INT(10) NOT NULL,
    `rating`    INT  NOT NULL,
    `review`    TEXT NOT NULL,
    PRIMARY KEY (`review_id`),
    FOREIGN KEY (reader_id) REFERENCES Readers (reader_id),
    FOREIGN KEY (book_id) REFERENCES Books (book_id)
);