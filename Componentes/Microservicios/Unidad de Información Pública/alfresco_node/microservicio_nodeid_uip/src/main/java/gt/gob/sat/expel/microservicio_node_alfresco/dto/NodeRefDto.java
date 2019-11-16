/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.microservicio_node_alfresco.dto;

/**
 *
 * @author amolopezo
 */
public class NodeRefDto {
    public NodeRefDto(){
        
    }
    
    public String nodeRef;
    
    public String getNodeRef(){
        return nodeRef;
    }
    
    public void setNodeRef(String nodeRef){
        this.nodeRef = nodeRef;
    }
}