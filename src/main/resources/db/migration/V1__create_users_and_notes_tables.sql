CREATE TABLE IF NOT EXISTS notes (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     title VARCHAR(100) NOT NULL,
                                     content VARCHAR(1000) NOT NULL
);