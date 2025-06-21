package br.com.passos.api_voll_med.controller;

import br.com.passos.api_voll_med.medico.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<DadosCadastroMedico> cadastro(@RequestBody DadosCadastroMedico dadosCadastroMedico) {
        Medico medico = new Medico(dadosCadastroMedico);
        medicoRepository.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosCadastroMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        Page<DadosListagemMedico> medicos = medicoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedico::new);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(medicos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        medicoRepository.save(medico);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new DadosListagemMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
