package br.com.mayconinforgames.demostracao.webmvctest.entity;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(Funcionario.class)
class FuncionarioTests {

	@Test
	public void testCriacaoFuncionarioComConstrutorVazio() {
		Funcionario funcionario = new Funcionario();
		assertNotNull(funcionario);
		assertEquals(0, funcionario.getId());
		assertNull(funcionario.getNome());
		assertNull(funcionario.getEmail());
	}

	@Test
	public void testCriacaoFuncionarioComConstrutorArgs() {
		Funcionario funcionario = new Funcionario(1L, "João", "joao@example.com");
		assertNotNull(funcionario);
		assertEquals(1L, funcionario.getId());
		assertEquals("João", funcionario.getNome());
		assertEquals("joao@example.com", funcionario.getEmail());
	}

	@Test
	public void testSettersAndGetters() {
		Funcionario funcionario = new Funcionario();

		funcionario.setId(2L);
		assertEquals(2L, funcionario.getId());

		funcionario.setNome("Maria");
		assertEquals("Maria", funcionario.getNome());

		funcionario.setEmail("maria@example.com");
		assertEquals("maria@example.com", funcionario.getEmail());
	}

	@Test
	public void testEquals() {
		Funcionario funcionario1 = new Funcionario(1L, "Alice", "alice@example.com");
		Funcionario funcionario2 = new Funcionario(1L, "Alice", "alice@example.com");
		Funcionario funcionario3 = new Funcionario(2L, "Bob", "bob@example.com");

		assertEquals(funcionario1, funcionario2);
		assertNotEquals(funcionario1, funcionario3);
	}

	@Test
	public void testHashCode() {
		Funcionario funcionario1 = new Funcionario(1L, "Alice", "alice@example.com");
		Funcionario funcionario2 = new Funcionario(1L, "Alice", "alice@example.com");
		Funcionario funcionario3 = new Funcionario(2L, "Bob", "bob@example.com");

		assertEquals(funcionario1.hashCode(), funcionario2.hashCode());
		assertNotEquals(funcionario1.hashCode(), funcionario3.hashCode());
	}

	@Test
	public void testSuperBuilder() {
		Funcionario funcionario = Funcionario.builder()
				.id(1L)
				.nome("João")
				.email("joao@example.com")
				.build();

		assertNotNull(funcionario);
		assertEquals(1L, funcionario.getId());
		assertEquals("João", funcionario.getNome());
		assertEquals("joao@example.com", funcionario.getEmail());
	}

	@Test
	public void testSuperBuilderWithInheritance() {
		Funcionario funcionario = Funcionario.builder()
				.id(2L)
				.nome("Maria")
				.email("maria@example.com")
				.build();

		assertNotNull(funcionario);
		assertEquals(2L, funcionario.getId());
		assertEquals("Maria", funcionario.getNome());
		assertEquals("maria@example.com", funcionario.getEmail());
	}

}
