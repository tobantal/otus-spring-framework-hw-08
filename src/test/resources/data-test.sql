INSERT INTO genres 
            (name) 
VALUES      ('comics'), 
            ('horrors'), 
            ('fantasy'),
            ('documentary'); 

INSERT INTO authors 
            (name) 
VALUES      ('Ivanov'), 
            ('Petrov'), 
            ('Sidorov'),
            ('Loshkin'); 

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
             'Young Pirate'),
            (1, 
             1, 
             'ABC'); 
