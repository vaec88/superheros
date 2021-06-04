DROP TABLE IF EXISTS superheros;

CREATE TABLE superheros (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

INSERT INTO superheros (name)
VALUES
('Ironman'),
('Batman'),
('Superman'),
('Hulk'),
('Thor'),
('Wolverine'),
('Captain America'),
('Antman'),
('Daredevil'),
('Acuaman');