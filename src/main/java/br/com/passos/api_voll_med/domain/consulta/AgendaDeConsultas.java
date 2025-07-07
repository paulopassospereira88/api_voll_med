package br.com.passos.api_voll_med.domain.consulta;

import br.com.passos.api_voll_med.domain.consulta.validacoes.ValidadorAgendamentoConsulta;
import br.com.passos.api_voll_med.domain.medico.Medico;
import br.com.passos.api_voll_med.domain.medico.MedicoRepository;
import br.com.passos.api_voll_med.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AgendaDeConsultas {

    private final ConsultaRepository  consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoConsulta> validadorAgendamentoConsultas;

    public AgendaDeConsultas(ConsultaRepository  consultaRepository,  MedicoRepository medicoRepository, PacienteRepository pacienteRepository, List<ValidadorAgendamentoConsulta> validadorAgendamentoConsultas) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadorAgendamentoConsultas = validadorAgendamentoConsultas;
    }

    @Transactional
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        if(!pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente())){
            throw new ValidacaoException("ID do paciente informado nao existe!");
        }

        if(dadosAgendamentoConsulta.idMedico() != null && !medicoRepository.existsById(dadosAgendamentoConsulta.idMedico())){
            throw new ValidacaoException("ID do medico informado nao existe!");
        }

        validadorAgendamentoConsultas.forEach(v -> v.validar(dadosAgendamentoConsulta));

        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());
        var medico = escolherMedico(dadosAgendamentoConsulta);

        if(medico == null){
            throw new ValidacaoException("Nao existe medico disponivel nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsulta.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
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
