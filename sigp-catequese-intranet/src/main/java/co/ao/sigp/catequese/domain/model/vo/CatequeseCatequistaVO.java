package co.ao.sigp.catequese.domain.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.util.StringUtils;

import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadIModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CatequeseCatequistaVO implements LazyLoadIModel {

	private Integer id;

	private String codigoCatequista;

	private String nomeCompleto;

	private LocalDate dataInicioCatecismo;

	private LocalDateTime dataRemocao;
	
	private String numeroTelefone1;

	private String numeroTelefone2;
	
	private String grupoMovimento;
	
	private String sacramentos;
	
	public String getImagem() {
		
		return "assets/images/person-picture.png";
	}
	
	public String getNumeroTelefone1() {
		
		return StringUtils.hasText(numeroTelefone1) ? numeroTelefone1 : numeroTelefone2;
	}
	
	public void setNumeroTelefone1(String numeroTelefone) {
		
		this.numeroTelefone1 = this.numeroTelefone2 = numeroTelefone;
	}
	
	public String getGrupoMovimento() {
		
		return this.grupoMovimento != null ? this.grupoMovimento : "";
	}
}
