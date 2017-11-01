INSERT INTO users VALUES (1,"user", "$2a$10$ZLI2Y6YQk3mN2yftRuDEm.gh9BnmEYWQNRKq32QNhFdhDY/LNMc1K");

insert into roles values (1, "ADMIN");
insert into roles values (2, "USER");

INSERT INTO user_roles VALUES (1, 2);

INSERT INTO authors VALUES (1,"Ernest", "Hemingway", "MAN",DATE "1989-07-21");
INSERT INTO authors VALUES (2,"Lev", "Tolstoy", "MAN",DATE "1928-09-09");
INSERT INTO authors VALUES (3,"Joan", "Rowling", "WOMAN",DATE "1965-07-31");
INSERT INTO authors VALUES (4,"Ivan", "Ivanov", "MAN",DATE "2000-01-01");
INSERT INTO authors VALUES (5,"Olga", "Kiev", "WOMAN",DATE "1988-05-05");

INSERT INTO books VALUES (1,"Goodbye gun", "10452820", "ROMANCE");
INSERT INTO books VALUES (2,"War and peace", "44867866", "ROMANCE");
INSERT INTO books VALUES (3,"Anna Karenina", "78642355", "ROMANCE");
INSERT INTO books VALUES (4,"Harry Poter", "45428888", "FANTASY");
INSERT INTO books VALUES (5,"Horror book", "22882822", "HORROR");
INSERT INTO books VALUES (6,"Math", "78242488", "SCIENCE");
INSERT INTO books VALUES (7,"Crime story", "96343435", "CRIME");

INSERT INTO rewards VALUES (1,1, 1925, "Best book after First Warld War");
INSERT INTO rewards VALUES (2,2, 1900, "Best roman");
INSERT INTO rewards VALUES (3,2, 2000, "Best author for the ages");
INSERT INTO rewards VALUES (4,3, 2015, "Most interesting book in the world");

INSERT INTO authors_books VALUES (1,1,1);
INSERT INTO authors_books VALUES (2,2,2);
INSERT INTO authors_books VALUES (3,2,3);
INSERT INTO authors_books VALUES (4,3,4);
INSERT INTO authors_books VALUES (5,4,5);
INSERT INTO authors_books VALUES (6,4,7);
INSERT INTO authors_books VALUES (7,5,5);
INSERT INTO authors_books VALUES (8,5,6);
INSERT INTO authors_books VALUES (9,5,7);
