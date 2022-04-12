/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.config;

import co.ao.sigp.catequese.domain.model.Paroquia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scm
 */
public class AppConfig {
    
    private static List<Paroquia> listaParoquiSistema;
    private static Paroquia paroquia;
    
    public static List<Paroquia> getListaParoquiSistema() {
        paroquia = new Paroquia(1);
        listaParoquiSistema = new ArrayList<Paroquia>();
        listaParoquiSistema.add(paroquia);
        paroquia = null;
        return listaParoquiSistema;
    }
    
    public Paroquia getParoquiaSistema(){
        return new Paroquia(1);
    }
    
    public void todo() {
        //TODO: tela inicial solicitando o registo de dados iniciais para o sistema funcionar
            /*
                - horario de catequese
                - etapas da catequese (adicionar no afterMigrate), mostrar as etapas pre-criadas na tela
                - estados de um ano letivo (mostrar exemplos de opcoes de estados do ano letivo
                - sacramentos (adicionar no afterMigrate), mostrar os sacramentos pre-criados na tela
            */
        //TODO: Editar/Remover Sacramento
        //TODO: Etapa catequese required mes e ano
        //TODO: Paroquia Pastoral estatistica
        //TODO: Grupo/Movimento estatistica
        //TODO: Ano letivo estatistica
        //TODO: Ano letivo nao pode ser alterado de em curso para criado, nem de fechado para criado - Ver Pedido->StatusPedido
        //TODO: Criar um migration com a adicao de varias paroquias de luanda
    }
}
