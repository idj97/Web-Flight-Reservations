package dto;

import javax.validation.constraints.NotNull;

public class BasicSearchDTO {
	@NotNull
	private String start;
	@NotNull
	private String end;
	@NotNull
	private String date;

	public BasicSearchDTO() {
		super();
	}

	public BasicSearchDTO(@NotNull String start, @NotNull String end, @NotNull String date) {
		super();
		this.start = start;
		this.end = end;
		this.date = date;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
