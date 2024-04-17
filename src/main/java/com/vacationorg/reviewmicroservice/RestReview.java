package com.vacationorg.reviewmicroservice;


	public class RestReview {

		private final long id;
		private final String content;

		public RestReview(long id, String content) {
			this.id = id;
			this.content = content;
		}

		public long getId() {
			return id;
		}

		public String getContent() {
			return content;
		}
	}
