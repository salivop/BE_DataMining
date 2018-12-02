package com.vaitkevicius.main.word.services;

import com.vaitkevicius.main.common.utils.ExceptionFactory;
import com.vaitkevicius.main.word.data.db.Word;
import com.vaitkevicius.main.word.data.repository.WordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class WordService {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    private ExceptionFactory exceptionFactory;

    public Word getWord(String id) {
        Word word = wordRepository.findOneById(id);
        if (word == null) {
            throw exceptionFactory.getResourseNotFoundException("message.error.wordNotFound", id);
        }
        return word;
    }

    public Word getWordByWord(String byWord) {
        Word word = wordRepository.findFirstByWord(byWord);
        if (word == null) {
            throw exceptionFactory.getResourseNotFoundException("message.error.wordNotFound", byWord);
        }
        return word;
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Word saveWord(Word word) {
        return wordRepository.save(word);
    }

    public void deleteWord(String id) {
        Word word = wordRepository.findOneById(id);

        if (word != null) {
            wordRepository.delete(word);
        }
    }

    public void deleteAllWords() {
        wordRepository.deleteAll();
    }

}
