create table Client (
    mailClient varchar2(20) PRIMARY KEY,
    nom varchar2(20),
    prenom varchar2(20),
    motDePasse varchar2(20)
);

create table CodePromo(
    idCodePromo number(5) PRIMARY KEY,
    code varchar2(20)
);

create table CodePromoClient(
    idCodePromo number(5) PRIMARY KEY,
    mailClient varchar2(20),
    Utilise boolean,
    constraint pk_cp PRIMARY KEY (idCodePromo, mailClient),
    constraint fk1_cp FOREIGN KEY (idCodePromo) 
    REFERENCES CodePromo(idCodePromo) ON DELETE CASCADE,
    constraint fk2_cp FOREIGN KEY (mailClient)
    REFERENCES Client(mailClient) ON DELETE CASCADE 
);

create table Adresse(
    idAdresse number(5) PRIMARY KEY,
    codePostale number(5),
    ville varchar2(20),
    nomAdresse varchar2(20),
    prenomAdresse varchar2(20)
);

create table Commande(
    numCommande number(5) PRIMARY KEY,
    dateC date,
    modeLivraison varchar2(20),
    statutCommande boolean,
    idCodePromo number(5),
    mailClient varchar2(20),
    idAdresse number(5),
    montant float(5) check (montant >= 0),
    constraint fk1_commande (idCodePromo)
    REFERENCES CodePromo(idCodePromo) ON DELETE CASCADE,
    constraint fk2_commande FOREIGN KEY (mailClient)
    REFERENCES Client(mailClient) ON DELETE CASCADE,
    constraint fk2_commande FOREIGN KEY (idAdresse)
    REFERENCES Adresse(idAdresse) ON DELETE CASCADE
);

create table AdresseClient(
    mailClient varchar2(20),
    idAdresse number(5),
    constraint pk_adresseClient PRIMARY KEY (mailClient, idAdresse),
    constraint fk1_adresseClient FOREIGN KEY (mailClient)
    REFERENCES Client(mailClient) ON DELETE CASCADE,
    constraint fk2_adresseClient FOREIGN KEY (idAdresse)
    REFERENCES Adresse(idAdresse) ON DELETE CASCADE,
);


-- le propriétaire devrait être identifié par son mail non?
create table FichierImage(
    pathImage varchar2(100),
    proprietaire varchar2(20), --???
    mailClient varchar2(20),
    infosPriseDeVue varchar2(20),
    resolution varchar2(20);
    partage boolean,
    dateAcces date,
    constraint pk_fichierImage PRIMARY KEY (pathImage, proprietaire),
    constraint fk1_fichierImage FOREIGN KEY (mailClient)
    REFERENCES Client(mailClient) ON DELETE CASCADE,
);

create table Photo(
    idPhoto number(5) PRIMARY KEY,
    pathImage varchar2(100),
    proprietaire varchar2(20),
    retouche varchar2(20),
    description varchar2(20),
    constraint fk1_photo FOREIGN KEY (pathImage)
    REFERENCES FichierImage(pathImage) ON DELETE CASCADE,
    constraint fk2_photo FOREIGN KEY (proprietaire)
    REFERENCES FichierImage(proprietaire) ON DELETE CASCADE
);

create table Impression(
    numImpression number(5) PRIMARY KEY,
    pathImpression varchar2(100)
);

create table PhotoImpression (
    idPhoto number(5),
    numImpression number(5),
    constraint pk_photoImpression PRIMARY KEY (idPhoto, numImpression),
    constraint fk1_photoImpression FOREIGN KEY (idPhoto)
    REFERENCES Photo(idPhoto) ON DELETE CASCADE,
    constraint fk2_photoImpression FOREIGN KEY (numImpression)
    REFERENCES Impression(numImpression) ON DELETE CASCADE,
);

create table Article(
    idArticle number(5) PRIMARY KEY,
    numCommande number(5),
    prix float(5) check (prix >= 0),
    quantite number(5) check (quantite >= 0),
    constraint fk1_article FOREIGN KEY (numCommande)
    REFERENCES Commande(numCommande) ON DELETE CASCADE
);

create table ArticleImpression(
    idArticle number(5),
    numImpression number(5),
    constraint pk_articleImpression PRIMARY KEY (idArticle, numImpression),
    constraint fk1_articleImpression FOREIGN KEY (idArticle)
    REFERENCES Article(idArticle) ON DELETE CASCADE,
    constraint fk2_articleImpression FOREIGN KEY (numImpression)
    REFERENCES Impression(numImpression) ON DELETE CASCADE
);

/**
reste à faire:
- albumPhoto
- calendrier
- agenda 
- tirage
- bureau
- mural
etc ...
**/