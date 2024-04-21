package com.vacationorg.reviewmicroservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getListingReviewsService")
public class GetListingReviewsService {
    @Autowired
    RestReviewRepository restReviewRepository;

    @Autowired
    RestPropertyReviewRepository restPropertyReviewRepository;

    //Save
    public void saveRestPropertyReview(RestPropertyReview restPropertyReview){
        restPropertyReviewRepository.save(restPropertyReview);
    }

    //Find
    public RestPropertyReview findPropertyReviewByID(long propertyID){
        List<RestPropertyReview> propertyReviews = restPropertyReviewRepository.findByPropertyID(propertyID);
        if (propertyReviews == null){
            return null;
        }

        if (propertyReviews.size() == 0)
            return null;

        return propertyReviews.get(0);
    }

    public List<RestReview> findReviewsByPropertyID(long propertyID){
        return restReviewRepository.findByPropertyID(propertyID);
    }
}
