/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AbstractDataBaseDAO;

/**
 *
 * @author JordanLeMagnifique
 */
public abstract class AbstractBaseModel {
    private int id;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public AbstractBaseModel(int id) {
        this.id = id;
    }
    
    public AbstractBaseModel() {
    }
    
    public static AbstractDataBaseDAO getDAO() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractBaseModel other = (AbstractBaseModel) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
