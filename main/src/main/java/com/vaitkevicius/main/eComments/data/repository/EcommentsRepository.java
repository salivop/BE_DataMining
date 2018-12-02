package com.vaitkevicius.main.eComments.data.repository;

import com.vaitkevicius.main.eComments.data.db.Ecomments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * *
 * Created By Povilas 02/12/2018
 * *
 **/
@Repository
public interface EcommentsRepository extends MongoRepository<Ecomments,String> {
}
