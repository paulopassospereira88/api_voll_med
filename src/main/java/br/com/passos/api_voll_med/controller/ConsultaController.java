package br.com.passos.api_voll_med.controller;

import br.com.passos.api_voll_med.domain.consulta.AgendaDeConsultas;
import br.com.passos.api_voll_med.domain.consulta.DadosAgendamentoConsulta;
import br.com.passos.api_voll_med.domain.consulta.DadosCancelamentoConsulta;
import br.com.passos.api_voll_med.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final AgendaDeConsultas agendaDeConsultas;

    public ConsultaController(AgendaDeConsultas agendaDeConsultas) {
        this.agendaDeConsultas = agendaDeConsultas;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var agendaDeConsultasDTO = agendaDeConsultas.agendar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaDeConsultasDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
