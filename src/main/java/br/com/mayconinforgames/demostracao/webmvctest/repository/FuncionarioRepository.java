package br.com.mayconinforgames.demostracao.webmvctest.repository;

import br.com.mayconinforgames.demostracao.webmvctest.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
