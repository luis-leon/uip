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
public class ParamDocDto {
    public ParamDocDto(){}
   
    public String expediente;
    public String docto;
    
    public String getExpediente() {
    return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }
    
    public String getDocto() {
    return docto;
    }

    public void setDocto(String docto) {
        this.docto = docto;
    }
    
}
