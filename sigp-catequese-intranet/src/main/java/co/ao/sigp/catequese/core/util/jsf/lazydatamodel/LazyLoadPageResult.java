package co.ao.sigp.catequese.core.util.jsf.lazydatamodel;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LazyLoadPageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter
	private List<T> data;
	private int size;
	private int index;
	private long count;
}
