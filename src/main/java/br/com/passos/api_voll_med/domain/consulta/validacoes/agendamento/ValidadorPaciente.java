package br.com.passos.api_voll_med.domain.consulta.validacoes.agendamento;

import br.com.passos.api_voll_med.domain.consulta.ConsultaRepository;
import br.com.passos.api_voll_med.domain.consulta.DadosAgendamentoConsulta;
import br.com.passos.api_voll_med.domain.consulta.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPaciente implements  ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta agendamentoConsulta){
        var primeiroHorario = agendamentoConsulta.data().withHour(7);
        var ultimoHorario = agendamentoConsulta.data().withHour(18);
        var pacientePossuiConsultaOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(agendamentoConsulta.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiConsultaOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente ja possui uma consulta agendada nesse dia.");
        }
    }
}
