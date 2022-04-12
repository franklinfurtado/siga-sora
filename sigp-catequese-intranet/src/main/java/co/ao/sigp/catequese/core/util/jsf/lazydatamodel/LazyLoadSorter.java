package co.ao.sigp.catequese.core.util.jsf.lazydatamodel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import co.ao.sigp.catequese.core.util.primefaces.ShowcaseUtil;

public class LazyLoadSorter  implements Comparator<Object> {

    private String sortField;
    private SortOrder sortOrder;

    public LazyLoadSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public int compare(Object customer1, Object customer2) {
        try {
            Object value1 = ShowcaseUtil.getPropertyValueViaReflection(customer1, sortField);
            Object value2 = ShowcaseUtil.getPropertyValueViaReflection(customer2, sortField);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}