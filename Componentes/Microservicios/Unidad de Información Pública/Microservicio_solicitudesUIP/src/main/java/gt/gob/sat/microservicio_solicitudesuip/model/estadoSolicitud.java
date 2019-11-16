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
public class estadoSolicitud {
    private String status;
    private String solicitud;

    @Id
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

   
    
    
}
