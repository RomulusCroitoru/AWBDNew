DROP TABLE IF EXISTS Member_Book;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS LibraryCard;
DROP TABLE IF EXISTS Author;
DROP TABLE IF EXISTS Library;
DROP TABLE IF EXISTS Genre;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Publisher;
DROP TABLE IF EXISTS USER_AUTHORITY;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS AUTHORITY;



CREATE TABLE Author (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);


CREATE TABLE Library (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);


CREATE TABLE Genre (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);


CREATE TABLE Member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE Publisher (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);


CREATE TABLE LibraryCard (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             cardNumber VARCHAR(255) NOT NULL,
                             member_id BIGINT UNIQUE,
                             CONSTRAINT FK_Member_LibraryCard FOREIGN KEY (member_id) REFERENCES Member(id)
);

CREATE TABLE Book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author_id BIGINT,
                      library_id BIGINT,
                      genre_id BIGINT,
                      publisher_id BIGINT,
                      CONSTRAINT FK_Author_Book FOREIGN KEY (author_id) REFERENCES Author(id),
                      CONSTRAINT FK_Library_Book FOREIGN KEY (library_id) REFERENCES Library(id),
                      CONSTRAINT FK_Genre_Book FOREIGN KEY (genre_id) REFERENCES Genre(id),
                      CONSTRAINT FK_Publisher_Book FOREIGN KEY (publisher_id) REFERENCES Publisher(id)
);



CREATE TABLE Member_Book (
                             member_id BIGINT,
                             book_id BIGINT,
                             PRIMARY KEY (member_id, book_id),
                             CONSTRAINT FK_Member_Book_Member FOREIGN KEY (member_id) REFERENCES Member(id),
                             CONSTRAINT FK_Member_Book_Book FOREIGN KEY (book_id) REFERENCES Book(id)

);

CREATE TABLE USER(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     username VARCHAR(50) NOT NULL,
                     password VARCHAR(100) NOT NULL,
                     enabled BOOLEAN NOT NULL DEFAULT true,
                     account_non_expired BOOLEAN NOT NULL DEFAULT true,
                     account_non_locked BOOLEAN NOT NULL DEFAULT true,
                     credentials_non_expired BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE AUTHORITY(
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          role VARCHAR(50) NOT NULL
);

CREATE TABLE USER_AUTHORITY(
                               user_id BIGINT,
                               authority_id BIGINT,
                               FOREIGN KEY (user_id) REFERENCES USER(id),
                               FOREIGN KEY (authority_id) REFERENCES AUTHORITY(id),
                               PRIMARY KEY (user_id, authority_id)
);




