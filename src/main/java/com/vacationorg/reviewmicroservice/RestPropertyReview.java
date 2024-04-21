package com.vacationorg.reviewmicroservice;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class RestPropertyReview {
		
		@Id
		private long propertyID;

		@OneToOne(cascade = CascadeType.ALL)
		private RestProperty property;

		@OneToMany(mappedBy="propertyID", cascade = CascadeType.ALL)
		private List<RestReview> reviews;

		public RestPropertyReview(){}

		public RestPropertyReview(long propertyID, List<RestReview> reviews, RestProperty property) {
			this.propertyID = propertyID;
			this.reviews = reviews;
			this.property = property;
		}

		public RestProperty getProperty(){
			return this.property;
		}

		public void setProperty(RestProperty property){
			this.property=property;
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
