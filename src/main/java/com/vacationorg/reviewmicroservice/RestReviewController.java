package com.vacationorg.reviewmicroservice;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
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

	//If we call from user 1 (Timmy), there will be things in the database
	@GetMapping("/prepareReviewList/{userID}")
	public List<RestPropertyReview> prepareReviewList(@PathVariable(value = "userID") long userID){
		
		if (!initPropReview){
			init(123);
		}
		
		//Getting all properties associated w/ user from Scheduling Functional Area
		List<RestProperty> props = userReviewsService.getUserReviews(userID);

		//For each property I get, assign it to or create a new RestPropertyReview
		List<RestPropertyReview> propReviews = new ArrayList<RestPropertyReview>();
		for (int i = 0; i < props.size(); i++){
			
			//Find or create a propertyReview for this property
			RestPropertyReview rpr = listingReviewsService.findPropertyReviewByID(props.get(i).getPropertyID());
			if (rpr == null){
				rpr = new RestPropertyReview(props.get(i).getPropertyID(), null, null);
				listingReviewsService.saveRestPropertyReview(rpr);
			}
			rpr.setProperty(props.get(i));

			//Filter out reviews not by this user
			List<RestReview> myUserReviews = rpr.getReviews();
			if (myUserReviews != null){
				Iterator<RestReview> itr = myUserReviews.iterator();
				while (itr.hasNext()){
					if (itr.next().getUserID() != userID){
						itr.remove();
					}
				}
			}

			//If no reviews exist, create an empty review list
			else{
				myUserReviews = new ArrayList<RestReview>();
			}

			//Save list for this property
			rpr.setReviews(myUserReviews);

			//Add to list of things to return
			propReviews.add(rpr);
			
		}

		return propReviews;
	}

	@GetMapping("/updateReview/{rid}/{user}/{uid}/{content}/{rating}/{pid}/{date}")
	public String updateReview(@PathVariable(value = "rid") long rid, @PathVariable(value = "user") String user, @PathVariable(value = "uid")long uid, @PathVariable(value = "content") String content, @PathVariable(value = "rating") int rating, @PathVariable(value="pid")long pid, @PathVariable(value = "date") String date) {
		//Get review
		RestReview review = userReviewsService.findReviewByReviewID(rid);

		//If review does not exist, make a new one
		if (review == null){
			Date finalDate;
			try{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				finalDate = df.parse(date);
			} catch (Exception e){
				finalDate = new Date();
			}
			
			review = new RestReview(rid, user, uid, content.replaceAll("_", " "), rating, pid, finalDate);
		}

		//Update fields
		review.setComment(content.replaceAll("_", " "));
		review.setRating(rating);
		Date finalDate;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			finalDate = df.parse(date);
		} catch (Exception e){
			finalDate = new Date();
		}
		review.setDate(finalDate);

		//Save changes
		userReviewsService.saveRestReview(review);

		return "ok";
	}
	

	//This is just to put data into the repository on startup.
	private void init(long propertyID){
		RestPropertyReview demoPropReview = new RestPropertyReview(propertyID, null, null);
		RestPropertyReview demoPropReview2 = new RestPropertyReview(propertyID + 1, null, null);
			
		//create some reviews to pre-exist in database
		RestReview demoRestReview1 = new RestReview((long)111, "Demo user 1", (long)123, "demo comment 1", 5, propertyID, new Date());
		RestReview demoRestReview2 = new RestReview((long)112, "Demo user 2", (long)456, "demo comment 2", 3, propertyID, new Date());
		RestReview demoRestReview3 = new RestReview((long)113, "Timmy", (long)1, "Timmy liked this", 5, propertyID, new Date());
		RestReview demoRestReview4 = new RestReview((long)114, "Timmy", (long)1, "Timmy disliked this", 2, propertyID + 1, new Date());
		userReviewsService.saveRestReview(demoRestReview1);
		userReviewsService.saveRestReview(demoRestReview2);
		userReviewsService.saveRestReview(demoRestReview3);
		userReviewsService.saveRestReview(demoRestReview4);

		//Save the reviews to a property (the property info will be filled in later)
		List<RestReview> demoReviews = new ArrayList<RestReview>();
		demoReviews.add(demoRestReview2);
		demoReviews.add(demoRestReview1);
		demoReviews.add(demoRestReview3);
		demoPropReview.setReviews(demoReviews);
		listingReviewsService.saveRestPropertyReview(demoPropReview);

		//Second property to demo someone who stayed at two places
		List<RestReview> demoReviews2 = new ArrayList<RestReview>();
		demoReviews2.add(demoRestReview4);
		demoPropReview2.setReviews(demoReviews2);
		listingReviewsService.saveRestPropertyReview(demoPropReview2);


		initPropReview = true;
	}
}
