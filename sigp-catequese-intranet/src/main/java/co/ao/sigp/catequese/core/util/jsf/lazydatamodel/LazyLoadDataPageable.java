package co.ao.sigp.catequese.core.util.jsf.lazydatamodel;


@FunctionalInterface
public interface LazyLoadDataPageable {
	
    @SuppressWarnings("rawtypes")
	LazyLoadPageResult getData(LazyLoadPageCriteria pageCriteria);
}