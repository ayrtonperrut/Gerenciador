package br.com.gerenciador.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.gerenciador.model.Fornecedor;
import br.com.gerenciador.repository.FornecedorRepository;

public class AtualizacaoFornecedorForm {
	
	@NotNull
	private String nome;
	
	@NotNull @Email
	private String email;
	
	@NotNull
	private String comentario;
	
	@NotNull @CNPJ
	private String cnpj;

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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Fornecedor atualizar(Long id, FornecedorRepository fornecedorRepository) {
		Fornecedor fornecedor = fornecedorRepository.getOne(id);
		fornecedor.setNome(this.nome);
		fornecedor.setEmail(this.email);
		fornecedor.setComentario(this.comentario);
		fornecedor.setCnpj(this.cnpj);
		
		return fornecedor;		
	}
	
	

}
