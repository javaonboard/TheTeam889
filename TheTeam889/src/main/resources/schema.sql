CREATE TABLE PET (
  id INTEGER NOT NULL AUTO_INCREMENT,
  species varchar(100) NOT NULL,
  age number (100) NOT NULL,
  fedday varchar(100) NOT NULL,
  price decimal(50,2) NOT NULL,
  discount NUMBER(7),
  status varchar(100),
  primary key(id)
  );