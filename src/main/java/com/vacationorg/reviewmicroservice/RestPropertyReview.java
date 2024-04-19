package com.vacationorg.reviewmicroservice;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class RestPropertyReview {
		
		@Id
		private long propertyID;

		@OneToMany(mappedBy="propertyID", cascade = CascadeType.ALL)
		private List<RestReview> reviews;

		public RestPropertyReview(){}

		public RestPropertyReview(long propertyID, List<RestReview> reviews) {
			this.propertyID = propertyID;
			this.reviews = reviews;
		}

		public long getPropertyID() {
			return this.propertyID;
		}

		public void setPropertyID(Long propertyID){
			this.propertyID = propertyID;
		}

		public List<RestReview> getReviews() {
			return this.reviews;
		}

		public void setReviews(List<RestReview> reviews){
			this.reviews = reviews;
		}
	}
