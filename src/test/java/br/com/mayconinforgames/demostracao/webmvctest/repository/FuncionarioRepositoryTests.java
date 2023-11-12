package br.com.mayconinforgames.demostracao.webmvctest.repository;

import br.com.mayconinforgames.demostracao.webmvctest.entity.Funcionario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FuncionarioRepositoryTests {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Test
	void inserirFuncionario() {
		Funcionario funcionario = new Funcionario(1, "João", "joao@example.com");
		funcionarioRepository.save(funcionario);
		Integer countFuncionario = funcionarioRepository.findAll().size();
		assertEquals(1, countFuncionario);
	}

}
