package br.com.passos.api_voll_med.domain.consulta;

import br.com.passos.api_voll_med.domain.medico.Especialidade;
import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        Especialidade especialidade
) {
}
