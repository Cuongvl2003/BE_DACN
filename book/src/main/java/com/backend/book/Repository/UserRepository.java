package com.backend.book.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backend.book.Model.Entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

