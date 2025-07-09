package br.com.passos.api_voll_med.domain.consulta.validacoes.cancelamento;

import br.com.passos.api_voll_med.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);
}
