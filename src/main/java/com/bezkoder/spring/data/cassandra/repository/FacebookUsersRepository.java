package com.bezkoder.spring.data.cassandra.repository;

import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FacebookUsersRepository extends MongoRepository<FacebookUsers,String> {

    @Query("db.getCollection('facebook_users').find().limit(10).skip(0)")
    List<FacebookUsers> findAllByLimitAndSkip();
}
