package br.com.passos.api_voll_med.domain.consulta.validacoes.agendamento;

import br.com.passos.api_voll_med.domain.consulta.DadosAgendamentoConsulta;
import br.com.passos.api_voll_med.domain.consulta.ValidacaoException;
import br.com.passos.api_voll_med.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        if(dadosAgendamentoConsulta.idMedico() == null){
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoById(dadosAgendamentoConsulta.idMedico());
        if(!medicoAtivo){
            throw new ValidacaoException("Consulta nao pode ser agendada pois o medico nao esta ativo.");
        }
    }
}
