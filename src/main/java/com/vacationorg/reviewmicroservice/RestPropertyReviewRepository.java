package com.vacationorg.reviewmicroservice;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RestPropertyReviewRepository extends CrudRepository<RestPropertyReview, Long>{
    List<RestPropertyReview> findByPropertyID(long propertyID);
}
