package com.example.demo.dto;

import java.util.List;

public class PostDto {

	private List<Data> data;

	public PostDto(List<Data> data) {
		this.data = data;
	}

	public PostDto() {
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public static class Data {

		private String id;
		private String title;
		private String link;
		private Integer ups;

		public Data(String id, String title, String link, Integer ups) {
			this.id = id;
			this.title = title;
			this.link = link;
			this.ups = ups;
		}

		public Data() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public Integer getUps() {
			return ups;
		}

		public void setUps(Integer ups) {
			this.ups = ups;
		}
	}
}
