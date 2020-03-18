package it.uniroma3.it.siwspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.it.siwspring.model.Studente;

@Repository
public interface StudenteRepository extends CrudRepository<Studente, Long>{
	
	public List<Studente> findByNome(String nome);
	public List<Studente> findByCognome(String cognome);
}
