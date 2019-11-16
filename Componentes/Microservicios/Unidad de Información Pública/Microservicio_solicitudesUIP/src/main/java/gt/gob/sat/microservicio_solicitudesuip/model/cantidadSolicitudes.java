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
@Entity
public class cantidadSolicitudes {
    private int solicitudes;
    
    @Id
    public int getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(int solicitudes) {
        this.solicitudes = solicitudes;
    }

}
