package com.vacationorg.reviewmicroservice;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RestReview {

		@Id
		private Long reviewID;

		private String userName;
        private Long userID;
		private Long propertyID;
        private String comment;
		private Date date;
        private int rating;

		public RestReview(){}

		public RestReview(long reviewID, String userName, long userID, String comment, int rating, Long propertyID, Date date) {
			this.reviewID = reviewID;
            this.userName = userName;
            this.userID = userID;
            this.comment = comment;
            this.rating = rating;
			this.propertyID = propertyID;
			this.date = date;
		}

		public long getPropertyID(){
			return this.propertyID;
		}

		public long getReviewID() {
			return this.reviewID;
		}

		public String getUserName() {
			return this.userName;
		}

        public long getUserID() {
			return this.userID;
		}

        public String getComment() {
			return this.comment;
		}

        public int getRating(){
            return this.rating;
        }

		public Date getDate(){
			return this.date;
		}

		//Setters
		public void setReviewID(Long reviewID) {
			this.reviewID = reviewID;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

        public void setUserID(Long userID) {
			this.userID = userID;
		}

        public void setComment(String comment) {
			this.comment = comment;
		}

        public void setRating(int rating){
            this.rating = rating;
        }

		public void setPropertyID(long propertyID){
			this.propertyID = propertyID;
		}

		public void setDate(Date date){
			this.date = date;
		}


	}
