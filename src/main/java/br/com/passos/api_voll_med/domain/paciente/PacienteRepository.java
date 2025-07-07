package br.com.passos.api_voll_med.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("select p.ativo from Paciente p where p.id = :idPaciente")
    Boolean findAtivoById(Long idPaciente);

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
