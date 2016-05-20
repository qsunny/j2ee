package com.aaron.search.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AtmIndex implements Serializable {

	private String title;
	private String text;
	private String date;
	private String views;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	
	@Override
	public String toString() {
		return "AtmIndex [title=" + title + ", text=" + text + ", date=" + date + ", views=" + views + "]";
	}
	
	
}
