/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 11                       */
/* Created on:     17/01/2019 19:32:16                          */
/*==============================================================*/

drop table Adresse;
drop table AdresseClient;
drop table Agenda;
drop table AlbumPhoto;
drop table Article;
drop table ArticleImpression;
drop table PhotoAlbumPhoto;
drop table PhotoCalendrier;
drop table photoAgenda;
drop table photoTirage;
drop table bureau; 

/*==============================================================*/
/* Table: ADRESSE                                               */
/*==============================================================*/
create table ADRESSE 
(
   ID_ADRESSE           integer                        not null,
   CODEPOSTAL           integer                        not null,
   VILLE                long varchar                   not null,
   NOM_ADRESSE          long varchar                   null,
   PRENOM_ADRESSE       long varchar                   null,
   RUE                  long varchar                   null,
   constraint PK_ADRESSE primary key (ID_ADRESSE)
);

/*==============================================================*/
/* Table: ADRESSECLIENT                                         */
/*==============================================================*/
create table ADRESSECLIENT 
(
   MAILCLIENT           long varchar                   not null,
   ID_ADRESSE           integer                        not null,
   constraint PK_ADRESSECLIENT primary key (MAILCLIENT, ID_ADRESSE)
);

/*==============================================================*/
/* Table: AGENDA                                                */
/*==============================================================*/
create table AGENDA 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   NOPAGEAGENDA         integer                        null,
   constraint PK_AGENDA primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: ALBUMPHOTO                                            */
/*==============================================================*/
create table ALBUMPHOTO 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   ID_PHOTO             integer                        not null,
   TITRE                long varchar                   null,
   QUALITE              long varchar                   null,
   FORMATALBUM          long varchar                   null,
   constraint PK_ALBUMPHOTO primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: ARTICLE                                               */
/*==============================================================*/
create table ARTICLE 
(
   ID_ARTICLE           integer                        not null,
   NUMCOMMANDE          integer                        not null,
   PRIX                 decimal                        not null,
   QUANTITE             integer                        not null,
   constraint PK_ARTICLE primary key (ID_ARTICLE)
);

/*==============================================================*/
/* Table: ARTICLEIMPRESSION                                     */
/*==============================================================*/
create table ARTICLEIMPRESSION 
(
   ID_ARTICLE           integer                        not null,
   NUMIPRESSION         integer                        not null,
   constraint PK_ARTICLEIMPRESSION primary key (ID_ARTICLE, NUMIPRESSION)
);

/*==============================================================*/
/* Table: PHOTOALBUMPHOTO                                        */
/*==============================================================*/
create table PHOTOALBUMPHOTO
(
   NUMIPRESSION         integer                        not null,
   ID_PHOTO             integer                        not null,
   NOPAGEPHOTOALBUMPHOTO integer                        null,
   constraint PK_ASSOCIATION_13 primary key (NUMIPRESSION, ID_PHOTO)
);

/*==============================================================*/
/* Table: PHOTOCALENDRIER                                        */
/*==============================================================*/
create table PHOTOCALENDRIER
(
   NUMIPRESSION         integer                        not null,
   ID_PHOTO             integer                        not null,
   NOPAGEPHOTOCALENDRIER integer                        null,
   constraint PK_ASSOCIATION_14 primary key (NUMIPRESSION, ID_PHOTO)
);

/*==============================================================*/
/* Table: PHOTOAGENDA                                        */
/*==============================================================*/
create table PHOTOAGENDA
(
   NUMIPRESSION         integer                        not null,
   ID_PHOTO             integer                        not null,
   NOPAGEPHOTOAGENDA    integer                        null,
   constraint PK_ASSOCIATION_16 primary key (NUMIPRESSION, ID_PHOTO)
);

/*==============================================================*/
/* Table: PHOTOTIRAGE                                         */
/*==============================================================*/
create table PHOTOTIRAGE
(
   NUMIPRESSION         integer                        not null,
   ID_PHOTO             integer                        not null,
   NBEXEMPLAIRE         integer                        null,
   constraint PK_ASSOCIATION_17 primary key (NUMIPRESSION, ID_PHOTO)
);

/*==============================================================*/
/* Table: BUREAU                                                */
/*==============================================================*/
create table BUREAU 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   constraint PK_BUREAU primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: CADRE                                                 */
/*==============================================================*/
create table CADRE 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   constraint PK_CADRE primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: CALENDRIER                                            */
/*==============================================================*/
create table CALENDRIER 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   constraint PK_CALENDRIER primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: CLIENT                                                */
/*==============================================================*/
create table CLIENT 
(
   MAILCLIENT           long varchar                   not null,
   NOM                  long varchar                   not null,
   PRENOM               long varchar                   not null,
   MOTDEPASSE           long varchar                   not null,
   constraint PK_CLIENT primary key (MAILCLIENT)
);

/*==============================================================*/
/* Table: CODEPROMO                                             */
/*==============================================================*/
create table CODEPROMO 
(
   CODE                 long varchar                   not null,
   ID_CODEPROMO         integer                        not null,
   constraint PK_CODEPROMO primary key (ID_CODEPROMO)
);

/*==============================================================*/
/* Table: CODEPROMOCLIENT                                       */
/*==============================================================*/
create table CODEPROMOCLIENT 
(
   ID_CODEPROMO         integer                        not null,
   MAILCLIENT           long varchar                   not null,
   DEJAUTILISE          smallint                       not null,
   constraint PK_CODEPROMOCLIENT primary key (ID_CODEPROMO, MAILCLIENT)
);

/*==============================================================*/
/* Table: COMMANDE                                              */
/*==============================================================*/
create table COMMANDE 
(
   "DATE"               date                           null,
   MODELIVRAISON        long varchar                   null,
   STATUT_COMMANDE      smallint                       null,
   NUMCOMMANDE          integer                        not null,
   ID_CODEPROMO         integer                        null,
   MAILCLIENT           long varchar                   not null,
   ID_ADRESSE           integer                        null,
   MONTANT              decimal                        not null,
   constraint PK_COMMANDE primary key (NUMCOMMANDE)
);

/*==============================================================*/
/* Table: FICHIERIMAGE                                          */
/*==============================================================*/
create table FICHIERIMAGE 
(
   PATH                 long varchar                   not null,
   PROPRIETAIRE         long varchar                   not null,
   MAILCLIENT           long varchar                   not null,
   INFOPRISEDEVUE       long varchar                   not null,
   RESOLUTION           long varchar                   not null,
   PARTAGE              smallint                       not null,
   DATEACCES            date                           not null,
   constraint PK_FICHIERIMAGE primary key (PATH, PROPRIETAIRE)
);

/*==============================================================*/
/* Table: IMPRESSION                                            */
/*==============================================================*/
create table IMPRESSION 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   constraint PK_IMPRESSION primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: JOURS                                                 */
/*==============================================================*/
create table JOURS 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   NOPAGEAGENDA         integer                        null,
   constraint PK_JOURS primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: MURAL                                                 */
/*==============================================================*/
create table MURAL 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   constraint PK_MURAL primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: PHOTO                                                 */
/*==============================================================*/
create table PHOTO 
(
   ID_PHOTO             integer                        not null,
   PATH                 long varchar                   not null,
   PROPRIETAIRE         long varchar                   not null,
   RETOUCHE             long varchar                   null,
   DESCRIPTION          long varchar                   null,
   constraint PK_PHOTO primary key (ID_PHOTO)
);

/*==============================================================*/
/* Table: SEMAINES                                              */
/*==============================================================*/
create table SEMAINES 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   NOPAGEAGENDA         integer                        null,
   constraint PK_SEMAINES primary key (NUMIPRESSION)
);

/*==============================================================*/
/* Table: STOCK                                                 */
/*==============================================================*/
create table STOCK 
(
   TYPE_IMPRESSION      long varchar                   not null,
   QUANTITESTOCK        integer                        not null,
   IDSTOCK              integer                        not null,
   constraint PK_STOCK primary key (IDSTOCK)
);

/*==============================================================*/
/* Table: STOCKARTICLE                                          */
/*==============================================================*/
create table STOCKARTICLE 
(
   IDSTOCK              integer                        not null,
   ID_ARTICLE           integer                        not null,
   constraint PK_STOCKARTICLE primary key (IDSTOCK, ID_ARTICLE)
);

/*==============================================================*/
/* Table: TIRAGE                                                */
/*==============================================================*/
create table TIRAGE 
(
   NUMIPRESSION         integer                        not null,
   PATH_IMPRESSION      long varchar                   not null,
   QUALITEPAPIER        long varchar                   null,
   FORMATTIRAGE         long varchar                   null,
   constraint PK_TIRAGE primary key (NUMIPRESSION)
);

alter table ADRESSECLIENT
   add constraint FK_ADRESSEC_ADRESSECL_ADRESSE foreign key (ID_ADRESSE)
      references ADRESSE (ID_ADRESSE)
      on update restrict
      on delete restrict;

alter table ADRESSECLIENT
   add constraint FK_ADRESSEC_ADRESSECL_CLIENT foreign key (MAILCLIENT)
      references CLIENT (MAILCLIENT)
      on update restrict
      on delete restrict;

alter table AGENDA
   add constraint FK_AGENDA_INHERITAN_IMPRESSI foreign key (NUMIPRESSION)
      references IMPRESSION (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table ALBUMPHOTO
   add constraint FK_ALBUMPHO_INHERITAN_IMPRESSI foreign key (NUMIPRESSION)
      references IMPRESSION (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table ALBUMPHOTO
   add constraint FK_ALBUMPHO_POSSEDE_P_PHOTO foreign key (ID_PHOTO)
      references PHOTO (ID_PHOTO)
      on update restrict
      on delete restrict;

alter table ARTICLE
   add constraint FK_ARTICLE_COMMANDEA_COMMANDE foreign key (NUMCOMMANDE)
      references COMMANDE (NUMCOMMANDE)
      on update restrict
      on delete restrict;

alter table ARTICLEIMPRESSION
   add constraint FK_ARTICLEI_ARTICLEIM_IMPRESSI foreign key (NUMIPRESSION)
      references IMPRESSION (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table ARTICLEIMPRESSION
   add constraint FK_ARTICLEI_ARTICLEIM_ARTICLE foreign key (ID_ARTICLE)
      references ARTICLE (ID_ARTICLE)
      on update restrict
      on delete restrict;

alter table ALBUMPHOTO
   add constraint FK_ASSOCIAT_ASSOCIATI_ALBUMPHO foreign key (NUMIPRESSION)
      references ALBUMPHOTO (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table ALBUMPHOTO
   add constraint FK_ASSOCIAT_ASSOCIATI_PHOTO foreign key (ID_PHOTO)
      references PHOTO (ID_PHOTO)
      on update restrict
      on delete restrict;

alter table PHOTOCALENDRIER
   add constraint FK_ASSOCIAT_ASSOCIATI_CALENDRI foreign key (NUMIPRESSION)
      references CALENDRIER (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table PHOTOCALENDRIER
   add constraint FK_ASSOCIAT_ASSOCIATI_PHOTO foreign key (ID_PHOTO)
      references PHOTO (ID_PHOTO)
      on update restrict
      on delete restrict;

alter table PHOTOAGENDA
   add constraint FK_ASSOCIAT_ASSOCIATI_AGENDA foreign key (NUMIPRESSION)
      references AGENDA (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table PHOTOAGENDA
   add constraint FK_ASSOCIAT_ASSOCIATI_PHOTO foreign key (ID_PHOTO)
      references PHOTO (ID_PHOTO)
      on update restrict
      on delete restrict;

alter table PHOTOTIRAGE
   add constraint FK_ASSOCIAT_ASSOCIATI_TIRAGE foreign key (NUMIPRESSION)
      references TIRAGE (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table PHOTOTIRAGE
   add constraint FK_ASSOCIAT_ASSOCIATI_PHOTO foreign key (ID_PHOTO)
      references PHOTO (ID_PHOTO)
      on update restrict
      on delete restrict;

alter table BUREAU
   add constraint FK_BUREAU_INHERITAN_CALENDRI foreign key (NUMIPRESSION)
      references CALENDRIER (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table CADRE
   add constraint FK_CADRE_INHERITAN_IMPRESSI foreign key (NUMIPRESSION)
      references IMPRESSION (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table CALENDRIER
   add constraint FK_CALENDRI_INHERITAN_IMPRESSI foreign key (NUMIPRESSION)
      references IMPRESSION (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table CODEPROMOCLIENT
   add constraint FK_CODEPROM_CODEPROMO_CLIENT foreign key (MAILCLIENT)
      references CLIENT (MAILCLIENT)
      on update restrict
      on delete restrict;

alter table CODEPROMOCLIENT
   add constraint FK_CODEPROM_CODEPROMO_CODEPROM foreign key (ID_CODEPROMO)
      references CODEPROMO (ID_CODEPROMO)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_COMMANDEA_ADRESSE foreign key (ID_ADRESSE)
      references ADRESSE (ID_ADRESSE)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_EFFECTUE_CLIENT foreign key (MAILCLIENT)
      references CLIENT (MAILCLIENT)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_PROMOCOMM_CODEPROM foreign key (ID_CODEPROMO)
      references CODEPROMO (ID_CODEPROMO)
      on update restrict
      on delete restrict;

alter table FICHIERIMAGE
   add constraint FK_FICHIERI_IMAGECLIE_CLIENT foreign key (MAILCLIENT)
      references CLIENT (MAILCLIENT)
      on update restrict
      on delete restrict;

alter table JOURS
   add constraint FK_JOURS_INHERITAN_AGENDA foreign key (NUMIPRESSION)
      references AGENDA (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table MURAL
   add constraint FK_MURAL_INHERITAN_CALENDRI foreign key (NUMIPRESSION)
      references CALENDRIER (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table PHOTO
   add constraint FK_PHOTO_CORRESPON_FICHIERI foreign key (PATH, PROPRIETAIRE)
      references FICHIERIMAGE (PATH, PROPRIETAIRE)
      on update restrict
      on delete restrict;

alter table SEMAINES
   add constraint FK_SEMAINES_INHERITAN_AGENDA foreign key (NUMIPRESSION)
      references AGENDA (NUMIPRESSION)
      on update restrict
      on delete restrict;

alter table STOCKARTICLE
   add constraint FK_STOCKART_STOCKARTI_ARTICLE foreign key (ID_ARTICLE)
      references ARTICLE (ID_ARTICLE)
      on update restrict
      on delete restrict;

alter table STOCKARTICLE
   add constraint FK_STOCKART_STOCKARTI_STOCK foreign key (IDSTOCK)
      references STOCK (IDSTOCK)
      on update restrict
      on delete restrict;

alter table TIRAGE
   add constraint FK_TIRAGE_INHERITAN_IMPRESSI foreign key (NUMIPRESSION)
      references IMPRESSION (NUMIPRESSION)
      on update restrict
      on delete restrict;


