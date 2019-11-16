/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.microservicio_node_alfresco.dto;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 *
 * @author amolopezo
 */
@Entity
public class NodeDto {
    public NodeDto(){}
    
    @Id
    public String uuid;
    
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid= uuid;
    }
}