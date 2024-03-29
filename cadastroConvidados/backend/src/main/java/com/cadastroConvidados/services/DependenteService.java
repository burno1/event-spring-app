package com.cadastroConvidados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cadastroConvidados.domain.Convidado;
import com.cadastroConvidados.domain.Dependente;
import com.cadastroConvidados.repositories.DependenteRepository;
import com.cadastroConvidados.services.exception.DataIntegrityException;
import com.cadastroConvidados.services.exception.ObjectNotFoundException;

@Service
public class DependenteService {
	
	@Autowired
	private DependenteRepository repo;
	
	public Dependente find(Integer id) {
		Optional<Dependente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto n�o encontrado! Id: " + id + ", Tipo: " + Dependente.class.getName()));
	}
	
	public Dependente insert(Dependente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Dependente update(Dependente obj) {
		find(obj.getId());
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("N�o � poss�vel excluir um Dependente que possui dependentes");
		}
	}
	
	public List<Dependente> findAll() {
		return repo.findAll();
	}
}
