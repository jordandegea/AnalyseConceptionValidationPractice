package model;

import java.sql.Date;

/**
 * Created by william on 05/04/16.
 */
public class BioInitialeModel extends EpisodeModel {
    public BioInitialeModel(int id) {
        super(id, new Date(0,0,0), false);
    }
    
    public BioInitialeModel() {
        super(new Date(0,0,0), false);
        this.addParagraphe(new ParagrapheModel(false, "lorem ipsum"));
    }
}
