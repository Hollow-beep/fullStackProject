package dev.hieplp.moviespring;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//Data access layer
@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
