package serviceBD;

import java.util.ArrayList;

public class BuildReq {

    /* premier argument la table arguments suivant les valeurs */
    public String insert(String... arguments){
        String req = "INSERT INTO ";
        ArrayList<String> args = new ArrayList<>();
        int i = 0;
        for(String arg : arguments){
            if(this.isInteger(arg) || i == 0 || arg.equalsIgnoreCase("null")){
                args.add(arg);
            } else {
                args.add("'"+arg+"'");
            }
            i++;
        }
        req += args.get(0);
        req += " VALUES (";
        for(i = 1 ; i < arguments.length ; i++){
            req += args.get(i);
            if(i != arguments.length - 1){
                req += ", ";
            }
        }
        req += ")";
        return req;
    }

    private boolean isInteger(String chaine) {
        try {
            Double.parseDouble(chaine);
            //Integer.parseInt(chaine);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
