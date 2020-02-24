package com.cadastroConvidados.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cadastroConvidados.domain.Convidado;
import com.cadastroConvidados.domain.Dependente;
import com.cadastroConvidados.dto.ConvidadoDTO;
import com.cadastroConvidados.services.ConvidadoService;

@RestController
@RequestMapping(value = "/convidados")
public class ConvidadoResource {

	@Autowired
	private ConvidadoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Convidado> find(@PathVariable Integer id) {
		Convidado obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert (@RequestBody Convidado obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update (@RequestBody Convidado obj,@PathVariable Integer id){
		obj.setId(id);
		
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Convidado>> findAll() {
		List<Convidado> list = service.findAll();
		  
		return ResponseEntity.ok().body(list);
	}
}
