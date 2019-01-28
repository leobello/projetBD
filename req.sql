/*==============================================================*/
/* DBMS NAME:      SYBASE SQL ANYWHERE 11                       */
/* CREATED ON:     17/01/2019 19:32:16                          */
/*==============================================================*/
/*
REQUETE PERMETTANT DE VOIS LES TABLES LIÉES PAR FOREIGN KEY
SELECT TABLE_NAME,CONSTRAINT_NAME FROM USER_CONSTRAINTS 
WHERE R_CONSTRAINT_NAME IN (SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS WHERE TABLE_NAME='NOM DE LA TABLE A SUPPRIMER') AND CONSTRAINT_TYPE='R'; 
*/

/*
RÈGLES MÉTIER : 
10 STOCKS DISPONIBLES
PRIX :   QUALITE MOYENNE -> 10
         QUALITE SUPERIEURE -> 15
*/


DROP TABLE ADRESSECLIENT;
DROP TABLE PHOTOALBUM;
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
DROP TABLE PHOTOCADRE ;
DROP TABLE CADRE;

DROP TABLE CALENDRIER;
DROP TABLE AGENDA;
DROP TABLE ALBUM;
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
   POURCENTAGE          DECIMAL                        NOT NULL,
   TYPE_CODEPROMO       VARCHAR(255)                   NOT NULL,
   CONSTRAINT PK_CODEPROMO PRIMARY KEY (CODE),
   CONSTRAINT CK_CODEPROMO
      CHECK (TYPE_CODEPROMO='GENERAL' OR TYPE_CODEPROMO='PERSONNEL')
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
   ACTIF                NUMBER                          NOT NULL,
   CONSTRAINT PK_CLIENT PRIMARY KEY (MAILCLIENT),
   CONSTRAINT CK_CLIENT
      CHECK (ACTIF = 1 OR ACTIF = 0)
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
   CODEPROMO            VARCHAR(255)                   NULL,
   MAILCLIENT           VARCHAR(255)                   NOT NULL,
   PRIXTOTAL            NUMBER                         NOT NULL,
   CONSTRAINT PK_COMMANDE PRIMARY KEY (NUMCOMMANDE),
   CONSTRAINT FK_COMMANDE_1 
      FOREIGN KEY (CODEPROMO) REFERENCES CODEPROMO(CODE) ,
   CONSTRAINT FK_COMMANDE_2 
      FOREIGN KEY (MAILCLIENT) REFERENCES CLIENT(MAILCLIENT) ON DELETE CASCADE ,
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
   CONSTRAINT CK_FICHIERIMAGE_1
      CHECK (RESOLUTION='FAIBLE' OR RESOLUTION='MOYENNE' OR RESOLUTION='HAUTE'),
   CONSTRAINT CK_FICHIERIMAGE_2
      CHECK (PARTAGE=0 OR PARTAGE=1)
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
   CODE                 VARCHAR(255)                   NOT NULL,
   MAILCLIENT           VARCHAR(255)                   NOT NULL,
   DEJAUTILISE          SMALLINT                       NOT NULL,
   CONSTRAINT PK_CODEPROMOCLIENT PRIMARY KEY (CODE, MAILCLIENT),
   CONSTRAINT CK_CODEPROMOCLIENT CHECK (DEJAUTILISE<=1 OR DEJAUTILISE>=0),
   CONSTRAINT FK_CODEPROMOCLIENT_1
      FOREIGN KEY (CODE) REFERENCES CODEPROMO(CODE),
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
   MAILCLIENT            VARCHAR(255)                   NOT NULL,
   PATH_IMPRESSION       VARCHAR(255)                   NOT NULL,
   IMPRESSION_OK         NUMBER                         NOT NULL,
   CONSTRAINT PK_IMPRESSION PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_IMPRESSION
      FOREIGN KEY (MAILCLIENT) REFERENCES CLIENT(MAILCLIENT)
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
   NUMIMPRESSION         NUMBER                        NOT NULL,
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
   QUALITE              VARCHAR(255)                   NOT NULL,
   FORMAT               VARCHAR(255)                   NOT NULL,
   QUANTITESTOCK        NUMBER                         NOT NULL,
   CONSTRAINT PK_STOCK PRIMARY KEY (TYPE_IMPRESSION, QUALITE, FORMAT),
   CONSTRAINT CK_STOCK_1 
      CHECK (TYPE_IMPRESSION='SEMAINES' OR TYPE_IMPRESSION='JOURS' OR TYPE_IMPRESSION='TIRAGE' OR TYPE_IMPRESSION='CADRE' OR TYPE_IMPRESSION='MURAL' OR TYPE_IMPRESSION='BUREAU'),
   CONSTRAINT CK_STOCK_2
      CHECK (QUALITE='MOYENNE' OR QUALITE='SUPERIEURE'),
   CONSTRAINT CK_STOCK_3
      CHECK (FORMAT='A4' OR FORMAT='A5')
);

/*==============================================================*/
/* TABLE: STOCKARTICLE                                          */
/*==============================================================*/
CREATE TABLE STOCKARTICLE
(
   TYPE_IMPRESSION      VARCHAR(255)                   NOT NULL,
   QUALITE              VARCHAR(255)                   NOT NULL,
   FORMAT               VARCHAR(255)                   NOT NULL,
   ID_ARTICLE           NUMBER                         NOT NULL,
   CONSTRAINT PK_STOCKARTICLE PRIMARY KEY (TYPE_IMPRESSION, QUALITE, ID_ARTICLE, FORMAT),
   CONSTRAINT FK_STOCKARTICLE_1
      FOREIGN KEY (TYPE_IMPRESSION, QUALITE, FORMAT) REFERENCES STOCK(TYPE_IMPRESSION, QUALITE, FORMAT),
   CONSTRAINT FK_STOCKARTICLE_2
      FOREIGN KEY (ID_ARTICLE) REFERENCES ARTICLE(ID_ARTICLE),
   CONSTRAINT CK_STOCKARTICLE_1
      CHECK (TYPE_IMPRESSION='SEMAINES' OR TYPE_IMPRESSION='JOURS' OR TYPE_IMPRESSION='TIRAGE' OR TYPE_IMPRESSION='CADRE' OR TYPE_IMPRESSION='MURAL' OR TYPE_IMPRESSION='BUREAU'),
   CONSTRAINT CK_STOCKARTICLE_2
      CHECK (QUALITE='MOYENNE' OR QUALITE='SUPERIEURE'),
   CONSTRAINT CK_STOCKARTICLE_3
      CHECK (FORMAT='A4' OR FORMAT='A5')
);

/*==============================================================*/
/* TABLE: AGENDA                                                */
/*==============================================================*/
CREATE TABLE AGENDA 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   NOPAGEAGENDA          NUMBER                         NOT NULL,
   QUALITE              VARCHAR(255)                    NULL,
   FORMAT               VARCHAR(255)                    NULL,
   CONSTRAINT PK_AGENDA PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_AGENDA
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_AGENDA_1
      CHECK (QUALITE='MOYENNE' OR QUALITE='SUPERIEURE'),
   CONSTRAINT CK_AGENDA_2
      CHECK (FORMAT='A4' OR FORMAT='A5')
);

/*==============================================================*/
/* TABLE: ALBUM                                            */
/*==============================================================*/
CREATE TABLE ALBUM 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                          NULL,
   TITRE                VARCHAR(255)                    NULL,
   QUALITE              VARCHAR(255)                    NULL,
   FORMAT               VARCHAR(255)                    NULL,
   CONSTRAINT PK_ALBUM PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_ALBUM
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_ALBUM_1
      CHECK (QUALITE='MOYENNE' OR QUALITE='SUPERIEURE'),
   CONSTRAINT CK_ALBUM_2
      CHECK (FORMAT='A4' OR FORMAT='A5')
);

/*==============================================================*/
/* TABLE: CALENDRIER                                            */
/*==============================================================*/
CREATE TABLE CALENDRIER 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   QUALITE              VARCHAR(255)                    NULL,
   FORMAT               VARCHAR(255)                    NULL,
   CONSTRAINT PK_CALENDRIER PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_CALENDRIER
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_CALENDRIER_1
      CHECK (QUALITE='MOYENNE' OR QUALITE='SUPERIEURE'),
   CONSTRAINT CK_CALENDRIER_2
      CHECK (FORMAT='A4' OR FORMAT='A5')
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
   MODELE                VARCHAR(255)                   NOT NULL,
   TAILLE                VARCHAR(255)                   NOT NULL,
   QUALITE               VARCHAR(255)                   NOT NULL,
   CONSTRAINT PK_CADRE PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_CADRE
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_CADRE_1
      CHECK (MODELE='PAYSAGE' OR MODELE='PORTRAIT'),
   CONSTRAINT CK_CADRE_2
      CHECK (TAILLE='A4' OR TAILLE='A5'),
   CONSTRAINT CK_CADRE_3
      CHECK (QUALITE='MOYENNE' OR QUALITE='SUPERIEURE')
);

/*==============================================================*/
/* TABLE: PHOTOCADRE                                            */
/*==============================================================*/
CREATE TABLE PHOTOCADRE 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO              NUMBER                         NOT NULL,
   CONSTRAINT PK_PHOTOCADRE PRIMARY KEY (NUMIMPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOCADRE_1
      FOREIGN KEY (NUMIMPRESSION) REFERENCES CADRE(NUMIMPRESSION),
   CONSTRAINT FK_PHOTOCADRE_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: JOURS                                                 */
/*==============================================================*/
CREATE TABLE JOURS 
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   NOPAGEAGENDA          NUMBER                         NULL,
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
   NOPAGEAGENDA          NUMBER                         NULL,
   CONSTRAINT PK_SEMAINES PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_SEMAINES
      FOREIGN KEY (NUMIMPRESSION) REFERENCES AGENDA(NUMIMPRESSION)
);

/*==============================================================*/
/* TABLE: TIRAGE                                                */
/*==============================================================*/
CREATE TABLE TIRAGE 
(
   NUMIMPRESSION        NUMBER                          NOT NULL,
   QUALITEPAPIER        VARCHAR(255)                    NULL,
   FORMATTIRAGE         VARCHAR(255)                    NULL,
   CONSTRAINT PK_TIRAGE PRIMARY KEY (NUMIMPRESSION),
   CONSTRAINT FK_TIRAGE
      FOREIGN KEY (NUMIMPRESSION) REFERENCES IMPRESSION(NUMIMPRESSION),
   CONSTRAINT CK_TIRAGE_1
      CHECK ( QUALITEPAPIER='MOYENNE' OR QUALITEPAPIER='SUPERIEURE'), 
   CONSTRAINT CK_TIRAGE_2
      CHECK ( FORMATTIRAGE='A4' OR FORMATTIRAGE='A5' ) 
);

/*==============================================================*/
/* TABLE: PHOTOALBUM                                        */
/*==============================================================*/
CREATE TABLE PHOTOALBUM
(
   NUMIMPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   NOPAGEPHOTOALBUM NUMBER                         NULL,
   CONSTRAINT PK_PHOTOALBUM_13 PRIMARY KEY (NUMIMPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOALBUM_1
      FOREIGN KEY (NUMIMPRESSION) REFERENCES ALBUM(NUMIMPRESSION),
   CONSTRAINT FK_PHOTOALBUM_2
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

INSERT INTO CODEPROMO VALUES ('AKJFKJDDFDFZ545D564S5Z64S64', 12.5, 'GENERAL');
INSERT INTO CODEPROMO VALUES ('AKJFKJJDHFJHKJSF545666SDF', 5.3, 'PERSONNEL');
INSERT INTO CODEPROMO VALUES ('AKJSDFSSD4F51Z545D564S5Z64S64', 10, 'PERSONNEL');
INSERT INTO CODEPROMO VALUES ('SDFS51DFS1DF45D564S5Z64S64', 15.2, 'PERSONNEL');
INSERT INTO CODEPROMO VALUES ('SQ5D4SD1FDFZ545D564S5Z64S64', 13.2, 'GENERAL');
INSERT INTO CODEPROMO VALUES ('S5FSF6SDF545D564S5Z64S64', 25, 'GENERAL');

INSERT INTO CLIENT VALUES ('LREOOF@ORANGE.FR', 'JEAN', 'PETIT', 'SDF545', 1);
INSERT INTO CLIENT VALUES ('LOUISR@ORANGE.FR', 'LOUIS', 'GRAND', 'DFGGD545', 1);
INSERT INTO CLIENT VALUES ('WILLY@ORANGE.FR', 'WILLIAMS', 'ARTO', 'SDFGDFG5', 1);
INSERT INTO CLIENT VALUES ('WORST@YAHOO.FR', 'JEAN', 'MECHANT', 'DFSDFF545', 1);
INSERT INTO CLIENT VALUES ('JEAP@GMAIL.COM', 'LOUIS', 'LAVOIT', 'SDFDF54SDFSDF', 1);

INSERT INTO COMMANDE VALUES ('2019-01-20', 'ADRESSE', 'EN COURS', 1,  NULL ,'LREOOF@ORANGE.FR', 50 );
INSERT INTO COMMANDE VALUES ('2019-01-15', 'ADRESSE', 'EN COURS', 2, 'AKJSDFSSD4F51Z545D564S5Z64S64','WILLY@ORANGE.FR', 25 );
INSERT INTO COMMANDE VALUES ('2019-01-01', 'ADRESSE', 'EN COURS', 3, 'S5FSF6SDF545D564S5Z64S64','WILLY@ORANGE.FR', 52);
INSERT INTO COMMANDE VALUES ('2019-01-12', 'ADRESSE', 'EN COURS', 4, 'SQ5D4SD1FDFZ545D564S5Z64S64','JEAP@GMAIL.COM', 26);
INSERT INTO COMMANDE VALUES ('2019-01-12', 'ADRESSE', 'EN COURS', 5, NULL,'JEAP@GMAIL.COM', 45);

INSERT INTO COMMANDEADRESSE VALUES (1, 1);
INSERT INTO COMMANDEADRESSE VALUES (3, 5);
INSERT INTO COMMANDEADRESSE VALUES (2, 4); 
INSERT INTO COMMANDEADRESSE VALUES (4, 3);

INSERT INTO FICHIERIMAGE VALUES ('/DSF/SFF','JEAP@GMAIL.COM', 'VUE DE FACE', 'MOYENNE', 0, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/DSDF/SDFFF/SDF','LOUISR@ORANGE.FR', 'CONTREPLONGEE', 'HAUTE', 0, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/ZER/DSF','LOUISR@ORANGE.FR', 'PLONGEE', 'HAUTE', 0, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/ZETT/ERT','WILLY@ORANGE.FR', 'FACE', 'HAUTE', 0, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/HGJ/DG','JEAP@GMAIL.COM', 'PLAN', 'FAIBLE', 0, '2019-01-15');
INSERT INTO FICHIERIMAGE VALUES ('/AZD/DFGD','LREOOF@ORANGE.FR', 'PLONGEE', 'FAIBLE', 0, '2019-01-15');

INSERT INTO ADRESSECLIENT VALUES ('JEAP@GMAIL.COM', 1 );
INSERT INTO ADRESSECLIENT VALUES ('WILLY@ORANGE.FR', 6 );
INSERT INTO ADRESSECLIENT VALUES ('JEAP@GMAIL.COM', 7 );
INSERT INTO ADRESSECLIENT VALUES ('JEAP@GMAIL.COM', 5 );
INSERT INTO ADRESSECLIENT VALUES ('WORST@YAHOO.FR', 3 );

INSERT INTO CODEPROMOCLIENT VALUES ('AKJFKJDDFDFZ545D564S5Z64S64', 'WILLY@ORANGE.FR', 0);
INSERT INTO CODEPROMOCLIENT VALUES ('AKJFKJJDHFJHKJSF545666SDF', 'WILLY@ORANGE.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES ('AKJSDFSSD4F51Z545D564S5Z64S64', 'WORST@YAHOO.FR', 0);
INSERT INTO CODEPROMOCLIENT VALUES ('SDFS51DFS1DF45D564S5Z64S64', 'WORST@YAHOO.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES ('SQ5D4SD1FDFZ545D564S5Z64S64', 'JEAP@GMAIL.COM', 0);
INSERT INTO CODEPROMOCLIENT VALUES ('SQ5D4SD1FDFZ545D564S5Z64S64', 'LREOOF@ORANGE.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES ('SQ5D4SD1FDFZ545D564S5Z64S64', 'LOUISR@ORANGE.FR', 1);
INSERT INTO CODEPROMOCLIENT VALUES ('S5FSF6SDF545D564S5Z64S64', 'JEAP@GMAIL.COM', 0);
INSERT INTO CODEPROMOCLIENT VALUES ('S5FSF6SDF545D564S5Z64S64', 'LREOOF@ORANGE.FR', 0);

INSERT INTO PHOTO VALUES (1, '/ZETT/ERT','WILLY@ORANGE.FR', 'FILTRE BLEU', 'PHOTO PRISE AU SAHARA' );
INSERT INTO PHOTO VALUES (2, '/ZETT/ERT','WILLY@ORANGE.FR', 'FILTRE VIOLET', 'PHOTO PRISE AU KENTUKI' );
INSERT INTO PHOTO VALUES (3, '/DSDF/SDFFF/SDF','LOUISR@ORANGE.FR', 'FILTRE GROSSISSANT', 'PHOTO AVEC NARUTO' );
INSERT INTO PHOTO VALUES (4, '/DSF/SFF','JEAP@GMAIL.COM', 'FILTRE ASSAINISSANT', 'PHOTO PRISE A PUNK HASARD' );
INSERT INTO PHOTO VALUES (5, '/AZD/DFGD','LREOOF@ORANGE.FR', 'FILTRE GRIS', 'PHOTO PRISE A DYSNEY AVEC MAMAN ET MAMIE' );
INSERT INTO PHOTO VALUES (6, '/DSF/SFF','JEAP@GMAIL.COM', 'FILTRE ASSAINISSANT BLEU', 'PHOTO PRISE A PUNK HASARD' );
INSERT INTO PHOTO VALUES (7, '/DSDF/SDFFF/SDF','LOUISR@ORANGE.FR', 'FILTRE GROSSISSANT VIOLET', 'PHOTO AVEC NARUTO' );
INSERT INTO PHOTO VALUES (8, '/DSDF/SDFFF/SDF','LOUISR@ORANGE.FR', 'FILTRE GROSSISSANT ROUGE', 'PHOTO AVEC NARUTO' );

INSERT INTO IMPRESSION VALUES (1,'JEAP@GMAIL.COM', '/ADRF/FDG/SDF',1 );
INSERT INTO IMPRESSION VALUES (2,'JEAP@GMAIL.COM', '/FGH/FFGHG/SDF',1 );
INSERT INTO IMPRESSION VALUES (3,'JEAP@GMAIL.COM', '/ADRF/FFHRRG/SDF',1 );
INSERT INTO IMPRESSION VALUES (4,'JEAP@GMAIL.COM', '/ADRF/DFREG/SDF',1 );
INSERT INTO IMPRESSION VALUES (5,'LOUISR@ORANGE.FR', '/ADFGYTF/FDG/GHF',0 );
INSERT INTO IMPRESSION VALUES (6,'LOUISR@ORANGE.FR', '/ADRF/FDG/GHYTH',1 );
INSERT INTO IMPRESSION VALUES (7,'LOUISR@ORANGE.FR', '/ADRF/FDG/FGHUYJKGH',0 );
INSERT INTO IMPRESSION VALUES (8,'LOUISR@ORANGE.FR','/ADFGHU/FDFGH/SFGHUDF',0 );
INSERT INTO IMPRESSION VALUES (9,'WILLY@ORANGE.FR', '/AD/G/F',1 );
INSERT INTO IMPRESSION VALUES (10,'WILLY@ORANGE.FR', '/A/FDG/GTH',0 );
INSERT INTO IMPRESSION VALUES (11,'WILLY@ORANGE.FR', '/ADRF/F/KGH',1 );
INSERT INTO IMPRESSION VALUES (12,'WILLY@ORANGE.FR', '/AU/FD/HUDF',1 );
INSERT INTO IMPRESSION VALUES (13,'LOUISR@ORANGE.FR', '/AGYTF/DG/GF',0 );
INSERT INTO IMPRESSION VALUES (14,'LOUISR@ORANGE.FR', '/ADRF/FDG/GH',1 );
INSERT INTO IMPRESSION VALUES (15,'LREOOF@ORANGE.FR', '/AF/FG/FH',0 );
INSERT INTO IMPRESSION VALUES (16,'LREOOF@ORANGE.FR', '/DFU/FH/HUDF',1 );

INSERT INTO ARTICLE VALUES (1, 1, 15.25, 5, 1);
INSERT INTO ARTICLE VALUES (2, 2, 15.25, 5, 2);
INSERT INTO ARTICLE VALUES (3, 2, 15.25, 5, 2);
INSERT INTO ARTICLE VALUES (4, 5, 15.25, 5, 3);
INSERT INTO ARTICLE VALUES (5, 5, 15.25, 5, 3);
INSERT INTO ARTICLE VALUES (6, 3, 15.25, 5, 4);
INSERT INTO ARTICLE VALUES (7, 4, 15.25, 5, 5);

/*
ON A EN STOCK DE QUOI FAIRE 154 AGENDA SEMAINES DE QUALITÉ MOYENNE
*/
INSERT INTO STOCK VALUES ('SEMAINES','MOYENNE','A4', 154);
INSERT INTO STOCK VALUES ('JOURS','MOYENNE','A4', 145);
INSERT INTO STOCK VALUES ('TIRAGE','MOYENNE','A4', 255);
INSERT INTO STOCK VALUES ('CADRE','MOYENNE','A4', 425);
INSERT INTO STOCK VALUES ('MURAL','MOYENNE','A4', 154);
INSERT INTO STOCK VALUES ('BUREAU','MOYENNE','A4', 215);
INSERT INTO STOCK VALUES ('SEMAINES','SUPERIEURE','A4', 154);
INSERT INTO STOCK VALUES ('JOURS','SUPERIEURE','A4', 145);
INSERT INTO STOCK VALUES ('TIRAGE','SUPERIEURE','A4', 155);
INSERT INTO STOCK VALUES ('CADRE','SUPERIEURE','A4', 125);
INSERT INTO STOCK VALUES ('MURAL','SUPERIEURE','A4', 154);
INSERT INTO STOCK VALUES ('BUREAU','SUPERIEURE','A4', 115);

INSERT INTO STOCK VALUES ('SEMAINES','MOYENNE','A5', 154);
INSERT INTO STOCK VALUES ('JOURS','MOYENNE','A5', 145);
INSERT INTO STOCK VALUES ('TIRAGE','MOYENNE','A5', 255);
INSERT INTO STOCK VALUES ('CADRE','MOYENNE','A5', 425);
INSERT INTO STOCK VALUES ('MURAL','MOYENNE','A5', 154);
INSERT INTO STOCK VALUES ('BUREAU','MOYENNE','A5', 215);
INSERT INTO STOCK VALUES ('SEMAINES','SUPERIEURE','A5', 154);
INSERT INTO STOCK VALUES ('JOURS','SUPERIEURE','A5', 145);
INSERT INTO STOCK VALUES ('TIRAGE','SUPERIEURE','A5', 155);
INSERT INTO STOCK VALUES ('CADRE','SUPERIEURE','A5', 125);
INSERT INTO STOCK VALUES ('MURAL','SUPERIEURE','A5', 154);
INSERT INTO STOCK VALUES ('BUREAU','SUPERIEURE','A5', 115);

INSERT INTO STOCKARTICLE VALUES ('SEMAINES','SUPERIEURE','A4', 1 );
INSERT INTO STOCKARTICLE VALUES ('SEMAINES','SUPERIEURE','A4', 2 );
INSERT INTO STOCKARTICLE VALUES ('SEMAINES','MOYENNE','A4', 3 );
INSERT INTO STOCKARTICLE VALUES ('MURAL','MOYENNE','A5', 1 );
INSERT INTO STOCKARTICLE VALUES ('MURAL','MOYENNE','A5', 2 );
INSERT INTO STOCKARTICLE VALUES ('TIRAGE','MOYENNE','A5', 5 );

INSERT INTO AGENDA VALUES (1,52, 'SUPERIEURE', 'A5');
INSERT INTO AGENDA VALUES (2,20, 'MOYENNE', 'A5');

INSERT INTO ALBUM VALUES (3, 4,'PHOTO DE FAMILLE', 'MOYENNE', 'A4');
INSERT INTO ALBUM VALUES (4, 4,'PHOTO DE LA FERME', 'SUPERIEURE', 'A5');

INSERT INTO CALENDRIER VALUES (5, 'SUPERIEURE', 'A4');
INSERT INTO CALENDRIER VALUES (6, 'SUPERIEURE', 'A4');
INSERT INTO CALENDRIER VALUES (7, 'MOYENNE', 'A5');
INSERT INTO CALENDRIER VALUES (8, 'MOYENNE', 'A5');

INSERT INTO BUREAU VALUES (5);
INSERT INTO BUREAU VALUES (6);

INSERT INTO CADRE VALUES (9, 'PAYSAGE', 'A4', 'MOYENNE');
INSERT INTO CADRE VALUES (10, 'PORTRAIT', 'A5', 'MOYENNE');

INSERT INTO JOURS VALUES (1,365);

INSERT INTO MURAL VALUES (5);
INSERT INTO MURAL VALUES (6);

INSERT INTO SEMAINES VALUES (2,52);

INSERT INTO TIRAGE VALUES ( 11, 'SUPERIEURE', 'A4' );
INSERT INTO TIRAGE VALUES ( 12, 'MOYENNE', 'A4' );

INSERT INTO PHOTOALBUM VALUES (3, 3, 1);
INSERT INTO PHOTOALBUM VALUES (3, 4, 1);
INSERT INTO PHOTOALBUM VALUES (4, 3, 1);

INSERT INTO PHOTOAGENDA VALUES (1, 4, 1 );
INSERT INTO PHOTOAGENDA VALUES (1, 6, 2 );
INSERT INTO PHOTOAGENDA VALUES (2, 4, 1 );
INSERT INTO PHOTOAGENDA VALUES (2, 6, 2 );

INSERT INTO PHOTOTIRAGE VALUES (11, 1, 2);
INSERT INTO PHOTOTIRAGE VALUES (11, 2, 2);
INSERT INTO PHOTOTIRAGE VALUES (12, 2, 2);
INSERT INTO PHOTOTIRAGE VALUES (12, 1, 4);

INSERT INTO PHOTOCALENDRIER VALUES (5, 3, 1 );
INSERT INTO PHOTOCALENDRIER VALUES (5, 7, 1 );
INSERT INTO PHOTOCALENDRIER VALUES (5, 8, 2 );
INSERT INTO PHOTOCALENDRIER VALUES (6, 3, 1 );
INSERT INTO PHOTOCALENDRIER VALUES (6, 7, 2 );
INSERT INTO PHOTOCALENDRIER VALUES (6, 8, 1 );
INSERT INTO PHOTOCALENDRIER VALUES (7, 3, 1 );
INSERT INTO PHOTOCALENDRIER VALUES (8, 7, 1 );

INSERT INTO PHOTOCADRE VALUES (9, 1);
INSERT INTO PHOTOCADRE VALUES (9, 2);

-- VÉRIFIER QU’IL N’Y A PAS DE COMMANDE EN COURS LORS DE LA SUPPRESSION D’UN CLIENT

CREATE OR REPLACE TRIGGER REMOVECLIENT
AFTER DELETE ON CLIENT
FOR EACH ROW
DECLARE 
NBCOMMANDE INTEGER;
BEGIN
   SELECT COUNT(*) INTO NBCOMMANDE
   FROM COMMANDE
   WHERE MAILCLIENT = :OLD.MAILCLIENT AND STATUT_COMMANDE = 'EN COURS';

   IF (NBCOMMANDE = 0) THEN
      RAISE_APPLICATION_ERROR(-20001, 'LE CLIENT A UNE COMMANDE EN COURS!');
   END IF;
END;
/

/*** test ***/
/*
INSERT INTO CLIENT VALUES ('LEOBELLO.WD@GMAIL.COM','BELLO', 'LEO', 'HAHAHA', 1);
INSERT INTO COMMANDE VALUES ('2O19-01-28', 'ADRESSE', 'EN COURS', 10, NULL, 'LEOBELLO.WD@GMAIL.COM', 10);
DELETE FROM CLIENT WHERE MAILCLIENT = 'LEOBELLO.WD@GMAIL.COM';
*/

-- EMPECHER LA CREATION D'UNE COMMANDE AVEC UN CODE PROMO GENERAL UTILISÉ
CREATE OR REPLACE TRIGGER CODEUNIQUE
AFTER INSERT OR UPDATE ON COMMANDE
FOR EACH ROW
DECLARE
codeDejaUtilisee INTEGER;
BEGIN
   SELECT COUNT(*) INTO codeDejaUtilisee
   FROM (SELECT CP.CODE, DEJAUTILISE, TYPE_CODEPROMO, MAILCLIENT
         FROM CODEPROMOCLIENT CC
         JOIN CODEPROMO CP ON CC.CODE = CP.CODE )
   WHERE CODE = :NEW.CODEPROMO
   AND DEJAUTILISE = 1
   AND TYPE_CODEPROMO = 'GENERAL'
   AND MAILCLIENT = :NEW.MAILCLIENT;

   IF (codeDejaUtilisee > 0) THEN
      RAISE_APPLICATION_ERROR(-20001, 'CODE PROMMOTIONEL DÉJÀ UTILISÉ');
   END IF;
END;
/

-- EMPECHER LA CREATION D'UNE COMMANDE AVEC UN CODE PROMO PERSONNEL UTILISÉ
CREATE OR REPLACE TRIGGER CODEUNIQUE
AFTER INSERT OR UPDATE ON COMMANDE
FOR EACH ROW
DECLARE
codeDejaUtilisee INTEGER;
BEGIN
   SELECT COUNT(*) INTO codeDejaUtilisee
   FROM (SELECT CP.CODE, DEJAUTILISE, TYPE_CODEPROMO
         FROM CODEPROMOCLIENT CC
         JOIN CODEPROMO CP ON CC.CODE = CP.CODE )
   WHERE CODE = :NEW.CODEPROMO
   AND DEJAUTILISE = 1
   AND TYPE_CODEPROMO = 'PERSONNEL';

   IF (codeDejaUtilisee > 0) THEN
      RAISE_APPLICATION_ERROR(-20001, 'CODE PROMMOTIONEL DÉJÀ UTILISÉ');
   END IF;
END;
/

/*
-- EMPÊCHER LA CRÉATION D’UNE COMMANDE AVEC UN CODE PROMO 
-- SI LE CODE PROMO APPARTIENT À UN AUTRE CLIENT.
CREATE OR REPLACE TRIGGER CODEUNIQUE
AFTER INSERT OR UPDATE ON COMMANDE
FOR EACH ROW
DECLARE
codeCPartage INTEGER;
BEGIN
   SELECT COUNT(*) INTO codeCPartage 
   FROM (SELECT CC.MAILCLIENT, CP.CODE, CP.TYPE_CODEPROMO
         FROM CODEPROMOCLIENT CC
         JOIN CODEPROMO CP ON CC.CODE = CP.CODE )
   WHERE CODE = :NEW.CODEPROMO
   AND MAILCLIENT != :NEW.MAILCLIENT
   AND TYPE_CODEPROMO = 'PERSONNEL';

   IF (codeCPartage > 0) THEN
      RAISE_APPLICATION_ERROR(-20001, 'CODE PROMMOTIONEL INVALIDE');
   END IF;
END;
/
*/