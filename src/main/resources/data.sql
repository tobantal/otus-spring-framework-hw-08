INSERT INTO genres 
            (name) 
VALUES      ('comics'), 
            ('horrors'), 
            ('fantasy'); 

INSERT INTO authors 
            (name) 
VALUES      ('Ivanov'), 
            ('Petrov'), 
            ('Sidorov'),
            ('Kruglov'); 

INSERT INTO books 
            (genre_id, 
             author_id, 
             name) 
VALUES      (1, 
             2, 
             'Desert rose'), 
            (2, 
             1, 
             'Fly N2'), 
            (3, 
             3, 
             'Young Pirate'); 

INSERT INTO comments 
            (date, 
             text, 
             book_id) 
VALUES      (CURRENT_TIMESTAMP, 
             'good book', 
             2), 
            (CURRENT_TIMESTAMP, 
             'bad book', 
             1), 
            (CURRENT_TIMESTAMP, 
             'excelent book !!!', 
             3); 