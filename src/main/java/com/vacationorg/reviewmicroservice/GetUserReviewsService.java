package com.vacationorg.reviewmicroservice;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    public List<RestProperty> getUserReviews(long userID){
        
        List<RestProperty> props = new ArrayList<RestProperty>();


		//Getting all properties associated w/ user from Scheduling Functional Area
		JSONObject respjson = null;
		String url = "http://localhost:8090/getMemberInfo/" + Long.toString(userID);
		try{
			respjson = new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));

			JSONArray retProps = respjson.getJSONArray("properties");
			if (retProps != null){
				for (int i = 0; i < retProps.length(); i++){
					//Debug
					System.out.println(retProps.getJSONObject(i));
					
					String start = retProps.getJSONObject(i).getString("startDate");
					String end   = retProps.getJSONObject(i).getString("endDate");
					String pName = retProps.getJSONObject(i).getString("name");
					String ploc  = retProps.getJSONObject(i).getString("city");
					Long propID  = retProps.getJSONObject(i).getLong("propertyID");

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date startd = df.parse(start);
					Date endd   = df.parse(end);

					RestProperty rp = new RestProperty(propID, ploc, pName, startd, endd);

					props.add(rp);
				}
			}
		} catch (JSONException e){
			System.out.println("JSON Exception!");
		} catch (IOException e){
			System.out.println("IO Exception!");
		} catch (ParseException e){
			System.out.println("Parse Exception!");
		}

        return props;
    }
}
