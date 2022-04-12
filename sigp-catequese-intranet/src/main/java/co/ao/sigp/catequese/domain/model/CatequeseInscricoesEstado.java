package co.ao.sigp.catequese.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CatequeseInscricoesEstado {

    
    CRIADO("Criado", "info", "Este estado é o estado incial das inscriçōes, ou seja, quando uma inscrição é criada e ainda não foi aprovada ou rejeitada"),
    APROVADO("Aprovado", "success", "Este estado representa uma inscrição aprovada. Quando uma inscrição é aprovada, automaticamente o Candidato é adicionado a lista de Catequistas do sistema"),
    REJEITADO("Rejeitado", "danger", "Este estado representa uma inscrição rejeitada.");

    private final String estado;
    private final String corCSS;
    private final String descricaoEstado;
}
