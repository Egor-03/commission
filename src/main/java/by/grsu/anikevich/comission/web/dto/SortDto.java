package by.grsu.anikevich.comission.web.dto;


public class SortDto {

	private String column;

	private boolean ascending;

	public SortDto(final String column) {
		this(column, false);
	}

	public SortDto(final String column, final boolean ascending) {
		super();
		this.column = column;
		this.ascending = ascending;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(final String column) {
		this.column = column;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(final boolean ascending) {
		this.ascending = ascending;
	}
}