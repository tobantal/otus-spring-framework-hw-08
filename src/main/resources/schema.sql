DROP TABLE IF EXISTS genres; 

CREATE TABLE genres 
  ( 
     id    BIGINT NOT NULL auto_increment, 
     name VARCHAR(60) NOT NULL, 
     PRIMARY KEY(id) 
  ); 

DROP TABLE IF EXISTS authors; 

CREATE TABLE authors 
  ( 
     id     BIGINT NOT NULL auto_increment, 
     name VARCHAR(60) NOT NULL, 
     PRIMARY KEY(id) 
  ); 

DROP TABLE IF EXISTS books; 

CREATE TABLE books 
  ( 
     id        BIGINT NOT NULL auto_increment, 
     name      VARCHAR(60), 
     genre_id  BIGINT NOT NULL, 
     author_id BIGINT NOT NULL, 
     PRIMARY KEY(id), 
     FOREIGN KEY (genre_id) REFERENCES genres(id), 
     FOREIGN KEY (author_id) REFERENCES authors(id) 
  );
  
DROP TABLE IF EXISTS comments; 

CREATE TABLE comments 
  ( 
     id        BIGINT NOT NULL auto_increment, 
     text      VARCHAR(255), 
     book_id  BIGINT NOT NULL, 
     date TIMESTAMP NOT NULL, 
     PRIMARY KEY(id), 
     FOREIGN KEY (book_id) REFERENCES books(id)
  );