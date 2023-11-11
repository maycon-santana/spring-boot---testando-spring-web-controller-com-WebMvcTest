package br.com.mayconinforgames.demostracao.webmvctest.controller;

import br.com.mayconinforgames.demostracao.webmvctest.entity.Funcionario;
import br.com.mayconinforgames.demostracao.webmvctest.repository.FuncionarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@InjectMocks
	private FuncionarioController funcionarioController;
	@MockBean
	private FuncionarioRepository funcionarioRepository;

	@Test
	void deveAtualizarFuncionario() throws Exception {

		long id = 1L;

		Funcionario funcionario = new Funcionario(1, "Alpha", "alpha@tmail.com");
		Funcionario atualizarFuncionario = new Funcionario(1, "Beta", "alpha@tmail.com");

		when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));
		when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(atualizarFuncionario);

		mockMvc.perform(put("/api/v1/funcionarios/{id}", id).
						contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(atualizarFuncionario)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(atualizarFuncionario.getNome()))
				.andExpect(jsonPath("$.email").value(atualizarFuncionario.getEmail()))
				.andDo(print());
	}

	@Test
	void deveRetornarUmaListaDeFuncionarios() throws Exception {

		List<Funcionario> funcionarios = new ArrayList<>(
				Arrays.asList(new Funcionario(1, "Alpha", "alpha@tmail.com"),
						new Funcionario(2, "Beta", "beta@tmail.com"),
						new Funcionario(3, "Gama", "gama@tmail.com")));

		when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		mockMvc.perform(get("/api/v1/funcionarios"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(funcionarios.size()))
				.andDo(print());
	}

	@Test
	void deveRetornarFuncionarioPorId() throws Exception {

		long id = 1;
		Funcionario funcionario = new Funcionario(1, "Alpha", "alpha@tmail.com");

		when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));
		mockMvc.perform(get("/api/v1/funcionarios/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.nome").value(funcionario.getNome()))
				.andExpect(jsonPath("$.email").value(funcionario.getEmail()))
				.andDo(print());
	}

	@Test
	void deveCadastrarFornecedor() throws Exception {

		Funcionario funcionario = new Funcionario(1, "Alpha", "alpha@tmail.com");

		mockMvc.perform(post("/api/v1/funcionarios").
						contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(funcionario)))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	void deveExcluirFuncionario() throws Exception {

		long id = 1;

		doNothing().when(funcionarioRepository).deleteById(id);
		mockMvc.perform(delete("/api/v1/funcionarios/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
