package co.ao.sigp.catequese.core.util.jsf.lazydatamodel;

import java.io.Serializable;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LazyLoadPageCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private int first;
	private int size;
	private Map<String, FilterMeta> filteredBy;
	private Boolean isAscending;
	private Map<String, SortMeta> sortedBy;
}
