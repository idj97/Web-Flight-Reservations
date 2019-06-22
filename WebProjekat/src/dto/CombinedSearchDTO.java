package dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CombinedSearchDTO {
	@Valid
	@NotNull
	private BasicSearchDTO basicSearch;

	@Valid
	@NotNull
	private FilterDTO filter;

	public CombinedSearchDTO() {
		super();
	}

	public BasicSearchDTO getBasicSearch() {
		return basicSearch;
	}

	public void setBasicSearch(BasicSearchDTO basicSearch) {
		this.basicSearch = basicSearch;
	}

	public FilterDTO getFilter() {
		return filter;
	}

	public void setFilter(FilterDTO filter) {
		this.filter = filter;
	}

}
