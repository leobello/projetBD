/*==============================================================*/
/* DBMS NAME:      SYBASE SQL ANYWHERE 11                       */
/* CREATED ON:     17/01/2019 19:32:16                          */
/*==============================================================*/
/*
Requete permettant de vois les tables liées par foreign key
SELECT TABLE_NAME,CONSTRAINT_NAME FROM USER_CONSTRAINTS 
WHERE R_CONSTRAINT_NAME IN (SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS WHERE TABLE_NAME='nom de la table a supprimer') AND CONSTRAINT_TYPE='R'; 
*/
DROP TABLE ADRESSECLIENT;
DROP TABLE PHOTOALBUMPHOTO;
DROP TABLE CODEPROMOCLIENT;
DROP TABLE STOCKARTICLE; 
DROP TABLE PHOTOCALENDRIER;
DROP TABLE PHOTOAGENDA;
DROP TABLE PHOTOTIRAGE;
DROP TABLE COMMANDEADRESSE;
DROP TABLE JOURS;
DROP TABLE MURAL; 
DROP TABLE SEMAINES; 
DROP TABLE BUREAU; 
DROP TABLE CADREPHOTO ;
DROP TABLE CADRE;
DROP TABLE CALENDRIER;
DROP TABLE AGENDA;
DROP TABLE ALBUMPHOTO;
DROP TABLE TIRAGE; 

DROP TABLE PHOTO; 
DROP TABLE FICHIERIMAGE;
DROP TABLE ARTICLE;
DROP TABLE IMPRESSION;
DROP TABLE COMMANDE;
DROP TABLE CLIENT;
DROP TABLE ADRESSE;
DROP TABLE CODEPROMO;
DROP TABLE STOCK;


/*==============================================================*/
/* TABLE: ADRESSE                                               */
/*==============================================================*/
CREATE TABLE ADRESSE
(
   ID_ADRESSE           NUMBER                         NOT NULL,
   CODEPOSTAL           NUMBER                         NOT NULL,
   VILLE                VARCHAR(255)                    NOT NULL,
   NOM_ADRESSE          VARCHAR(255)                    NULL,
   PRENOM_ADRESSE       VARCHAR(255)                    NULL,
   RUE                  VARCHAR(255)                    NULL,
   CONSTRAINT PK_ADRESSE PRIMARY KEY (ID_ADRESSE)
);

/*==============================================================*/
/* TABLE: CODEPROMO                                             */
/*==============================================================*/
CREATE TABLE CODEPROMO 
(
   CODE                 VARCHAR(255)                   NOT NULL,
   ID_CODEPROMO         NUMBER                         NOT NULL,
   POURCENTAGE          DECIMAL                        NOT NULL,
   CONSTRAINT PK_CODEPROMO PRIMARY KEY (ID_CODEPROMO)
);

/*==============================================================*/
/* TABLE: CLIENT                                                */
/*==============================================================*/
CREATE TABLE CLIENT 
(
   MAILCLIENT           VARCHAR(255)                    NOT NULL,
   NOM                  VARCHAR(255)                    NOT NULL,
   PRENOM               VARCHAR(255)                    NOT NULL,
   MOTDEPASSE           VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_CLIENT PRIMARY KEY (MAILCLIENT)
);

/*==============================================================*/
/* TABLE: COMMANDE                                              */
/*==============================================================*/
CREATE TABLE COMMANDE 
(
   DATEC                VARCHAR(255)                   NULL,
   MODELIVRAISON        VARCHAR(255)                   NULL,
   STATUT_COMMANDE      VARCHAR(255)                   NULL,
   NUMCOMMANDE          NUMBER                         NOT NULL,
   ID_CODEPROMO         NUMBER                         NULL,
   MAILCLIENT           VARCHAR(255)                   NOT NULL,
   CONSTRAINT PK_COMMANDE PRIMARY KEY (NUMCOMMANDE),
   CONSTRAINT FK_COMMANDE_1 
      FOREIGN KEY (ID_CODEPROMO) REFERENCES CODEPROMO(ID_CODEPROMO) ,
   CONSTRAINT FK_COMMANDE_2 
      FOREIGN KEY (MAILCLIENT) REFERENCES CLIENT(MAILCLIENT),
   CONSTRAINT CK_COMMANDE_1 
      CHECK (MODELIVRAISON='ADRESSE' OR MODELIVRAISON='POINT RELAIS' ),
   CONSTRAINT CK_COMMANDE_2 
      CHECK (STATUT_COMMANDE='EN COURS' OR 
            STATUT_COMMANDE='PRET A L ENVOI' OR 
            STATUT_COMMANDE='ENVOYEE')
);


/*==============================================================*/
/* TABLE: COMMANDEADRESSE                                       */
/*==============================================================*/
CREATE TABLE COMMANDEADRESSE
(
   ID_ADRESSE           NUMBER                        NOT NULL,
   NUMCOMMANDE          NUMBER                        NOT NULL,
   CONSTRAINT PK_COMMANDEADRESSE PRIMARY KEY (ID_ADRESSE, NUMCOMMANDE),
   CONSTRAINT FK_COMMANDEADRESSE_1 
      FOREIGN KEY (ID_ADRESSE) REFERENCES ADRESSE(ID_ADRESSE),
   CONSTRAINT FK_COMMANDEADRESSE_2 
      FOREIGN KEY (NUMCOMMANDE) REFERENCES COMMANDE(NUMCOMMANDE)
);


/*==============================================================*/
/* TABLE: FICHIERIMAGE                                          */
/*==============================================================*/
CREATE TABLE FICHIERIMAGE 
(
   PATH                 VARCHAR(255)                    NOT NULL,
   PROPRIETAIRE         VARCHAR(255)                    NOT NULL,
   INFOPRISEDEVUE       VARCHAR(255)                    NOT NULL,
   RESOLUTION           VARCHAR(255)                    NOT NULL,
   PARTAGE              SMALLINT                        NOT NULL,
   DATEACCES            VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_FICHIERIMAGE PRIMARY KEY (PATH, PROPRIETAIRE),
   CONSTRAINT FK_FICHIERIMAGE 
      FOREIGN KEY (PROPRIETAIRE) REFERENCES CLIENT(MAILCLIENT),
   CONSTRAINT CK_FICHIERIMAGE
      CHECK (RESOLUTION='FAIBLE' OR RESOLUTION='MOYENNE' OR RESOLUTION='HAUTE')
);

/*==============================================================*/
/* TABLE: ADRESSECLIENT                                         */
/*==============================================================*/
CREATE TABLE ADRESSECLIENT 
(
   MAILCLIENT           VARCHAR(255)                    NOT NULL,
   ID_ADRESSE           NUMBER                         NOT NULL,
   CONSTRAINT PK_ADRESSECLIENT PRIMARY KEY (MAILCLIENT, ID_ADRESSE),
   CONSTRAINT FK_ADRESSECLIENT_1 
      FOREIGN KEY (MAILCLIENT) REFERENCES CLIENT(MAILCLIENT),
   CONSTRAINT FK_ADRESSECLIENT_2
      FOREIGN KEY (ID_ADRESSE) REFERENCES ADRESSE(ID_ADRESSE)
);

/*==============================================================*/
/* TABLE: CODEPROMOCLIENT                                       */
/*==============================================================*/
CREATE TABLE CODEPROMOCLIENT 
(
   ID_CODEPROMO         NUMBER                         NOT NULL,
   MAILCLIENT           VARCHAR(255)                   NOT NULL,
   DEJAUTILISE          SMALLINT                       NOT NULL,
   CONSTRAINT PK_CODEPROMOCLIENT PRIMARY KEY (ID_CODEPROMO, MAILCLIENT),
   CONSTRAINT CK_CODEPROMOCLIENT CHECK (DEJAUTILISE<=1 OR DEJAUTILISE>=0),
   CONSTRAINT FK_CODEPROMOCLIENT_1
      FOREIGN KEY (ID_CODEPROMO) REFERENCES CODEPROMO(ID_CODEPROMO),
   CONSTRAINT FK_CODEPROMOCLIENT_2
      FOREIGN KEY (MAILCLIENT) REFERENCES CLIENT(MAILCLIENT)
);

/*==============================================================*/
/* TABLE: PHOTO                                                 */
/*==============================================================*/
CREATE TABLE PHOTO 
(
   ID_PHOTO             NUMBER                         NOT NULL,
   PATH                 VARCHAR(255)                    NOT NULL,
   PROPRIETAIRE         VARCHAR(255)                    NOT NULL,
   RETOUCHE             VARCHAR(255)                    NULL,
   DESCRIPTION          VARCHAR(255)                    NULL,
   CONSTRAINT PK_PHOTO PRIMARY KEY (ID_PHOTO),
   CONSTRAINT FK_PHOTO
      FOREIGN KEY (PATH,PROPRIETAIRE) REFERENCES FICHIERIMAGE(PATH,PROPRIETAIRE)
);

/*==============================================================*/
/* TABLE: IMPRESSION                                            */
/*==============================================================*/
CREATE TABLE IMPRESSION 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_IMPRESSION PRIMARY KEY (NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: ARTICLE                                               */
/*==============================================================*/
CREATE TABLE ARTICLE 
(
   ID_ARTICLE           NUMBER                         NOT NULL,
   NUMCOMMANDE          NUMBER                         NOT NULL,
   PRIX                 DECIMAL                        NOT NULL,
   QUANTITE             NUMBER                         NOT NULL,
   NUMIMPRESSION         NUMBER                         NOT NULL,
   CONSTRAINT PK_ARTICLE PRIMARY KEY (ID_ARTICLE),
   CONSTRAINT FK_ARTICLE_1
      FOREIGN KEY (NUMCOMMANDE) REFERENCES COMMANDE(NUMCOMMANDE),
   CONSTRAINT FK_ARTICLE_2
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: STOCK                                                 */
/*==============================================================*/
CREATE TABLE STOCK 
(
   TYPE_IMPRESSION      VARCHAR(255)                   NOT NULL,
   QUANTITESTOCK        NUMBER                         NOT NULL,
   CONSTRAINT PK_STOCK PRIMARY KEY (TYPE_IMPRESSION),
   CONSTRAINT CK_STOCK 
      CHECK (TYPE_IMPRESSION='SEMAINES' OR TYPE_IMPRESSION='JOURS' OR TYPE_IMPRESSION='TIRAGE' OR TYPE_IMPRESSION='CADRE' OR TYPE_IMPRESSION='MURAL' OR TYPE_IMPRESSION='BUREAU' OR TYPE_IMPRESSION='FEUILLE A4' OR TYPE_IMPRESSION='FEUILLE A5' )
);

/*==============================================================*/
/* TABLE: STOCKARTICLE                                          */
/*==============================================================*/
CREATE TABLE STOCKARTICLE 
(
   TYPE_IMPRESSION      VARCHAR(255)                   NOT NULL,
   ID_ARTICLE           NUMBER                         NOT NULL,
   CONSTRAINT PK_STOCKARTICLE PRIMARY KEY (TYPE_IMPRESSION, ID_ARTICLE),
   CONSTRAINT FK_STOCKARTICLE_1
      FOREIGN KEY (TYPE_IMPRESSION) REFERENCES STOCK(TYPE_IMPRESSION),
   CONSTRAINT FK_STOCKARTICLE_2
      FOREIGN KEY (ID_ARTICLE) REFERENCES ARTICLE(ID_ARTICLE)
);

/*==============================================================*/
/* TABLE: AGENDA                                                */
/*==============================================================*/
CREATE TABLE AGENDA 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   CONSTRAINT PK_AGENDA PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_AGENDA
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: ALBUMPHOTO                                            */
/*==============================================================*/
CREATE TABLE ALBUMPHOTO 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                          NULL,
   TITRE                VARCHAR(255)                    NULL,
   QUALITE              VARCHAR(255)                    NULL,
   FORMATALBUM          VARCHAR(255)                    NULL,
   CONSTRAINT PK_ALBUMPHOTO PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_ALBUMPHOTO
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_ALBUMPHOTO_1
      CHECK (QUALITE='FAIBLE' OR QUALITE='MOYENNE' OR QUALITE='HAUTE'),
   CONSTRAINT CK_ALBUMPHOTO_2
      CHECK (FORMATALBUM='A4' OR FORMATALBUM='A5')
);

/*==============================================================*/
/* TABLE: CALENDRIER                                            */
/*==============================================================*/
CREATE TABLE CALENDRIER 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   CONSTRAINT PK_CALENDRIER PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_CALENDRIER
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: BUREAU                                                */
/*==============================================================*/
CREATE TABLE BUREAU 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   CONSTRAINT PK_BUREAU PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_BUREAU
      FOREIGN KEY (NUMIMPRESSION) REFERENCES CALENDRIER(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: CADRE                                                 */
/*==============================================================*/
CREATE TABLE CADRE 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   MODELE               VARCHAR(255)                    NOT NULL,
   TAILLE               VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_CADRE PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_CADRE
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_CADRE_1
      CHECK (MODELE='PAYSAGE' OR MODELE='PORTRAIT'),
   CONSTRAINT CK_CADRE_2
      CHECK (TAILLE='M' OR TAILLE='L')
);

/*==============================================================*/
/* TABLE: CADREPHOTO                                            */
/*==============================================================*/
CREATE TABLE CADREPHOTO 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO              NUMBER                         NOT NULL,
   CONSTRAINT PK_CADREPHOTO PRIMARY KEY (NUMIMPRESSION, ID_PHOTO),
   CONSTRAINT FK_CADREPHOTO_1
      FOREIGN KEY (NUMIMPRESSION) REFERENCES CADRE(NUMIMPRESSION),
   CONSTRAINT FK_CADREPHOTO_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: JOURS                                                 */
/*==============================================================*/
CREATE TABLE JOURS 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   NOPAGEAGENDA         NUMBER                         NULL,
   CONSTRAINT PK_JOURS PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_JOURS
      FOREIGN KEY (NUMIMPRESSION) REFERENCES AGENDA(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: MURAL                                                 */
/*==============================================================*/
CREATE TABLE MURAL 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   CONSTRAINT PK_MURAL PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_MURAL
      FOREIGN KEY (NUMIMPRESSION) REFERENCES CALENDRIER(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: SEMAINES                                              */
/*==============================================================*/
CREATE TABLE SEMAINES 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   NOPAGEAGENDA         NUMBER                         NULL,
   CONSTRAINT PK_SEMAINES PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_SEMAINES
      FOREIGN KEY (NUMIMPRESSION) REFERENCES AGENDA(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: TIRAGE                                                */
/*==============================================================*/
CREATE TABLE TIRAGE 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   QUALITEPAPIER        VARCHAR(255)                    NULL,
   FORMATTIRAGE         VARCHAR(255)                    NULL,
   CONSTRAINT PK_TIRAGE PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_TIRAGE
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_TIRAGE_1
      check ( qualitepapier='bonne' or qualitepapier='moyenne' ), 
   CONSTRAINT CK_TIRAGE_2
      check ( formattirage='paysage' or formattirage='portrait' ) 
);

/*==============================================================*/
/* TABLE: PHOTOALBUMPHOTO                                        */
/*==============================================================*/
CREATE TABLE PHOTOALBUMPHOTO
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   NOPAGEPHOTOALBUMPHOTO NUMBER                         NULL,
   CONSTRAINT PK_PHOTOALBUMPHOTO_13 PRIMARY KEY (NUMIMPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOALBUMPHOTO_1
      FOREIGN KEY (NUMIMPRESSION) REFERENCES ALBUMPHOTO(NUMIMPRESSION),
   CONSTRAINT FK_PHOTOALBUMPHOTO_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: PHOTOAGENDA                                        */
/*==============================================================*/
CREATE TABLE PHOTOAGENDA
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   NOPAGEPHOTOAGENDA    NUMBER                         NULL,
   CONSTRAINT PK_PHOTOAGENDA_16 PRIMARY KEY (NUMIMPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOAGENDA_1
      FOREIGN KEY (NUMIMPRESSION) REFERENCES AGENDA(NUMIMPRESSION),
   CONSTRAINT FK_PHOTOAGENDA_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: PHOTOTIRAGE                                         */
/*==============================================================*/
CREATE TABLE PHOTOTIRAGE
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO              NUMBER                         NOT NULL,
   NBEXEMPLAIRE          NUMBER                         NULL,
   CONSTRAINT PK_PHOTOTIRAGE PRIMARY KEY (NUMIMPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOTIRAGE_1
      FOREIGN KEY (NUMIMPRESSION) REFERENCES TIRAGE(NUMIMPRESSION),
   CONSTRAINT FK_PHOTOTIRAGE_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: PHOTOCALENDRIER                                        */
/*==============================================================*/
CREATE TABLE PHOTOCALENDRIER
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO              NUMBER                         NOT NULL,
   NOPAGEPHOTOCALENDRIER NUMBER                         NULL,
   CONSTRAINT PK_PHOTOCALENDRIER PRIMARY KEY (NUMIMPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOCALENDRIER_1
      FOREIGN KEY (NUMIMPRESSION) REFERENCES CALENDRIER(NUMIMPRESSION),
   CONSTRAINT FK_PHOTOCALENDRIER_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

INSERT INTO ADRESSE VALUES (1, 74200, 'VALENCE', 'RICHARD', 'JEAN', '255 RUE BOOLEAN');
INSERT INTO ADRESSE VALUES (2, 74225, 'PARIS', 'MOTUN', 'LOUIS', '254 RUE BOLEAIN');
INSERT INTO ADRESSE VALUES (3, 54200, 'THONON', 'RICH', 'ARTHUR', '255 AVENUE LEAN');
INSERT INTO ADRESSE VALUES (4, 84200, 'GRENOBLE', 'WILLS', 'LOIC', '12 RUE BOOLETTE');
INSERT INTO ADRESSE VALUES (5, 85254, 'BENANT', 'LOINTU', 'MICKA', '15 AVENUE DE LA TETE');
INSERT INTO ADRESSE VALUES (6, 83211, 'EVIAN', 'KER', 'GEORGE', '12 RUE CHOUBERT');
INSERT INTO ADRESSE VALUES (7, 12560, 'TOULOUSE', 'MOIN', 'MARTIAL', '5541 RUE DE LA LIBERTE');

INSERT INTO CODEPROMO VALUES ('AKJFKJDDFDFZ545D564S5Z64S64',1, 12.5);
INSERT INTO CODEPROMO VALUES ('AKJFKJJDHFJHKJSF545666SDF',2, 5.3);
INSERT INTO CODEPROMO VALUES ('AKJSDFSSD4F51Z545D564S5Z64S64',3, 10);
INSERT INTO CODEPROMO VALUES ('SDFS51DFS1DF45D564S5Z64S64',4, 15.2);
INSERT INTO CODEPROMO VALUES ('SQ5D4SD1FDFZ545D564S5Z64S64',5, 13.2);
INSERT INTO CODEPROMO VALUES ('S5FSF6SDF545D564S5Z64S64',6, 25);

INSERT INTO CLIENT VALUES ('LREOOF@ORANGE.FR', 'JEAN', 'PETIT', 'SDF545');
INSERT INTO CLIENT VALUES ('LOUISR@ORANGE.FR', 'LOUIS', 'GRAND', 'DFGGD545');
INSERT INTO CLIENT VALUES ('WILLY@ORANGE.FR', 'WILLIAMS', 'ARTO', 'SDFGDFG5');
INSERT INTO CLIENT VALUES ('WILLSS@ORANGE.FR', 'JEAN', 'MECHANT', 'DFSDFF545');
INSERT INTO CLIENT VALUES ('JEAP@ORANGE.FR', 'LOUIS', 'LAVOIT', 'SDFDF54SDFSDF');

INSERT INTO COMMANDE VALUES ('2019-01-20', 'ADRESSE', 'EN COURS', 1,  NULL ,'LREOOF@ORANGE.FR' );
INSERT INTO COMMANDE VALUES ('2019-01-15', 'ADRESSE', 'EN COURS', 2, 3,'WILLY@ORANGE.FR' );
INSERT INTO COMMANDE VALUES ('2019-01-01', 'ADRESSE', 'EN COURS', 3, 6,'WILLY@ORANGE.FR');
INSERT INTO COMMANDE VALUES ('2019-01-12', 'ADRESSE', 'EN COURS', 4, 5,'JEAP@ORANGE.FR');
INSERT INTO COMMANDE VALUES ('2019-01-12', 'ADRESSE', 'EN COURS', 5, NULL,'JEAP@ORANGE.FR');

INSERT INTO COMMANDEADRESSE VALUES (1, 1);
INSERT INTO COMMANDEADRESSE VALUES (3, 5);
INSERT INTO COMMANDEADRESSE VALUES (2, 4);
INSERT INTO COMMANDEADRESSE VALUES (4, 3);

INSERT INTO FICHIERIMAGE VALUES ('/DSF/SFF','JEAP@ORANGE.FR', 'VUE DE FACE', 'MOYENNE', 0, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/DSDF/SDFFF/SDF','LOUISR@ORANGE.FR', 'CONTREPLONGEE', 'HAUTE', 1, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/ZER/DSF','LOUISR@ORANGE.FR', 'PLONGEE', 'HAUTE', 1, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/ZETT/ERT','WILLY@ORANGE.FR', 'FACE', 'HAUTE', 0, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/HGJ/DG','JEAP@ORANGE.FR', 'PLAN', 'FAIBLE', 1, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/AZD/DFGD','LREOOF@ORANGE.FR', 'PLONGEE', 'FAIBLE', 0, '2019-01-15');

INSERT INTO ADRESSECLIENT VALUES ('JEAP@ORANGE.FR', 1 );
INSERT INTO ADRESSECLIENT VALUES ('WILLY@ORANGE.FR', 6 );
INSERT INTO ADRESSECLIENT VALUES ('JEAP@ORANGE.FR', 7 );
INSERT INTO ADRESSECLIENT VALUES ('JEAP@ORANGE.FR', 5 );
INSERT INTO ADRESSECLIENT VALUES ('WILLSS@ORANGE.FR', 3 );

INSERT INTO CODEPROMOCLIENT VALUES (1, 'WILLY@ORANGE.FR', 0);
INSERT INTO CODEPROMOCLIENT VALUES (2, 'WILLY@ORANGE.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES (3, 'WILLSS@ORANGE.FR', 0);
INSERT INTO CODEPROMOCLIENT VALUES (4, 'WILLSS@ORANGE.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES (5, 'JEAP@ORANGE.FR', 0);
INSERT INTO CODEPROMOCLIENT VALUES (5, 'LREOOF@ORANGE.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES (5, 'LOUISR@ORANGE.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES (6, 'JEAP@ORANGE.FR', 0);
INSERT INTO CODEPROMOCLIENT VALUES (6, 'LREOOF@ORANGE.FR', 0);

INSERT INTO PHOTO VALUES (1, '/ZETT/ERT','WILLY@ORANGE.FR', 'FILTRE BLEU', 'PHOTO PRISE AU SAHARA' );
INSERT INTO PHOTO VALUES (2, '/ZETT/ERT','WILLY@ORANGE.FR', 'FILTRE VIOLET', 'PHOTO PRISE AU KENTUKI' );
INSERT INTO PHOTO VALUES (3, '/DSDF/SDFFF/SDF','LOUISR@ORANGE.FR', 'FILTRE GROSSISSANT', 'PHOTO AVEC NARUTO' );
INSERT INTO PHOTO VALUES (4, '/DSF/SFF','JEAP@ORANGE.FR', 'FILTRE ASSAINISSANT', 'PHOTO PRISE A PUNK HASARD' );
INSERT INTO PHOTO VALUES (5, '/AZD/DFGD','LREOOF@ORANGE.FR', 'FILTRE GRIS', 'PHOTO PRISE A DYSNEY AVEC MAMAN ET MAMIE' );

INSERT INTO IMPRESSION VALUES (1, '/ADRF/FDG/SDF' );
INSERT INTO IMPRESSION VALUES (2, '/FGH/FFGHG/SDF' );
INSERT INTO IMPRESSION VALUES (3, '/ADRF/FFHRRG/SDF' );
INSERT INTO IMPRESSION VALUES (4, '/ADRF/DFREG/SDF' );
INSERT INTO IMPRESSION VALUES (5, '/ADFGYTF/FDG/GHF' );
INSERT INTO IMPRESSION VALUES (6, '/ADRF/FDG/GHYTH' );
INSERT INTO IMPRESSION VALUES (7, '/ADRF/FDG/FGHUYJKGH' );
INSERT INTO IMPRESSION VALUES (8, '/ADFGHU/FDFGH/SFGHUDF' );
INSERT INTO IMPRESSION VALUES (9, '/AD/G/F' );
INSERT INTO IMPRESSION VALUES (10, '/A/FDG/GTH' );
INSERT INTO IMPRESSION VALUES (11, '/ADRF/F/KGH' );
INSERT INTO IMPRESSION VALUES (12, '/AU/FD/HUDF' );
INSERT INTO IMPRESSION VALUES (13, '/AGYTF/DG/GF' );
INSERT INTO IMPRESSION VALUES (14, '/ADRF/FDG/GH' );
INSERT INTO IMPRESSION VALUES (15, '/AF/FG/FH' );
INSERT INTO IMPRESSION VALUES (16, '/DFU/FH/HUDF' );

INSERT INTO ARTICLE VALUES (1, 1, 15.25, 5, 1);
INSERT INTO ARTICLE VALUES (2, 2, 15.25, 5, 2);
INSERT INTO ARTICLE VALUES (3, 2, 15.25, 5, 2);
INSERT INTO ARTICLE VALUES (4, 5, 15.25, 5, 3);
INSERT INTO ARTICLE VALUES (5, 5, 15.25, 5, 3);
INSERT INTO ARTICLE VALUES (6, 3, 15.25, 5, 4);
INSERT INTO ARTICLE VALUES (7, 4, 15.25, 5, 5);

INSERT INTO STOCK VALUES ('SEMAINES', 15);
INSERT INTO STOCK VALUES ('JOURS', 15);
INSERT INTO STOCK VALUES ('TIRAGE', 15);
INSERT INTO STOCK VALUES ('CADRE', 15);
INSERT INTO STOCK VALUES ('MURAL', 15);
INSERT INTO STOCK VALUES ('BUREAU', 15);
INSERT INTO STOCK VALUES ('FEUILLE A4', 15);
INSERT INTO STOCK VALUES ('FEUILLE A5', 15);

INSERT INTO STOCKARTICLE VALUES ('SEMAINES', 1 );
INSERT INTO STOCKARTICLE VALUES ('SEMAINES', 2 );
INSERT INTO STOCKARTICLE VALUES ('SEMAINES', 3 );
INSERT INTO STOCKARTICLE VALUES ('MURAL', 1 );
INSERT INTO STOCKARTICLE VALUES ('FEUILLE A5', 2 );
INSERT INTO STOCKARTICLE VALUES ('TIRAGE', 5 );

INSERT INTO AGENDA VALUES (1);
INSERT INTO AGENDA VALUES (2);

INSERT INTO ALBUMPHOTO VALUES (3, 1,'PHOTO DE FAMILLE', 'FAIBLE', 'A4');
INSERT INTO ALBUMPHOTO VALUES (4, 2,'PHOTO DE LA FERME', 'HAUTE', 'A5');

INSERT INTO CALENDRIER VALUES (5);
INSERT INTO CALENDRIER VALUES (6);
INSERT INTO CALENDRIER VALUES (7);
INSERT INTO CALENDRIER VALUES (8);

INSERT INTO BUREAU VALUES (5);
INSERT INTO BUREAU VALUES (6);

INSERT INTO CADRE VALUES (9, 'PAYSAGE', 'M');
INSERT INTO CADRE VALUES (10, 'PORTRAIT', 'M');

insert into cadrephoto values (9, 1);
insert into cadrephoto values (9, 2);

insert into jours values (1,365);

insert into mural values (5);
insert into mural values (6);

insert into semaines values (2,52);

insert into tirage values ( 10, 'bonne', 'portrait' );
insert into tirage values ( 11, 'moyenne', 'paysage' );

insert into photoalbumphoto values (3, 3, 1);
insert into photoalbumphoto values (3, 4, 1);
insert into photoalbumphoto values (4, 3, 1);

insert into photoagenda values (1, 1, 1 );
insert into photoagenda values (1, 2, 1 );
insert into photoagenda values (1, 3, 1 );
insert into photoagenda values (2, 1, 1 );

insert into phototirage values (10, 1, 2);
insert into phototirage values (10, 2, 2);
insert into phototirage values (10, 3, 2);
insert into phototirage values (11, 1, 4);

insert into photocalendrier values (5, 1, 1 );
insert into photocalendrier values (5, 2, 1 );
insert into photocalendrier values (5, 3, 2 );
insert into photocalendrier values (6, 1, 1 );
insert into photocalendrier values (6, 2, 2 );
insert into photocalendrier values (6, 4, 1 );
insert into photocalendrier values (6, 5, 2 );
insert into photocalendrier values (7, 1, 1 );
insert into photocalendrier values (8, 1, 1 );








-- empêcher la création d’une commande avec un code promo si le code promo utilisé n’appartient pas à ce client.
 
CREATE OR REPLACE TRIGGER CODEPROMO2
AFTER INSERT OR UPDATE ON COMMANDE
FOR EACH ROW
DECLARE
matchingCP integer;
BEGIN
   SELECT COUNT(*) INTO matchingCP 
   FROM CODEPROMOCLIENT
   WHERE MAILCLIENT = :new.MAILCLIENT
   AND ID_CODEPROMO = :new.ID_CODEPROMO;

   IF (matchingCP = 0) THEN 
      raise_application_error(-20001, 'code Prommotionel invalide');
   END IF;
END;
/

-- Vérifier qu’il n’y a pas de commande en cours lors de la suppression d’un client
-- A revoir 
/*
CREATE OR REPLACE TRIGGER REMOVECLIENT
AFTER DELETE ON CLIENT
DECLARE 
nbCommande integer;
BEGIN
   SELECT COUNT(*) INTO nbCommande
   FROM COMMANDE
   WHERE MAILCLIENT = :old.MAILCLIENT;

   IF (nbCommande = 0) THEN
      raise_application_error(-20001, 'Le client a une commande en cours!');
   END IF;
END;
/      */
