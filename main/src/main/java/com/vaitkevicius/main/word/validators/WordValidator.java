package com.vaitkevicius.main.word.validators;

import com.vaitkevicius.main.common.validation.validators.AbstractValidator;
import com.vaitkevicius.main.word.data.db.Word;
import com.vaitkevicius.main.word.data.dto.WordDto;
import com.vaitkevicius.main.word.data.repository.WordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Log4j2
public class WordValidator extends AbstractValidator {

    @Autowired
    WordRepository wordRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

    public void validateGetWord(String id) {
        if (!wordRepository.existsById(id)) {
            throwException(null, GET, "WordDto.id.doesNotExist");
        }
    }

    public void validateGetWordByWord(String word) {
        if (!wordRepository.existsByWord(word)) {
            throwException(null, GET, "WordDto.word.doesNotExist");
        }
    }

    public void validateGetAllWords() {
        if (wordRepository.findAll().size() == 0) {
            throwException(null, GET, "WordDto.all.doesNotExist");
        }
    }

    public void validateCreateWord(WordDto wordDto, Errors errors) {
        throwIfHaveErrors(errors, SAVE);
        validateCommon(wordDto, errors, null);
        throwIfHaveErrors(errors, SAVE);
    }

    private void validateCommon(WordDto wordDto, Errors errors, Word dbWord) {
        validateWordValue(wordDto, null, errors);
    }

    private void validateWordValue(WordDto wordDto, Word dbWord, Errors errors) {
        if (!(wordDto.getWordValue() >= 0) || !(wordDto.getWordValue() <= 10)) {
            rejectValue(errors, "wordValue", "WordDto.wordValue.incorrect");
            return;
        }
    }

    public void validateUpdateWord(WordDto wordDto, Errors errors, String id) {
        throwIfHaveErrors(errors, UPDATE);
        Word word = wordRepository.findOneById(id);

        if (word == null) {
            throwResourceNotFound("WordDto.word.notFound", wordDto.getId());
        }
        throwIfHaveErrors(errors, UPDATE);
    }

    public void validateDeleteWord(String id) {
        if (!wordRepository.existsById(id)) {
            throwException(null, GET, "WordDto.id.doesNotExist");
        }
    }

    public void validateDeleteAllWords() {
        if (wordRepository.findAll().size() == 0) {
            throwException(null, GET, "WordDto.all.doesNotExist");
        }
    }

    public void validateWordList(WordDto[] wordDtos, Errors errors) {
        if (wordDtos.length < 1) {
            throwResourceNotFound("WordDto.wordList.tooShort", wordDtos.length);
        }
    }
}
