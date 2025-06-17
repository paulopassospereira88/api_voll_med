package br.com.passos.api_voll_med.medico;

import br.com.passos.api_voll_med.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, DadosEndereco endereco) {
}
