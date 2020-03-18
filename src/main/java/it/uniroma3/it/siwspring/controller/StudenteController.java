package it.uniroma3.it.siwspring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.it.siwspring.model.Studente;
import it.uniroma3.it.siwspring.services.StudenteService;
import it.uniroma3.it.siwspring.services.StudenteValidator;

@Controller
public class StudenteController {
	
	@Autowired
	private StudenteValidator studenteValidator;
	@Autowired
	private StudenteService studenteService;
	
	@RequestMapping(value="/addStudente", method = RequestMethod.POST)
	public String nuovoStudente(@Valid @ModelAttribute("studente") Studente studente,
			Model model, BindingResult bindingResult) {
		
		this.studenteValidator.validate(studente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.studenteService.inserisci(studente);
			model.addAttribute("studenti", studenteService.getTutti());
			return "studenti.html";
		} else {
			return "studenteForm.html";
		}
	}
	
	@RequestMapping(value = "/studente/{id}", method = RequestMethod.GET) 
	public String getStudente(@PathVariable("id") Long id, Model model) {
		if(id!= null) {
			model.addAttribute("studente", this.studenteService.getStudenteById(id));
			return "studente.html";
		} else {
			return getListaStudenti(model);
		}
		
	}
	
	@RequestMapping("/listaStudenti") 
	public String getListaStudenti(Model model) {
		model.addAttribute("studenti", this.studenteService.getTutti());
		return "studenti.html";
	}
	
	@RequestMapping("/newStudente")
	public String addStudente(Model model) {
		model.addAttribute("studente", new Studente());
		return "studenteForm.html";
		
	}
	
	@RequestMapping(value = "/eliminaStudente/{id}", method = RequestMethod.POST)
	public String eliminaStudente(@PathVariable Long id, Model model) {
		this.studenteService.elimina(id);
		model.addAttribute("studenti", this.studenteService.getTutti());
		return "studenti.html";
	}
}
