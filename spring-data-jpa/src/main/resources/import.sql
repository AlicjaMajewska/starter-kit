insert into book (id, title) values (1, 'Pierwsza książka');
insert into book (id, title) values (2, 'Druga książka');
insert into book (id, title) values (3, 'Trzecia książka');
insert into book (id, title) values (4, 'Czwarta książka');
insert into book (id, title) values (5, 'Piąta książka');
insert into book (id, title) values (6, 'Szósta książka');
insert into book (id, title) values (7, 'Siódma książka');
insert into book (id, title) values (8, 'Ósma książka');
insert into book (id, title) values (9, 'Dziewiąta książka');

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');
insert into author (id, first_name, last_name) values (10, 'Alicja', 'Majewska');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);
insert into book_author(book_id, author_id) values (4, 10);
insert into book_author(book_id, author_id) values (5, 10);
insert into book_author(book_id, author_id) values (6, 10);
insert into book_author(book_id, author_id) values (7, 10);
insert into book_author(book_id, author_id) values (8, 7);
insert into book_author(book_id, author_id) values (9, 7);