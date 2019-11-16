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
public class ParamExpDto {
    public ParamExpDto(){}
   
    public String nit;
    public String razonSocial;
    
    public String getNit() {
    return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public String getRazonSocial() {
    return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }   
}
