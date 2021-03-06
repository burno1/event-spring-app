package com.cadastroConvidados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cadastroConvidados.domain.Convidado;
import com.cadastroConvidados.dto.ConvidadoDTO;
import com.cadastroConvidados.repositories.ConvidadoRepository;
import com.cadastroConvidados.services.exception.DataIntegrityException;
import com.cadastroConvidados.services.exception.ObjectNotFoundException;

@Service
public class ConvidadoService {
	@Autowired
	private ConvidadoRepository repo;
	
	
	
	public Convidado find(Integer id) {
		Optional<Convidado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto n�o encontrado! Id: " + id + ", Tipo: " + Convidado.class.getName()));
	}
	
	public Convidado insert(Convidado obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Convidado update(Convidado obj) {
		find(obj.getId());
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("N�o � poss�vel excluir um convidado que possui dependentes");
		}
	}
	
	public List<Convidado> findAll() {
		return repo.findAll();
	}
	
	public Convidado fromDTO(ConvidadoDTO objDto) {
		return new Convidado(objDto.getId(), objDto.getNome(), objDto.getDataNasc(), objDto.getEmail());
	}
}
