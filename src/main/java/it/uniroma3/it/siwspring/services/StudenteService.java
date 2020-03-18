package it.uniroma3.it.siwspring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.it.siwspring.model.Studente;
import it.uniroma3.it.siwspring.repository.StudenteRepository;

@Service
public class StudenteService {

	@Autowired
	private StudenteRepository studenteRepository;
	
	@Transactional
	public Studente inserisci(Studente studente) {
		return studenteRepository.save(studente);
	}
	
	@Transactional
	public List<Studente> getTutti() {
		return (List<Studente>)studenteRepository.findAll();
	}
	
	public Studente getStudenteById(Long id) {
		return this.studenteRepository.findById(id).get();
	}
	
	
	public void elimina(Long id) {
		this.studenteRepository.deleteById(id);
	}
	
}
