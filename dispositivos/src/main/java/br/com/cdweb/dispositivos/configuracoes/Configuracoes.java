/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cdweb.dispositivos.configuracoes;

/**
 *
 * @author Willian
 */
public enum Configuracoes {
    INSTANCE("DISPO_01");
    private String name;

    private Configuracoes(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
