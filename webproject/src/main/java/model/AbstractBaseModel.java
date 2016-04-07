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
    private final int id;

    public int getId() {
        return id;
    }

    public AbstractBaseModel(int id) {
        this.id = id;
    }

    public static AbstractDataBaseDAO getDAO() {
        return null;
    }
}
