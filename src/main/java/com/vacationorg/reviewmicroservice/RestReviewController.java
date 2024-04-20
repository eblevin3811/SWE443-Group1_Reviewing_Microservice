package com.vacationorg.reviewmicroservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestReviewController {

	@Autowired
	GetListingReviewsService listingReviewsService;

	@Autowired
	GetUserReviewsService userReviewsService;
	
	private boolean initPropReview = false;

	//@GetMapping("/greeting")
	//public RestReview greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	//	return new RestReview(counter.incrementAndGet(), String.format(template, name));
	//}

	@GetMapping("/getPropertyReviews/{propertyID}")
	public RestPropertyReview getPropertyReviews(@PathVariable(value = "propertyID") long propertyID){
		//Build review list given property id
		if (!initPropReview){
			init(propertyID);
		}

		RestPropertyReview toDisplay = listingReviewsService.findPropertyReviewByID(propertyID);

		if (toDisplay == null)
			return new RestPropertyReview();
		
		return toDisplay;
	}

	@GetMapping("/prepareReviewList/{userID}")
	public List<RestReview> prepareReviewList(@RequestParam(value = "userID", defaultValue = "123") long userID){
		if (!initPropReview){
			init(123);
		}

		return userReviewsService.findReviewsByUserID(userID);
	}

	private void init(long propertyID){
		RestPropertyReview demoPropReview = new RestPropertyReview(propertyID, null);
			
		//create some reviews
		RestReview demoRestReview1 = new RestReview((long)111, "Demo user 1", (long)123, "demo comment 1", 5, propertyID, new Date());
		RestReview demoRestReview2 = new RestReview((long)112, "Demo user 2", (long)456, "demo comment 2", 3, propertyID, new Date());
		userReviewsService.saveRestReview(demoRestReview1);
		userReviewsService.saveRestReview(demoRestReview2);

		List<RestReview> demoReviews = new ArrayList<RestReview>();
		demoReviews.add(demoRestReview2);
		demoReviews.add(demoRestReview1);
		demoPropReview.setReviews(demoReviews);
		listingReviewsService.saveRestPropertyReview(demoPropReview);

		initPropReview = true;
	}
}
