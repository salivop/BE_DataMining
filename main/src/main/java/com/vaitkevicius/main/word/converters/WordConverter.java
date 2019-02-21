package com.vaitkevicius.main.word.converters;

import com.vaitkevicius.main.common.converter.AbstractConverter;
import com.vaitkevicius.main.word.data.db.Word;
import com.vaitkevicius.main.word.data.dto.WordDto;

public class WordConverter extends AbstractConverter<Word, WordDto> {

    @Override
    public WordDto convertToDto(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .mainWordFormLT(word.getMainWordFormLT())
                .mainWordFormWithoutLTLetter(word.getMainWordFormWithoutLTLetter())
                .mainWordFormLT(word.getMainWordFormLT())
                .mainWordFormLTRoot(word.getMainWordFormLTRoot())
                .mainWordFormRootWithoutLTLetter(word.getMainWordFormRootWithoutLTLetter())
                .wordSaveTime(word.getWordSaveTime())
                .category(word.getCategory())
                .wordWeight(word.getWordWeight())
                .wordType(word.getWordType())
                .semanticEvaluation(word.getSemanticEvaluation())
                .build();
    }

    @Override
    public Word convertToEntity(WordDto wordDto) {
        return Word.builder()
                .id(wordDto.getId())
                .mainWordFormLT(wordDto.getMainWordFormLT())
                .mainWordFormWithoutLTLetter(wordDto.getMainWordFormWithoutLTLetter())
                .mainWordFormLT(wordDto.getMainWordFormLT())
                .mainWordFormLTRoot(wordDto.getMainWordFormLTRoot())
                .mainWordFormRootWithoutLTLetter(wordDto.getMainWordFormRootWithoutLTLetter())
                .wordSaveTime(wordDto.getWordSaveTime())
                .category(wordDto.getCategory())
                .wordWeight(wordDto.getWordWeight())
                .wordType(wordDto.getWordType())
                .semanticEvaluation(wordDto.getSemanticEvaluation())
                .build();
    }
}


