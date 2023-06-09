package dev.hieplp.moviespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbID){
        //Create review object with new review body
        Review review = new Review(reviewBody);
        //Insert review body to Data access Layer
        reviewRepository.insert(review);
        //Update data to specific movie class in MongoDB database base on ImdbId
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbID))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
