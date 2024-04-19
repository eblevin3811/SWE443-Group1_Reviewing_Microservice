package com.vacationorg.reviewmicroservice;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RestReviewRepository extends CrudRepository<RestReview, Long>{
    
    List<RestReview> findByReviewID(Long reviewID);

    List<RestReview> findByPropertyID(Long propertyID);

    List<RestReview> findByUserID(long userID);
}
