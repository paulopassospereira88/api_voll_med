package br.com.passos.api_voll_med.domain.consulta.validacoes.agendamento;

import br.com.passos.api_voll_med.domain.consulta.DadosAgendamentoConsulta;
import br.com.passos.api_voll_med.domain.consulta.ValidacaoException;
import br.com.passos.api_voll_med.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta agendamentoConsulta) {
        var pacienteAtivo = pacienteRepository.findAtivoById(agendamentoConsulta.idPaciente());
        if (!pacienteAtivo) {
            throw new ValidacaoException("Consulta nao pode ser agendada pois o paciente nao esta ativo.");
        }
    }
}
