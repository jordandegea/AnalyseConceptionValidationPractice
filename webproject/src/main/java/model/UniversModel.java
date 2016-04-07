/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import loaders.PartieLoader;
import loaders.PersonnageLoader;

import java.util.Set;

/**
 *
 * @author JordanLeMagnifique
 */
public class UniversModel extends AbstractBaseModel {
    private String nom;
    private PersonnageLoader personnages;

    public UniversModel(int id, String nom){
        super(id);
        this.nom = nom;
        personnages = new PersonnageLoader();
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<PersonnageModel> getPersonnages() {
        return personnages.get(this);
    }
}
