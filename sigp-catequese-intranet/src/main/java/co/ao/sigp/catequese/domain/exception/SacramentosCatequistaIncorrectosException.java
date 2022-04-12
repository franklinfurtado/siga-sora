package co.ao.sigp.catequese.domain.exception;

public class SacramentosCatequistaIncorrectosException  extends SacramentosIncorrectosException {

	private static final long serialVersionUID = 1L;
    
    public SacramentosCatequistaIncorrectosException() {
    	super("Erro!!! Validar sacramentos informados, o catequista deve ter os seguintes sacramentos: Batismo, Comunhão e Crisma");
    }   
}
