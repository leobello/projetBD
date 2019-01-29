-- EMPÊCHER LA CRÉATION D’UNE COMMANDE AVEC UN CODE PROMO SI LE CODE PROMO UTILISÉ N’APPARTIENT PAS À CE CLIENT.
 
CREATE OR REPLACE TRIGGER CODEPROMO2
AFTER INSERT OR UPDATE ON COMMANDE
FOR EACH ROW
DECLARE
MATCHINGCP INTEGER;
BEGIN
   SELECT COUNT(*) INTO MATCHINGCP 
   FROM CODEPROMOCLIENT
   WHERE MAILCLIENT = :NEW.MAILCLIENT
   AND CODE = :NEW.CODE;

   IF (MATCHINGCP = 0) THEN 
      RAISE_APPLICATION_ERROR(-20001, 'CODE PROMMOTIONEL INVALIDE');
   END IF;
END;
/
/*
VÉRIFIER QUE QUAND LA COMMANDE CRÉÉE PEUT L'ÊTRE
SURVEILLER LA TABLE COMMANDE
ARTICLE( NUMCOMMANDE, IDARTICLE , NUMIMPRESSION)


CREATE OR REPLACE TRIGGER CHECK_COMMANDE_MURAL
AFTER INSERT OR UPDATE ON COMMANDE
DECLARE 
   NB_COMMANDE INTEGER;
   STOCK_COMMANDE INTEGER;
BEGIN 

SELECT QUANTITE
FROM ARTICLE NATURAL JOIN COMMANDE NATURAL JOIN IMPRESSION NATURAL JOIN JOURS


SELECT QUANTITESTOCK INTO STOCK_COMMANDE
FROM STOCK 
WHERE TYPE_IMPRESSION='MURAL';
*/

/*
UNE COMMANDE QUI PASSE A OK DOIT AVOIR TOUTES SES IMPRESSIONS OK
*/

/*
QUAND ON AJOUTE UN ARTICLE, VÉRIFIER QUE LE NOMBRE D'IMPRESSION DEMANDÉE EST RÉALISABLE

CREATE OR REPLACE TRIGGER ARTICLE_CALENDRIER
BEFORE INSERT OR UPDATE ON ARTICLE
FOR EACH ROW
DECLARE
   COUNT_TMP INTEGER;
   STOCK_MURAL INTEGER;
BEGIN 
-- COMBIEN DE PAGES NECESSAIRE POUR L ARTICLE
-- ERREUR AU DÉBUT DU TRIGGER
   SELECT DISTINCT ID_ARTICLE, MAX(NOPAGEPHOTOCALENDRIER) INTO COUNT_TMP
   
   FROM ARTICLE 
      NATURAL JOIN CALENDRIER 
      NATURAL JOIN PHOTOCALENDRIER 
      NATURAL JOIN MURAL
   WHERE ID_ARTICLE = :NEW.ID_ARTICLE
   GROUP BY ID_ARTICLE;
   
   SELECT QUANTITESTOCK INTO STOCK_MURAL
   FROM STOCK 
   WHERE TYPE_IMPRESSION='CALENDRIER';

   IF (STOCK_MURAL - COUNT_TMP < 10 ) THEN
      RAISE_APPLICATION_ERROR(-20001, 'IMPRESSION PAS POSSIBLE CAR MANQUE DE STOCK');
   END IF;
END;
/
*/
-- Requetes statistiques
select qualite, count(*)
from commande natural join
(
   select numimpression, qualite, format
   from impression natural join calendrier
   union
   select numimpression, qualite, format
   from impression natural join album
   union
   select numimpression, qualite, format
   from impression natural join cadre
   union
   select numimpression, qualite, format
   from impression natural join agenda
   union
   select numimpression, qualite, format
   from impression natural join tirage
)
natural join article natural join commande
group by qualite
;
select format, count(*)
from (
   select numimpression, qualite, format
   from impression natural join calendrier
   union
   select numimpression, qualite, format
   from impression natural join album
   union
   select numimpression, qualite, format
   from impression natural join cadre
   union
   select numimpression, qualite, format
   from impression natural join agenda
   union
   select numimpression, qualite, format
   from impression natural join tirage)
natural join article natural join commande
group by format
;
select count(*)
from (
   select numimpression, qualite, format
   from impression natural join calendrier
   union
   select numimpression, qualite, format
   from impression natural join album
   union
   select numimpression, qualite, format
   from impression natural join cadre
   union
   select numimpression, qualite, format
   from impression natural join agenda
   union
   select numimpression, qualite, format
   from impression natural join tirage)
natural join article natural join commande
;
-- Récupérer toutes les commandes en cours ou pret a l'envoi

-- mise a jour du stock, j'ai un format, une qualite, quantite


