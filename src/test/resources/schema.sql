DROP TABLE IF EXISTS painters;

CREATE TABLE IF NOT EXISTS painters(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    info VARCHAR(1000));
    
INSERT INTO painters (id, first_name, last_name, info) VALUES (DEFAULT, 'Pablo', 'Picasso', 'Pablo Picasso info');
INSERT INTO painters (id, first_name, last_name, info) VALUES (DEFAULT, 'Vincent', 'van Gogh', 'Vincent van Gogh info');
INSERT INTO painters (id, first_name, last_name, info) VALUES (DEFAULT, 'Leonardo', 'da Vinci', 'Leonardo da Vinci info');
INSERT INTO painters (id, first_name, last_name, info) VALUES (DEFAULT, 'Johannes', 'Vermeer', 'Johannes Vermeer info');


DROP TABLE IF EXISTS paintings;
	
CREATE TABLE IF NOT EXISTS paintings(
    id SERIAL PRIMARY KEY,
	year INT,
	name VARCHAR(70),
	style VARCHAR(70),
	image bytea);
	
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1911, 'Ma Jolie', 'CUBISM');
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1921, 'The Three Musicians', 'CUBISM');
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1888, 'Cafe Terrace At Night', 'POST_IMPRESSIONISM');
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1889, 'Starry Night', 'POST_IMPRESSIONISM');
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1489, 'Lady with an Ermine', 'HIGH_RENAISSANCE');
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1503, 'Mona Lisa', 'RENAISSANE');
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1665, 'Girl with a Pearl Earring', 'TRONIE');
INSERT INTO paintings (id, year, name, style) VALUES (DEFAULT, 1668, 'The Art of Painting', 'BAROQUE');

DROP TABLE IF EXISTS painters_paintings;


CREATE TABLE IF NOT EXISTS painters_paintings(
    painter_id INT,
    painting_id INT,
    FOREIGN KEY (painter_id) REFERENCES painters (id),
    FOREIGN KEY (painting_id) REFERENCES paintings (id));
    
INSERT INTO painters_paintings (painter_id, painting_id) VALUES (1, 1);
INSERT INTO painters_paintings (painter_id, painting_id) VALUES (1, 2);
INSERT INTO painters_paintings (painter_id, painting_id) VALUES (2, 3);
INSERT INTO painters_paintings (painter_id, painting_id) VALUES (2, 4);
INSERT INTO painters_paintings (painter_id, painting_id) VALUES (3, 6);
INSERT INTO painters_paintings (painter_id, painting_id) VALUES (4, 7);
INSERT INTO painters_paintings (painter_id, painting_id) VALUES (4, 8);