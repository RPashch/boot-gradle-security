drop table if exists authors;
drop table if exists books;
drop table if exists rewards;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS authors_books;

create table users(
id INT not null AUTO_INCREMENT primary key,
user_name varchar (255) not null,
password varchar (100) not null)
engine = InnoDB;

create TABLE roles (
   id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL
 )
ENGINE = InnoDB;

CREATE TABLE user_roles (
   user_id INT NOT NULL,
   role_id INT NOT NULL,
 
   FOREIGN KEY (user_id) REFERENCES users (id),
   FOREIGN KEY (role_id) REFERENCES roles (id),
 
   UNIQUE (user_id, role_id)
 )
ENGINE = InnoDB;

CREATE TABLE authors (
  author_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  sex VARCHAR(10) NOT NULL,
  birth_date DATE NOT NULL	
)
ENGINE = INNODB;

CREATE TABLE books (
  book_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  ISBN VARCHAR(255) unique NOT NULL,
  genre VARCHAR(50) NOT NULL
)
ENGINE = INNODB;


CREATE TABLE rewards (
  reward_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  author_id INT(11) NOT NULL,
  year int(10) NOT NULL,
  title VARCHAR(255) NOT NULL,
  CONSTRAINT FK_rewards_authors_id FOREIGN KEY (author_id)
    REFERENCES authors(author_id) ON DELETE RESTRICT ON UPDATE CASCADE
)
ENGINE = INNODB;

CREATE TABLE authors_books (
  authors_book_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  author_id INT(11) NOT NULL,
  book_id INT(11) NOT NULL,
  CONSTRAINT FK_autorsbook_autors_autor_id FOREIGN KEY (author_id)
    REFERENCES authors(author_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT FK_autorsbook_books_BookID FOREIGN KEY (book_id)
    REFERENCES books(book_id) ON DELETE RESTRICT ON UPDATE CASCADE
)
ENGINE = INNODB;