package co.ao.sigp.catequese.core.util.jsf.lazydatamodel;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.apache.commons.collections.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

import co.ao.sigp.catequese.core.util.primefaces.ShowcaseUtil;

public class LazyLoadCustomDataModel<T extends LazyLoadIModel> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private LazyLoadPageResult<T> page;

	private LazyLoadDataPageable dataPageable;

	public LazyLoadCustomDataModel(LazyLoadDataPageable dataPageable) {

		this.dataPageable = dataPageable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

		try {

			LazyLoadPageCriteria pageCriteria = new LazyLoadPageCriteria();

			pageCriteria.setFilteredBy(filterBy);

			pageCriteria.setFirst(first);

			pageCriteria.setSize(pageSize);

			page = this.dataPageable.getData(pageCriteria);

			if (!sortBy.isEmpty()) {
				List<Comparator<Object>> comparators = sortBy.values().stream()
						.map(o -> new LazyLoadSorter(o.getField(), o.getOrder())).collect(Collectors.toList());
				Comparator<T> cp = ComparatorUtils.chainedComparator(comparators); // from apache
				page.getData().sort(cp);
			}

			List<T> mList = page.getData().stream().skip(first)
					.filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
					.limit(pageSize)
					.collect(Collectors.toList());
			
			page.setData(mList);

		} catch (Exception ex) {

			page = new LazyLoadPageResult<>(new ArrayList<>(), 0, first, pageSize);
		}

		this.setRowCount((int) page.getCount());

		return page.getData();
	}

	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	public T getRowData(String rowKey) {

		List<T> data = (List<T>) getWrappedData();

		for (T d : data) {

			if (d.getId() != null) {

				if (d.getId().toString().equals(rowKey)) {

					return d;
				}
			}
		}
		return null;
	}

	@Override
	public String getRowKey(T object) {

		return object != null ? object.getId().toString() : null;
	}

	private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
		boolean matching = true;

		for (FilterMeta filter : filterBy) {
			FilterConstraint constraint = filter.getConstraint();
			Object filterValue = filter.getFilterValue();

			try {
				Object columnValue = String.valueOf(ShowcaseUtil.getPropertyValueViaReflection(o, filter.getField()));
				matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
			} catch (ReflectiveOperationException | IntrospectionException e) {
				matching = false;
			}

			if (!matching) {
				break;
			}
		}

		return matching;
	}

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		// TODO Auto-generated method stub
		return 0;
	}
}