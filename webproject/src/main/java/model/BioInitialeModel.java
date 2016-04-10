package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;



/**
 * Created by william on 05/04/16.
 */
public class BioInitialeModel extends EpisodeModel {
    public BioInitialeModel(int id) {
        super(id, new Date(0), false);
    }
    
    public BioInitialeModel() {
        super(new Date(0), false);
        this.addParagraphe(new ParagrapheModel(false, "lorem ipsum"));
    }
    
    public BioInitialeModel(ArrayList<String> paragraphes, ArrayList<Integer> access) {
        super(new Date(0), false);
        int i = 0;
        for (String p : paragraphes) {
            this.addParagraphe(new ParagrapheModel ((access.get(i)==0)?false:true, p));
            i++;
        }
    }
}
