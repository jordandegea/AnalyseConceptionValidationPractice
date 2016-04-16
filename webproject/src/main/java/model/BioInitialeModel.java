package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;



/**
 * Created by william on 05/04/16.
 */
public class BioInitialeModel extends EpisodeModel {
    public BioInitialeModel(int id) {
        super(id, new Date(0), false, false);
    }
    
//    public BioInitialeModel() {
//        super(new Date(0), false);
//        Set<ParagrapheModel> paras = new LinkedHashSet<>();
//        paras.add(new ParagrapheModel(false, "lorem ipsum", 1));
//    }
    
    public BioInitialeModel(ArrayList<String> paragraphes, ArrayList<Integer> access) {
        super(new Date(0), false, false, paragraphes, access);
    }
}
