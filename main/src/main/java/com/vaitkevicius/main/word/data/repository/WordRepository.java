package com.vaitkevicius.main.word.data.repository;

import com.vaitkevicius.main.word.data.db.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WordRepository extends MongoRepository<Word, String> {

    String FIND_BY_WORD = "" +
            "{" +
            "$and: [" +
            "{'mainWordFormLT' : { $regex: ?0, $options: 'i' } }," +
            "{'mainWordFormWithoutLTLetter': {$regex: ?0, $options: 'i' }}" +
            "         ]" +

            "}";

    @Query(value = FIND_BY_WORD)
    List<Word> findWordByMainWordFormLTOrMainWordFormWithoutLTLetter(String word, String wordWithoutLtLetters);


    @Query(value = FIND_BY_WORD)
    List<Word> findFirstByMainWordFormLTOrMainWordFormWithoutLTLetterIgnoreCase(String word, String wordWithoutLtLetters);


    Word findFirstByMainWordFormLT(String mainWordFormLT);

    Word findOneByMainWordFormLT(String mainWordFormLT);

    boolean existsByMainWordFormLT(String word);

    boolean existsByMainWordFormLTOrMainWordFormWithoutLTLetter(String word, String withoutLtLetters);

    List<Word> findAllByWordTypeAndCategory(String wordType, String category);

    List<Word> findAllByMainWordFormLTRoot(String wordRoot);

    Word findOneById(String id);

    Word findFirstByMainWordFormLTOrMainWordFormWithoutLTLetter(String word, String withoutLtLetters);

    Word findOneByMainWordFormWithoutLTLetter(String mainWordFormWithoutLTLetter);

    Word findFirstByMainWordFormLTLike(String word);


}
