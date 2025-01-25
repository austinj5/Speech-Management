-- Speeches Table
CREATE TABLE Speeches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(255) NOT NULL,
    speech_text TEXT NOT NULL,
    --comma separated String
    keywords VARCHAR(1000), 
    speech_date DATE NOT NULL
);