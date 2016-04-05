package model;

/**
 * Created by william on 05/04/16.
 */
public class ParagrapheModel extends AbstractBaseModel {
    private boolean secret;
    private String contenu;

    public ParagrapheModel(int id, boolean secret, String contenu) {
        super(id);
        this.secret = secret;
        this.contenu = contenu;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
