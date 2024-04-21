package com.vacationorg.reviewmicroservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getUserReviewsService")
public class GetUserReviewsService {
    @Autowired
    RestReviewRepository restReviewRepository;

    @Autowired
    RestPropertyReviewRepository restPropertyReviewRepository;

    //Save
    public void saveRestReview(RestReview restReview){
        restReviewRepository.save(restReview);
    }

    //Find
    public List<RestReview> findReviewsByUserID(long userID){
        return restReviewRepository.findByUserID(userID);
    }

    public RestReview findReviewByReviewID(long reviewID){
        try{
            return restReviewRepository.findByReviewID(reviewID).get(0);
        } catch (Exception e){
            return null;
        }
    }

    //Call outside
    public String getUserReviews(long userID){
        //TODO: CALL SCHEDULING FUNCTIONAL AREA
        return null;
    }
}
