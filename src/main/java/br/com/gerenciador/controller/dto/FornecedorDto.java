package br.com.gerenciador.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gerenciador.model.Fornecedor;

public class FornecedorDto {
	
	private Long id;
	private String nome;
	private String email;
	private String comentario;
	private String cnpj;
	
	
	public FornecedorDto(Fornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.nome = fornecedor.getNome();
		this.email = fornecedor.getEmail();
		this.comentario = fornecedor.getComentario();
		this.cnpj = fornecedor.getCnpj();
	}
		
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public static List<FornecedorDto> converter(List<Fornecedor> fornecedores) {
			
		return fornecedores.stream().map(FornecedorDto::new).collect(Collectors.toList());
						
	}
	
	

}
