package br.com.passos.api_voll_med.domain.consulta.validacoes.agendamento;

import br.com.passos.api_voll_med.domain.consulta.DadosAgendamentoConsulta;
import br.com.passos.api_voll_med.domain.consulta.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        var dataConsulta = dadosAgendamentoConsulta.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDaAberturaDaClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisDaAberturaDaClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica.");
        }
    }
}
