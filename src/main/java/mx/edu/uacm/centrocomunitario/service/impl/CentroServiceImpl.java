package mx.edu.uacm.centrocomunitario.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.centrocomunitario.entity.Centro;
import mx.edu.uacm.centrocomunitario.repository.CentroRepository;
import mx.edu.uacm.centrocomunitario.service.CentroService;



@Service
public class CentroServiceImpl implements CentroService{
	@Autowired
	private CentroRepository centroRepository ;
	
	public ArrayList<Centro> obtenerCentros(){
		ArrayList<Centro> centros= new ArrayList<Centro>();
		centros=(ArrayList<Centro>) centroRepository.findAll();
		return centros;
	}
}
