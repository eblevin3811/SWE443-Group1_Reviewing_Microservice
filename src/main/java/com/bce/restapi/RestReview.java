package com.bce.restapi;


	public class RestGreeting {

		private final long id;
		private final String content;

		public RestGreeting(long id, String content) {
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
