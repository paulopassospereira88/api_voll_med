package br.com.passos.api_voll_med.domain.consulta.validacoes.agendamento;

import br.com.passos.api_voll_med.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta agendamentoConsulta);
}
