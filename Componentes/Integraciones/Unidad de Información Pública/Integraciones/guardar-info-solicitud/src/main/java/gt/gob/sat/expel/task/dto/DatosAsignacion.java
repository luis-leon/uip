package gt.gob.sat.expel.task.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class DatosAsignacion {
	private String nombreUsuario;
	private String paraCorreo;

	public DatosAsignacion(){

	}

	public String getNombreUsuario(){
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario){
		this.nombreUsuario = nombreUsuario;
	}

	public String getParaCorreo(){
		return paraCorreo;
	}

	public void setParaCorreo(String paraCorreo){
		this.paraCorreo = paraCorreo;
	}

}