insert into library (id, name) values (1, 'Biblioteka Miejska');
insert into library (id, name) values (2, 'Książekczki do poduszczki');
insert into library (id, name) values (3, 'Kolejna biblioteka');
insert into library (id, name) values (4, 'Biblioteka im. Alicji Majewskiej');

insert into book (id, title, library_entity) values (1, 'Pierwsza książka', 1);
insert into book (id, title, library_entity) values (2, 'Druga książka', 2);
insert into book (id, title, library_entity) values (3, 'Trzecia książka', 3);
insert into book (id, title, library_entity) values (4, 'Czwarta książka', 4);
insert into book (id, title, library_entity) values (5, 'Piąta książka', 4);
insert into book (id, title, library_entity) values (6, 'Szósta książka', 4);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);
