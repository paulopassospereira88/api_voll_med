package br.com.passos.api_voll_med.domain.consulta;

import br.com.passos.api_voll_med.domain.medico.Medico;
import br.com.passos.api_voll_med.domain.medico.MedicoRepository;
import br.com.passos.api_voll_med.domain.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendaDeConsultas {

    private final ConsultaRepository  consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public AgendaDeConsultas(ConsultaRepository  consultaRepository,  MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public void agendar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        if(!pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente())){
            throw new ValidacaoException("ID do paciente informado nao existe!");
        }

        if(dadosAgendamentoConsulta.idMedico() != null && medicoRepository.existsById(dadosAgendamentoConsulta.idMedico())){
            throw new ValidacaoException("ID do medico informado nao existe!");
        }

        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());
        var medico = escolherMedico(dadosAgendamentoConsulta);
        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsulta.data(), null);
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        if(dadosAgendamentoConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(dadosAgendamentoConsulta.idMedico());
        }

        if(dadosAgendamentoConsulta.especialidade() == null){
            throw new ValidacaoException("Especialidade do medico nao informada!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamentoConsulta.especialidade(), dadosAgendamentoConsulta.data());
    }

    public void cancelar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        if(!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta())){
            throw new ValidacaoException("ID da consulta informada nao existe!");
        }

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        consulta.cancelar(dadosCancelamentoConsulta.motivo());
    }
}
