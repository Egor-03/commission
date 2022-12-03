package by.grsu.anikevich.comission.web.dto;

public class TableStateDto {

	public static final String KEY_CURRENT_PAGE_TABLE_STATE = "currentPageTableState";

	private SortDto sort;

	private long pageCount;

	private int page = 1;

	private int itemsPerPage;

	private long totalCount;

	public TableStateDto(final int itemsPerPage) {
		super();
		this.itemsPerPage = itemsPerPage;
	}

	public SortDto getSort() {
		return sort;
	}

	private void setSort(final SortDto sort) {
		this.sort = sort;
	}

	public void setSort(final String sortColumn, String defaultSortColumn) {
		if (sortColumn == null) {
			if (getSort() == null) {
				setSort(new SortDto(defaultSortColumn));
			}
			return;
		}

		final String[] sortParams = sortColumn.split(":");
		// unsafe operation below but assumes that JSP doesn't have bugs
		if (sortParams.length == 1) {
			setSort(new SortDto(sortParams[0]));
		} else {
			setSort(new SortDto(sortParams[0], "asc".equals(sortParams[1])));
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(final Integer pageNumber) {
		if ((pageNumber == null) || (pageNumber == 0)) {
			return;
		}

		this.page = pageNumber;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public boolean getFirstPage() {
		return getPage() == 1;
	}

	public boolean getLastPage() {
		return getPage() >= this.pageCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
		this.pageCount = (totalCount / itemsPerPage);
		if ((totalCount % itemsPerPage) > 0) {
			this.pageCount++;
		}
	}

	public long getTotalCount() {
		return totalCount;
	}
}