package com.vaitkevicius.main.word.services;

import com.vaitkevicius.main.common.PostTags;
import com.vaitkevicius.main.word.data.db.Word;
import com.vaitkevicius.main.word.data.repository.WordRepository;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * *
 * Created By Povilas 12/01/2019
 * *
 **/

@Service
@Log4j2
public class WordAnnotatedService {

    private final String OUTPUT = "C:\\Users\\povil\\Desktop\\NotAnnotatedWords.txt";

    @Autowired
    private WordRepository wordRepository;

    public void saveAnnotatedWords(String link) {

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(link);
            bufferedReader = new BufferedReader(fileReader);

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                getLineValues(currentLine);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Global error");

            e.printStackTrace();
        } finally {

            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
        outputNotAnnotatedWords(OUTPUT);
    }

    public void outputNotAnnotatedWords(String link) {

        List<Word> temp = wordRepository.findAll();
        List<Word> notAnnotatedWords = new ArrayList<>();

        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;


        try {
            fileWriter = new FileWriter(link);
            bufferedWriter = new BufferedWriter(fileWriter);
            for(int i = 0; i < notAnnotatedWords.size(); i++){
                bufferedWriter.write(notAnnotatedWords.get(i).getMainWordFormLT());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bufferedWriter != null)
                    bufferedWriter.close();

                if (fileWriter != null)
                    fileWriter.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

    private void getLineValues(String currentLine) {
        String[] tokens = currentLine.split("\t");

        if (isWordExist(tokens[0])) {
            updateWord(tokens[0], tokens[1]);
        }
    }

    private Word saveWord(Word word) {
        return wordRepository.save(word);
    }


    private Word getWordByWord(String byWord) {
        Word word = wordRepository.findFirstByMainWordFormLT(byWord);
        if (word == null) {
            return null;
        }
        return word;
    }

    private boolean isWordExist(String byWord) {
        Word word = wordRepository.findFirstByMainWordFormLT(byWord);
        if (word == null) {
            return false;
        }
        return true;
    }

    private Word updateWord(String mainWordFormLT, String semanticEvaluation) {
        Word dbWord = getWordByWord(mainWordFormLT);

        dbWord.setSemanticEvaluation(semanticEvaluation);
        return wordRepository.save(dbWord);
    }
}
