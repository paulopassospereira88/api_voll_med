package br.com.passos.api_voll_med.domain.consulta.validacoes;

import br.com.passos.api_voll_med.domain.consulta.ConsultaRepository;
import br.com.passos.api_voll_med.domain.consulta.DadosAgendamentoConsulta;
import br.com.passos.api_voll_med.domain.consulta.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaMesmoHorario implements  ValidadorAgendamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta agendamentoConsulta){
        var medicoPossuiOutraConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(agendamentoConsulta.idMedico(), agendamentoConsulta.data());

        if(medicoPossuiOutraConsultaMesmoHorario){
            throw new ValidacaoException("Medico ja possui outra consulta agendada nesse horario.");
        }
    }
}
