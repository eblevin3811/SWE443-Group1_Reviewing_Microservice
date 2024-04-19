package com.vacationorg.reviewmicroservice;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;

@Entity
public class RestUserReview {
		
		@Id
		private long userID;

        private String userName;

		@OneToMany(mappedBy="userID", cascade = CascadeType.ALL)
		private List<RestReview> reviews;

        @ManyToMany(cascade = CascadeType.ALL)
        private List<RestProperty> properties;

		public RestUserReview(){}

		public RestUserReview(long userID, String userName, List<RestReview> reviews, List<RestProperty> properties) {
			this.userID = userID;
            this.userName = userName;
			this.reviews = reviews;
            this.properties = properties;
		}

        public List<RestProperty> getProperties(){
            return this.properties;
        }

        public void setProperties(List<RestProperty> properties){
            this.properties = properties;
        }

		public long getUserID() {
			return this.userID;
		}

		public void setUserID(Long userID){
			this.userID = userID;
		}

        public String getUserName(){
            return this.userName;
        }

        public void setUserName(String userName){
            this.userName = userName;
        }

		public List<RestReview> getReviews() {
			return this.reviews;
		}

		public void setReviews(List<RestReview> reviews){
			this.reviews = reviews;
		}
	}
