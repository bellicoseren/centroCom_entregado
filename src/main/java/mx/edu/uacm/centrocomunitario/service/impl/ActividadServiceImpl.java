package mx.edu.uacm.centrocomunitario.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.centrocomunitario.entity.Actividad;
import mx.edu.uacm.centrocomunitario.repository.ActividadRepository;
import mx.edu.uacm.centrocomunitario.service.ActividadService;



@Service
public class ActividadServiceImpl implements ActividadService{
	@Autowired
	private ActividadRepository actividadRepository ;
	
	public ArrayList<Actividad> obtenerActividades(){
		ArrayList<Actividad> actividades= new ArrayList<Actividad>();
		actividades=(ArrayList<Actividad>) actividadRepository.findAll();
		return actividades;
	}
}