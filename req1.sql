/*
Vérifier que quand la commande créer peut l'être
surveiller la table commande
article( numcommande, idarticle , numImpression)
*/

CREATE OR replace trigger check_commande
after insert or update on commande
declare 
   nb_commande integer;
begin 

select quantite
from article natural join commande
        



/*
une commande qui passe a OK doit avoir toutes ses impressions OK
*/
