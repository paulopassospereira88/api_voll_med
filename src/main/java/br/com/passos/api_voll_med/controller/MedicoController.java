package br.com.passos.api_voll_med.controller;

import br.com.passos.api_voll_med.medico.DadosCadastroMedico;
import br.com.passos.api_voll_med.medico.Medico;
import br.com.passos.api_voll_med.medico.MedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
