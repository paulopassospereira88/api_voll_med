package br.com.passos.api_voll_med.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

}
