package co.ao.sigp.catequese.config;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class RewriteConfig extends HttpConfigurationProvider {
	
	@Override
	public int priority() {
		return 10;
	}

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin()
				.addRule(Join.path("/login").to("/login.xhtml"))
				
				.addRule(Join.path("/pastoral").to("/admin/paroquia/paroquia_pastoral/pastoral_home.xhtml"))
				
				.addRule(Join.path("/grupo-e-movimento").to("/admin/paroquia/paroquia_grupos_movimento/grupo_movimento_home.xhtml"))
				
				.addRule(Join.path("/horarios-de-catequese").to("/admin/catequese_horario/horario_home.xhtml"))
				
				.addRule(Join.path("/sacramentos-da-catequese").to("/admin/catequese_sacramento/sacramento_home.xhtml"))
				
				.addRule(Join.path("/etapas-da-catequese").to("/admin/catequese_etapa/etapa_home.xhtml"))
				
				.addRule(Join.path("/estados-ano-letivo").to("/admin/catequese_ano_letivo_estado/ano_letivo_estado_home.xhtml"))
				.addRule(Join.path("/ano-letivo").to("/admin/catequese_ano_letivo/ano_letivo_home.xhtml"))
				
				.addRule(Join.path("/lista-de-catecumeno").to("/admin/catequese_catecumeno/catecumeno_ver_lista.xhtml"))
				
				.addRule(Join.path("/lista-de-inscricoes-de-catequistas").to("/admin/catequese_catequistas/catequista_inscricoes_listar.xhtml"))
				.addRule(Join.path("/lista-de-inscricoes-de-catequistas-eliminadas").to("/admin/catequese_catequistas/catequista_inscricoes_listar_eliminados.xhtml"))
				.addRule(Join.path("/ficha-inscricao-de-catequista-registo").to("/admin/catequese_catequistas/catequista_inscricoes_ficha_registo.xhtml"))
				.addRule(Join.path("/ficha-inscricao-de-catequista-ver").to("/admin/catequese_catequistas/catequista_inscricoes_ficha_ver.xhtml"))
				
				.addRule(Join.path("/lista-de-catequistas-selecionados").to("/admin/catequese_catequistas/catequistas_ver_lista.xhtml"))
				.addRule(Join.path("/lista-de-catequistas-eliminados").to("/admin/catequese_catequistas/catequistas_listar_eliminados.xhtml"))
				.addRule(Join.path("/lista-de-catequistas").to("/admin/catequese_catequistas/catequistas_listar.xhtml"))
				.addRule(Join.path("/ficha-de-catequistas-registo").to("/admin/catequese_catequistas/catequistas_ficha_registo.xhtml"))
				.addRule(Join.path("/ficha-de-catequista-ver").to("/admin/catequese_catequistas/catequistas_ficha_ver.xhtml"));
	}
}
