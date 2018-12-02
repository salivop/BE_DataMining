package com.vaitkevicius.main.word.data.repository;

import com.vaitkevicius.main.word.data.db.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordRepository extends MongoRepository<Word, String> {

    Word findFirstByWord(String word);

    boolean existsByWord(String word);

    Word findOneById(String id);
}
