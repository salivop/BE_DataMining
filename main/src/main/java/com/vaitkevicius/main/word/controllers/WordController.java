package com.vaitkevicius.main.word.controllers;

import com.vaitkevicius.main.common.UrlConst;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessage;
import com.vaitkevicius.main.common.validation.groups.Create;
import com.vaitkevicius.main.common.validation.groups.Update;
import com.vaitkevicius.main.word.converters.WordConverter;
import com.vaitkevicius.main.word.data.dto.WordDto;
import com.vaitkevicius.main.word.services.WordService;
import com.vaitkevicius.main.word.validators.WordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(UrlConst.WORD)
public class WordController {

    @Autowired
    WordService wordService;

    @Autowired
    WordValidator wordValidator;

    @Autowired
    private MessageSource messages;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public WordDto getWord(@PathVariable String id) {
        wordValidator.validateGetWord(id);
        return new WordConverter().toDto(wordService.getWord(id));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{word}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public WordDto getWordByWord(@PathVariable String word) {
        wordValidator.validateGetWordByWord(word);
        return new WordConverter().toDto(wordService.getWordByWord(word));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<WordDto> getAllWords() {
        wordValidator.validateGetAllWords();
        return new WordConverter().toDto(wordService.getAllWords()
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage saveWord(@Validated(Create.class) @RequestBody WordDto wordDto, BindingResult bindingResult) {
        wordValidator.validateCreateWord(wordDto, bindingResult);
        wordService.saveWord(new WordConverter().convertToEntity(wordDto));

        return ResponseMessage.getSuccess(messages, "message.success.create.word");
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage updateWord(@PathVariable String id, @Validated(Update.class) @RequestBody WordDto wordDto, BindingResult bindingResult) {
        wordValidator.validateUpdateWord(wordDto, bindingResult, id);
        wordService.saveWord(new WordConverter().convertToEntity(wordDto));

        return ResponseMessage.getSuccess(messages, "message.success.update.word");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage deleteWord(@PathVariable String id) {
        wordValidator.validateDeleteWord(id);
        wordService.deleteWord(id);
        return ResponseMessage.getSuccess(messages, "message.success.delete.word");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage deleteAllWords() {
        wordValidator.validateDeleteAllWords();
        wordService.deleteAllWords();
        return ResponseMessage.getSuccess(messages, "message.success.deleteAll.words");
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/word-list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage saveWordsList(@Validated(Create.class) @RequestBody WordDto[] wordDtoList, BindingResult bindingResult) {
        wordValidator.validateWordList(wordDtoList, bindingResult);
        WordConverter wordConverter = new WordConverter();
        for (WordDto wordDto : wordDtoList) {
            wordService.saveWord(wordConverter.convertToEntity(wordDto));
        }

        return ResponseMessage.getSuccess(messages, "message.success.createList.word");
    }
}

