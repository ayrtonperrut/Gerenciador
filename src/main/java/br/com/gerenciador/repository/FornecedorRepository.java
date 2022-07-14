package br.com.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerenciador.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	List<Fornecedor> findByNome(String nome);

}
