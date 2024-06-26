
-- Inserare date în tabela Author
INSERT INTO Author (name) VALUES
                              ('Stephen King'),
                              ('J.K. Rowling'),
                              ('George Orwell'),
                              ('Harper Lee');

-- Inserare date în tabela Library
INSERT INTO Library (name) VALUES
                               ('Central Library'),
                               ('Westside Library'),
                               ('East End Library');

-- Inserare date în tabela Genre
INSERT INTO Genre (name) VALUES
                             ('Fantasy'),
                             ('Horror'),
                             ('Science Fiction'),
                             ('Classic Fiction');

-- Inserare date în tabela Member
INSERT INTO Member (name) VALUES
                              ('John Doe'),
                              ('Jane Smith'),
                              ('Michael Johnson'),
                              ('Emily Davis');

-- Inserare date în tabela Publisher
INSERT INTO Publisher (name) VALUES
                                 ('Penguin Books'),
                                 ('HarperCollins'),
                                 ('Random House');

-- Inserare date în tabela Book
INSERT INTO Book (title, author_id, library_id, genre_id, publisher_id) VALUES
                                                                            ('Harry Potter and the Philosopher''s Stone', 2, 1, 1, 1),
                                                                            ('1984', 3, 3, 3, 2),
                                                                            ('To Kill a Mockingbird', 4, 2, 4, 3),
                                                                            ('The Shining', 1, 1, 2, 1);

-- Inserare date în tabela Member_Book (legate de exemplu)
-- Vom presupune că John Doe a împrumutat câteva cărți
INSERT INTO Member_Book (member_id, book_id) VALUES
                                                 (1, 1),  -- John Doe a împrumutat Harry Potter
                                                 (1, 3);  -- John Doe a împrumutat To Kill a Mockingbird

-- Inserare date în tabela LibraryCard
INSERT INTO library_card (card_number, member_id) VALUES
    ('ABC123', 1);  -- Cardul membrului John Doe

