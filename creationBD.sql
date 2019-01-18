/*==============================================================*/
/* DBMS NAME:      SYBASE SQL ANYWHERE 11                       */
/* CREATED ON:     17/01/2019 19:32:16                          */
/*==============================================================*/

DROP TABLE ADRESSECLIENT;
DROP TABLE ARTICLEIMPRESSION;
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
DROP TABLE CADRE;
DROP TABLE CALENDRIER;
DROP TABLE AGENDA;
DROP TABLE ALBUMPHOTO;
DROP TABLE TIRAGE; 

DROP TABLE PHOTO; 
DROP TABLE FICHIERIMAGE;
DROP TABLE ADRESSE;
DROP TABLE CLIENT;
DROP TABLE ARTICLE;
DROP TABLE CODEPROMO;
DROP TABLE COMMANDE;
DROP TABLE IMPRESSION;
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
/* TABLE: COMMANDE                                              */
/*==============================================================*/
CREATE TABLE COMMANDE 
(
   "DATE"               DATE                           NULL,
   MODELIVRAISON        VARCHAR(255)                    NULL,
   STATUT_COMMANDE      SMALLINT                       NULL,
   NUMCOMMANDE          NUMBER                         NOT NULL,
   ID_CODEPROMO         NUMBER                         NULL,
   MAILCLIENT           VARCHAR(255)                    NOT NULL,
   ID_ADRESSE           NUMBER                         NULL,
   MONTANT              DECIMAL                        NOT NULL,
   CONSTRAINT PK_COMMANDE PRIMARY KEY (NUMCOMMANDE)
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
/* TABLE: FICHIERIMAGE                                          */
/*==============================================================*/
CREATE TABLE FICHIERIMAGE 
(
   PATH                 VARCHAR(255)                    NOT NULL,
   PROPRIETAIRE         VARCHAR(255)                    NOT NULL,
   MAILCLIENT           VARCHAR(255)                    NOT NULL,
   INFOPRISEDEVUE       VARCHAR(255)                    NOT NULL,
   RESOLUTION           VARCHAR(255)                    NOT NULL,
   PARTAGE              SMALLINT                       NOT NULL,
   DATEACCES            DATE                           NOT NULL,
   CONSTRAINT PK_FICHIERIMAGE PRIMARY KEY (PATH, PROPRIETAIRE),
   CONSTRAINT FK_FICHIERIMAGE 
      FOREIGN KEY (PROPRIETAIRE) REFERENCES CLIENT(MAILCLIENT)
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
/* TABLE: CODEPROMO                                             */
/*==============================================================*/
CREATE TABLE CODEPROMO 
(
   CODE                 VARCHAR(255)                    NOT NULL,
   ID_CODEPROMO         NUMBER                         NOT NULL,
   CONSTRAINT PK_CODEPROMO PRIMARY KEY (ID_CODEPROMO)
);

/*==============================================================*/
/* TABLE: CODEPROMOCLIENT                                       */
/*==============================================================*/
CREATE TABLE CODEPROMOCLIENT 
(
   ID_CODEPROMO         NUMBER                         NOT NULL,
   MAILCLIENT           VARCHAR(255)                    NOT NULL,
   DEJAUTILISE          SMALLINT                       NOT NULL,
   CONSTRAINT PK_CODEPROMOCLIENT PRIMARY KEY (ID_CODEPROMO, MAILCLIENT),
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
/* TABLE: ARTICLE                                               */
/*==============================================================*/
CREATE TABLE ARTICLE 
(
   ID_ARTICLE           NUMBER                         NOT NULL,
   NUMCOMMANDE          NUMBER                         NOT NULL,
   PRIX                 DECIMAL                        NOT NULL,
   QUANTITE             NUMBER                         NOT NULL,
   CONSTRAINT PK_ARTICLE PRIMARY KEY (ID_ARTICLE),
   CONSTRAINT FK_ARTICLE
      FOREIGN KEY (NUMCOMMANDE) REFERENCES COMMANDE(NUMCOMMANDE)
);

/*==============================================================*/
/* TABLE: STOCK                                                 */
/*==============================================================*/
CREATE TABLE STOCK 
(
   TYPE_IMPRESSION      VARCHAR(255)                    NOT NULL,
   QUANTITESTOCK        NUMBER                         NOT NULL,
   IDSTOCK              NUMBER                         NOT NULL,
   CONSTRAINT PK_STOCK PRIMARY KEY (IDSTOCK)
);

/*==============================================================*/
/* TABLE: STOCKARTICLE                                          */
/*==============================================================*/
CREATE TABLE STOCKARTICLE 
(
   IDSTOCK              NUMBER                         NOT NULL,
   ID_ARTICLE           NUMBER                         NOT NULL,
   CONSTRAINT PK_STOCKARTICLE PRIMARY KEY (IDSTOCK, ID_ARTICLE),
   CONSTRAINT FK_STOCKARTICLE_1
      FOREIGN KEY (IDSTOCK) REFERENCES STOCK(IDSTOCK),
   CONSTRAINT FK_STOCKARTICLE_2
      FOREIGN KEY (ID_ARTICLE) REFERENCES ARTICLE(ID_ARTICLE)
);

/*==============================================================*/
/* TABLE: IMPRESSION                                            */
/*==============================================================*/
CREATE TABLE IMPRESSION 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_IMPRESSION PRIMARY KEY (NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: AGENDA                                                */
/*==============================================================*/
CREATE TABLE AGENDA 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   NOPAGEAGENDA         NUMBER                         NULL,
   CONSTRAINT PK_AGENDA PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_AGENDA
      FOREIGN KEY (NUMIPRESSION) REFERENCES IMPRESSION(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: ALBUMPHOTO                                            */
/*==============================================================*/
CREATE TABLE ALBUMPHOTO 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   TITRE                VARCHAR(255)                    NULL,
   QUALITE              VARCHAR(255)                    NULL,
   FORMATALBUM          VARCHAR(255)                    NULL,
   CONSTRAINT PK_ALBUMPHOTO PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_ALBUMPHOTO
      FOREIGN KEY (NUMIPRESSION) REFERENCES IMPRESSION(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: ARTICLEIMPRESSION                                     */
/*==============================================================*/
CREATE TABLE ARTICLEIMPRESSION 
(
   ID_ARTICLE           NUMBER                         NOT NULL,
   NUMIPRESSION         NUMBER                         NOT NULL,
   CONSTRAINT PK_ARTICLEIMPRESSION PRIMARY KEY (ID_ARTICLE, NUMIPRESSION),
   CONSTRAINT FK_ARTICLEIMPRESSION_1
      FOREIGN KEY (NUMIPRESSION) REFERENCES IMPRESSION(NUMIPRESSION),
   CONSTRAINT FK_ARTICLEIMPRESSION
      FOREIGN KEY (ID_ARTICLE) REFERENCES ARTICLE(ID_ARTICLE)
);

/*==============================================================*/
/* TABLE: CALENDRIER                                            */
/*==============================================================*/
CREATE TABLE CALENDRIER 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_CALENDRIER PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_CALENDRIER
      FOREIGN KEY (NUMIPRESSION) REFERENCES IMPRESSION(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: BUREAU                                                */
/*==============================================================*/
CREATE TABLE BUREAU 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_BUREAU PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_BUREAU
      FOREIGN KEY (NUMIPRESSION) REFERENCES CALENDRIER(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: CADRE                                                 */
/*==============================================================*/
CREATE TABLE CADRE 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_CADRE PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_CADRE
      FOREIGN KEY (NUMIPRESSION) REFERENCES IMPRESSION(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: JOURS                                                 */
/*==============================================================*/
CREATE TABLE JOURS 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   NOPAGEAGENDA         NUMBER                         NULL,
   CONSTRAINT PK_JOURS PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_JOURS
      FOREIGN KEY (NUMIPRESSION) REFERENCES AGENDA(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: MURAL                                                 */
/*==============================================================*/
CREATE TABLE MURAL 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   CONSTRAINT PK_MURAL PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_MURAL
      FOREIGN KEY (NUMIPRESSION) REFERENCES CALENDRIER(NUMIPRESSION)
);


/*==============================================================*/
/* TABLE: SEMAINES                                              */
/*==============================================================*/
CREATE TABLE SEMAINES 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   NOPAGEAGENDA         NUMBER                         NULL,
   CONSTRAINT PK_SEMAINES PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_SEMAINES
      FOREIGN KEY (NUMIPRESSION) REFERENCES AGENDA(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: TIRAGE                                                */
/*==============================================================*/
CREATE TABLE TIRAGE 
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   PATH_IMPRESSION      VARCHAR(255)                    NOT NULL,
   QUALITEPAPIER        VARCHAR(255)                    NULL,
   FORMATTIRAGE         VARCHAR(255)                    NULL,
   CONSTRAINT PK_TIRAGE PRIMARY KEY (NUMIPRESSION),
   CONSTRAINT FK_TIRAGE
      FOREIGN KEY (NUMIPRESSION) REFERENCES IMPRESSION(NUMIPRESSION)
);

/*==============================================================*/
/* TABLE: PHOTOALBUMPHOTO                                        */
/*==============================================================*/
CREATE TABLE PHOTOALBUMPHOTO
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   NOPAGEPHOTOALBUMPHOTO NUMBER                         NULL,
   CONSTRAINT PK_ASSOCIATION_13 PRIMARY KEY (NUMIPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOALBUMPHOTO_1
      FOREIGN KEY (NUMIPRESSION) REFERENCES ALBUMPHOTO(NUMIPRESSION),
   CONSTRAINT FK_PHOTOALBUMPHOTO_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: PHOTOAGENDA                                        */
/*==============================================================*/
CREATE TABLE PHOTOAGENDA
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   NOPAGEPHOTOAGENDA    NUMBER                         NULL,
   CONSTRAINT PK_ASSOCIATION_16 PRIMARY KEY (NUMIPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOAGENDA_1
      FOREIGN KEY (NUMIPRESSION) REFERENCES AGENDA(NUMIPRESSION),
   CONSTRAINT FK_PHOTOAGENDA_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: PHOTOTIRAGE                                         */
/*==============================================================*/
CREATE TABLE PHOTOTIRAGE
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   NBEXEMPLAIRE         NUMBER                         NULL,
   CONSTRAINT PK_ASSOCIATION_17 PRIMARY KEY (NUMIPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOTIRAGE_1
      FOREIGN KEY (NUMIPRESSION) REFERENCES TIRAGE(NUMIPRESSION),
   CONSTRAINT FK_PHOTOTIRAGE_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

/*==============================================================*/
/* TABLE: PHOTOCALENDRIER                                        */
/*==============================================================*/
CREATE TABLE PHOTOCALENDRIER
(
   NUMIPRESSION         NUMBER                         NOT NULL,
   ID_PHOTO             NUMBER                         NOT NULL,
   NOPAGEPHOTOCALENDRIER NUMBER                         NULL,
   CONSTRAINT PK_ASSOCIATION_14 PRIMARY KEY (NUMIPRESSION, ID_PHOTO),
   CONSTRAINT FK_PHOTOCALENDRIER_1
      FOREIGN KEY (NUMIPRESSION) REFERENCES CALENDRIER(NUMIPRESSION),
   CONSTRAINT FK_PHOTOCALENDRIER_2
      FOREIGN KEY (ID_PHOTO) REFERENCES PHOTO(ID_PHOTO)
);

