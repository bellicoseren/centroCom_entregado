package mx.edu.uacm.centrocomunitario.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long folio;
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String correo;
	String direccion;
	String telefono;
	int edad;
	String sexo;
	String password;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Rol> roles = new ArrayList<>();
	
	

	    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Asistencia> centros = new ArrayList<>();
	    
	    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Asistencia> acyividades = new ArrayList<>();
}

