/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author JordanLeMagnifique
 */
public class UniversModel extends AbstractBaseModel{
    private String nom; 

    
    public UniversModel(int id, String nom){
        super(id);
        this.nom = nom;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
