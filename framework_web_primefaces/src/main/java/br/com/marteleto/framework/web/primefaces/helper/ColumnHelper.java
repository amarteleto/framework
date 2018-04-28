package br.com.marteleto.framework.web.primefaces.helper;

import java.io.Serializable;

public class ColumnHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String header;
	private String property;
	private String style;
	private String styleClass;
	private String sort;
	private Integer abbreviate;
    private boolean link;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public String getSort() {
		if (this.sort == null) {
			return this.getProperty();
		}
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Integer getAbbreviate() {
		return abbreviate;
	}
	public void setAbbreviate(Integer abbreviate) {
		this.abbreviate = abbreviate;
	}
	public boolean isLink() {
		return link;
	}
	public void setLink(boolean link) {
		this.link = link;
	}
}