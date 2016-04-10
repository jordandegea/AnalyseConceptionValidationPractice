package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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
        Set<ParagrapheModel> paras = new LinkedHashSet<>();
        paras.add(new ParagrapheModel(false, "lorem ipsum"));
    }
    
    public BioInitialeModel(ArrayList<String> paragraphes, ArrayList<Integer> access) {
        super(new Date(0), false);
        int i = 0;
        Set<ParagrapheModel> paras = new LinkedHashSet<>();
        for (String p : paragraphes) {
            paras.add(new ParagrapheModel (/*access.get(i)==0)?false:true */ false, p));
            i++;
        }
        
        super.setParagraphes(paras);
    }
}
