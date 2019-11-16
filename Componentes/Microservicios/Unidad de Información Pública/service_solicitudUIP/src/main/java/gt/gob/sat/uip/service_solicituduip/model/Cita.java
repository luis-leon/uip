package gt.gob.sat.uip.service_solicituduip.model;
// Generated 06-ago-2019 15:34:47 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Cita generated by hbm2java
 */
@Entity
@Table(name="cita"
    ,schema="sat_uip"
)
public class Cita  implements java.io.Serializable {


     private int idCita;
     private boolean asistio;
     private Date fechaHora;
     private RazonCita razonCita;

    public Cita() {
    }

    public Cita(int idCita, boolean asistio, Date fechaHora, RazonCita razonCita) {
       this.idCita = idCita;
       this.asistio = asistio;
       this.fechaHora = fechaHora;
       this.razonCita = razonCita;
    }
   
     @Id 

    
    @Column(name="id_cita", unique=true, nullable=false)
    public int getIdCita() {
        return this.idCita;
    }
    
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    
    @Column(name="asistio", nullable=false)
    public boolean isAsistio() {
        return this.asistio;
    }
    
    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_hora", nullable=false, length=13)
    public Date getFechaHora() {
        return this.fechaHora;
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_razon", nullable=false)
    public RazonCita getRazonCita() {
        return this.razonCita;
    }
    
    public void setRazonCita(RazonCita razonCita) {
        this.razonCita = razonCita;
    }




}


