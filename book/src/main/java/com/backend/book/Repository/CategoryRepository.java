package com.backend.book.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backend.book.Model.Entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
