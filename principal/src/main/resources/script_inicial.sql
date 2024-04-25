CREATE SCHEMA PROD;

CREATE TABLE PROD.RELEASE_USE( 
    ID int primary key not null,
    NOME varchar(255) not null
);

CREATE TABLE PROD.DEVICES
(
    DEVICE VARCHAR(255) primary key,
    ID_RELEASE_USE int not  null,
    CLIENT_NAME VARCHAR(255)  null,
    RELEASE_DATE date  null,
	FOREIGN KEY(ID_RELEASE_USE) REFERENCES RELEASE_USE(ID)
	
);


insert into  PROD.RELEASE_USE values(1,'item não Processado');
insert into  PROD.RELEASE_USE values(2,'released');
insert into  PROD.RELEASE_USE values(3,'not released');
