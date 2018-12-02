package com.vaitkevicius.main.word.converters;

import com.vaitkevicius.main.common.converter.AbstractConverter;
import com.vaitkevicius.main.word.data.db.Word;
import com.vaitkevicius.main.word.data.dto.WordDto;

public class WordConverter extends AbstractConverter<Word, WordDto> {

    @Override
    public WordDto convertToDto(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .word(word.getWord())
                .wordValue(word.getWordValue())
                .build();
    }

    @Override
    public Word convertToEntity(WordDto wordDto) {
        return Word.builder()
                .id(wordDto.getId())
                .word(wordDto.getWord())
                .wordValue(wordDto.getWordValue())
                .build();
    }
}
