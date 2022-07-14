package br.com.gerenciador.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gerenciador.controller.dto.FornecedorDto;
import br.com.gerenciador.controller.form.AtualizacaoFornecedorForm;
import br.com.gerenciador.controller.form.FornecedorForm;
import br.com.gerenciador.model.Fornecedor;
import br.com.gerenciador.repository.FornecedorRepository;

@RestController
@RequestMapping("/fornecedores")
public class FornecedoresController {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping
	public List<FornecedorDto> lista(String nome) {
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		if (nome == null) {
			fornecedores = fornecedorRepository.findAll();	
		} else {
			fornecedores = fornecedorRepository.findByNome(nome);
		}
		
		return FornecedorDto.converter(fornecedores);	
	}
	
	@PostMapping
	public ResponseEntity<FornecedorDto> cadastrar(@RequestBody @Valid FornecedorForm form, UriComponentsBuilder uriBuilder) {
		Fornecedor fornecedor = form.converter();
		fornecedorRepository.save(fornecedor);
		
		URI uri = uriBuilder.path("/fornecedores/{id}").buildAndExpand(fornecedor.getId()).toUri();
		return ResponseEntity.created(uri).body(new FornecedorDto(fornecedor));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDto> getFornecedorById(@PathVariable Long id) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		
		if (fornecedor.isPresent()) {
			
			return ResponseEntity.ok(new FornecedorDto(fornecedor.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public <T> ResponseEntity<FornecedorDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoFornecedorForm form) {
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		
		
		if (optional.isPresent()) {
			Fornecedor fornecedor = form.atualizar(id, fornecedorRepository);
			return ResponseEntity.ok(new FornecedorDto(fornecedor));
		}
		
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		if (optional.isPresent()) {
			fornecedorRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
