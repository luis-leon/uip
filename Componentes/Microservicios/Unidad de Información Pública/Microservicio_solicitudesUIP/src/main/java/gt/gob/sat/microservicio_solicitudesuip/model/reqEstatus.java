/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.microservicio_solicitudesuip.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author alfdeleon
 */

public class reqEstatus {
    private String pNit;
    private int Anio;
    private int Periodo;

     
    public String getpNit() {
        return pNit;
    }

    public void setpNit(String pNit) {
        this.pNit = pNit;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    public int getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(int Periodo) {
        this.Periodo = Periodo;
    }
    
    
    
}
