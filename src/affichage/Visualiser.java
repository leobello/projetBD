package affichage;
import BDD.Commande;
import BDD.Impression;
import BDD.Client;
import serviceBD.LectureClavier;

public class Visualiser {

    public static void visualiserImpressions(Client client) {
        String ch;
        for (Impression impression : client.getImpressions()){
            System.out.println(/**********************************************************************************************/);
            System.out.println(" Nombre d'impresion: " + client.getImpressions().size());
            System.out.println(" Impression n: "+ impression.getNumImpression());
            System.out.println(" Qualité: " + impression.getQualite());
            System.out.println(" Format: " + impression.getQualite());
            System.out.println(" Chemin: " + impression.getPathImpression());
            System.out.println(/**********************************************************************************************/);
            do {
                ch = LectureClavier.lireChaine(" Suivante? [y/n]: ");
            } while ( ch.equalsIgnoreCase("y") && ch.equalsIgnoreCase("n"));
            if(ch.equalsIgnoreCase("n")){
                break;
            }
        }
    }

    public static void visualiserDetailCommande(Commande commande) {
        System.out.println(/**********************************************************************************************/);
        System.out.println(" Date de la commande: " + commande.getDate().toString());
        System.out.println(" Mode de livraison: " + commande.getModeLivraison());
        System.out.println(" Statut: " + commande.getStatutCommande());
        System.out.println(" Effectué par: " + commande.getClient().getNom() + " " + commande.getClient().getPrenom());
        System.out.println(" Montant total: " + commande.getMontant());
        System.out.println(" q: quitter");
        // voir suite
    }
}
