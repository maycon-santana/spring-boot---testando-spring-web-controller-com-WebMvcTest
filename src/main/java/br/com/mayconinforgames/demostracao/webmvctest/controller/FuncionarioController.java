package br.com.mayconinforgames.demostracao.webmvctest.controller;

import br.com.mayconinforgames.demostracao.webmvctest.entity.Funcionario;
import br.com.mayconinforgames.demostracao.webmvctest.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository;
    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> listarTodosFuncionarios() {

        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);

    }
    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable("id") long id) {

        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return new ResponseEntity<>(funcionario.get(), HttpStatus.OK);

    }
    @PostMapping("/funcionarios")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {

        Funcionario _funcionario = funcionarioRepository.save(Funcionario.builder()
                .nome(funcionario.getNome())
                .email(funcionario.getEmail())
                .build());
        return new ResponseEntity<>(_funcionario, HttpStatus.CREATED);
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Funcionario funcionario) {

        Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);

        if (funcionarioData.isPresent()) {
            Funcionario _funcionario = funcionarioData.get();
            _funcionario.setNome(funcionario.getNome());
            _funcionario.setEmail(funcionario.getEmail());

            return new ResponseEntity<>(funcionarioRepository.save(_funcionario),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {

        funcionarioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
