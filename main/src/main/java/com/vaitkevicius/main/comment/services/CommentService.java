package com.vaitkevicius.main.comment.services;

import com.vaitkevicius.main.comment.converters.ArticleConverter;
import com.vaitkevicius.main.comment.converters.CommentReadArticleConverter;
import com.vaitkevicius.main.comment.data.db.Article;
import com.vaitkevicius.main.comment.data.db.Comment;
import com.vaitkevicius.main.comment.data.dto.ArticleDto;
import com.vaitkevicius.main.comment.data.dto.CommentDto;
import com.vaitkevicius.main.comment.data.repositories.ArticleRepository;
import com.vaitkevicius.main.comment.data.repositories.CommentRepository;
import com.vaitkevicius.main.common.utils.ExceptionFactory;
import com.vaitkevicius.main.emoticon.data.db.Emoticon;
import com.vaitkevicius.main.emoticon.services.EmoticonService;
import com.vaitkevicius.main.word.data.db.StopWord;
import com.vaitkevicius.main.word.data.db.Word;
import com.vaitkevicius.main.word.data.repository.WordRepository;
import com.vaitkevicius.main.word.services.*;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.Comparator.comparingLong;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CommentService {

    static protected final String PUBLIC_COMMENT = "&com=1&reg=0&no=0&s=2";
    static protected final String COMMENTS_LIST_PAGE_NO = "&no=";

    public static final String ARTICLE_NAME = "headline";
    public static final String ARTICLE_AUTHOR = "delfi-author-name";
    public static final String ARTICLE_PUBLISHED_DATE = "source-date";

    public static final String COMMENT_COUNT = "comment-thread-switcher-list-a-anon";
    public static final String COMMENT_DATE = "comment-date";
    public static final String COMMENT_CONTENT = "comment-content-inner";
    public static final String COMMENT_AUTHOR = "comment-author";


    @Autowired
    CommentRepository commentRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    EmoticonService emoticonService;

    @Autowired
    WordInitialDatabaseService wordInitialDatabaseService;

    @Autowired
    StopWordService stopWordService;

    @Autowired
    WordRootService wordRootService;

    @Autowired
    WordService wordService;


    @Autowired
    private ExceptionFactory exceptionFactory;

    @Autowired
    UnrecognizedWordService unrecognizedWordService;

    public Comment getComment(String id) {
        Comment comment = commentRepository.findOneById(id);
        if (comment == null) {
            throw exceptionFactory.getResourseNotFoundException("message.error.commentNotFound", id);

        }
        return comment;
    }
//
//    public List<Comment> getAllComments() {
//        return commentRepository.findAll();
//    }
//
//    public Comment saveComment(Comment comment) {
//        HashMap<String, Integer> words = new HashMap<String, Integer>();
//
//        String[] arr = comment.getComment().split("[^A-Za-zčąįžęėūųšČĄĮŽĘĖŪŲŠ]+$");
////        for (int i = 0; i < arr.length; i += 1) {
////            words.put(arr[i].toLowerCase(), Integer.valueOf(wordRepository.findFirstByMainWordFormLT(arr[i]).));
////        }
////        comment.setCommentWithValue(words);
//        return commentRepository.save(comment);
//    }
//
//    public Comment saveOnlyComments(Comment comment ){
//        List<String> words = new ArrayList<>();
////        for (int i = 0; i < arr.length; i += 1) {
////            if (!arr[i].trim().isEmpty()) {
////                words.add(arr[i].toLowerCase());
////            }
////        }
//        String[] arr = comment.getComment().split("[^A-Za-zčąįžęėūųšČĄĮŽĘĖŪŲŠ]");
//        Arrays.stream(arr).forEach(string -> { if (!string.trim().isEmpty()) {
//            words.add(string.toLowerCase());
//        }});
//
//        comment.setWords(words);
//        return commentRepository.save(comment);
//    }
//
//    public void deleteComment(String id) {
//        Comment comment = commentRepository.findOneById(id);
//
//        if (comment != null) {
//            commentRepository.delete(comment);
//        }
//    }

    public void saveComments(String url) throws Exception {
        log.info("Saving article comments");
        String commentsUrl = url + PUBLIC_COMMENT;

        List<String> articleAuthorAndPublishedDate = getArticleAuthorAndPublishedDate(url);
        readHtmlPage(commentsUrl, articleAuthorAndPublishedDate, url);
    }

    private void readHtmlPage(String commentsUrl, List<String> articleAuthorAndPublishedDate, String articleUrl) throws Exception {
        Document doc = Jsoup.connect(commentsUrl).get();

        String commentsCountOnPost = doc.getElementsByClass(COMMENT_COUNT).select("span").text().replaceAll("[^a-zA-Z0-9]+", "");
        int commentsOnPost = Integer.parseInt(commentsCountOnPost);

        if (!isArticleExist(articleUrl)) {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setArticleUrl(articleUrl);

            articleDto.setArticleName(articleAuthorAndPublishedDate.get(0));
            articleDto.setArticleAuthor(articleAuthorAndPublishedDate.get(1));
            articleDto.setArticlePublishedDate(articleAuthorAndPublishedDate.get(2));

            getArticleComments(commentsOnPost, commentsUrl, articleAuthorAndPublishedDate, articleDto, articleUrl);
        } else {
            getArticleComments(commentsOnPost, commentsUrl, articleAuthorAndPublishedDate, getArticle(articleUrl), articleUrl);
        }
    }

    private List<String> getArticleAuthorAndPublishedDate(String url) throws IOException {
        log.info("Getting article author and published date");
        List<String> articleAuthorAndPublishedDate = new ArrayList<>();
        Document document = Jsoup.connect(url).get();

        articleAuthorAndPublishedDate.add(document.getElementsByClass(ARTICLE_NAME).text());
        articleAuthorAndPublishedDate.add(document.getElementsByClass(ARTICLE_AUTHOR).text().replaceAll(",", ""));
        articleAuthorAndPublishedDate.add(document.getElementsByClass(ARTICLE_PUBLISHED_DATE).text());
        return articleAuthorAndPublishedDate;
    }

    private Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    private boolean isCommentExist(String date, String commentAuthor, String articleUrl) {
        Article article = articleRepository.findFirstByArticleUrl(articleUrl);
        if (article != null) {
            List<Comment> comments = article.getCommentList();
            if (comments != null) {
                for (Comment com : comments) {
                    if (date.equals(com.getDate()) && commentAuthor.equals(com.getCommentAuthor())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isArticleExist(String isExistArticle) {
        Article article = articleRepository.findFirstByArticleUrl(isExistArticle);
        if (article == null) {
            return false;
        }
        return true;
    }

    private List<String> getCommentWords(String getCommentWords) {
        String commentWithReplacedEmojiToTag = replaceAllEmojisToTag(getCommentWords);
        List<String> removesStopWords = removeStopWords(commentWithReplacedEmojiToTag);
        List<String> commentSplitedWithMainForms = splitCommnetWords(removesStopWords);
//        List<String> removesStopWordsAndLtLetters = removeLtLetters(commentSplitedWithMainForms);

        return commentSplitedWithMainForms;
    }

    private List<String> getCommentWordsRoots(String getCommentWords) {
//        String commentWithReplacedEmojiToTag = replaceAllEmojisToTag(getCommentWords);
//        List<String> removesStopWords = removeStopWords(commentWithReplacedEmojiToTag);
//        List<String> commentSplitedWithMainForms = splitCommnetWords(removesStopWords);
//        List<String> getWordsRoots = getWordRootsAndLtLetters(commentSplitedWithMainForms);

//        return getWordsRoots;
        return Collections.singletonList("");
    }

    private ArticleDto getArticle(String articleUrl) {
        Article article = articleRepository.findFirstByArticleUrl(articleUrl);
        return new ArticleConverter().convertToDto(article);
    }

    private void getArticleComments(int commentsOnPost, String commentsUrl, List<String> articleAuthorAndPublishedDate, ArticleDto articleDto, String articleUrl) throws IOException {
        List<String> tempCommentIdList = new ArrayList<>();

        for (int i = 0; i <= commentsOnPost; i += 20) {
            Document commentListOnPage = Jsoup.connect(commentsUrl + COMMENTS_LIST_PAGE_NO + i).get();

            Elements commentDate = commentListOnPage.getElementsByClass(COMMENT_DATE);
            Elements commentContent = commentListOnPage.getElementsByClass(COMMENT_CONTENT);
            Elements commentAuthor = commentListOnPage.getElementsByClass(COMMENT_AUTHOR);

            for (int j = 0; j < commentDate.size(); j++) {
                if (!isCommentExist(commentDate.get(j).text(), commentAuthor.get(j).text(), articleUrl)) {
                    List<String> commentWords = new ArrayList<>(getCommentWords(commentContent.get(j).text()));

                    CommentDto commentDto = new CommentDto();

                    commentDto.setCommentAuthor(commentAuthor.get(j).text());
                    commentDto.setDate(commentDate.get(j).text());
                    commentDto.setComment(commentContent.get(j).text());

                    commentDto.setCleanedComment(getCleanedComment(commentWords));
                    commentDto.setWords(commentWords);
                    commentDto.setMainWordFormsLT(articleAuthorAndPublishedDate);
                    commentDto.setMainWordFormsLTRoots(getCommentWordsRoots(commentContent.get(j).text()));

                    tempCommentIdList.add(saveComment(new CommentReadArticleConverter().convertToEntity(commentDto)).getId());
                }
            }
        }

        List<Comment> comments = tempCommentIdList.stream()
                .map(id -> commentRepository.findOneById(id)).collect(Collectors.toList());
        articleDto.setCommentList(comments);
        saveArticle(new ArticleConverter().convertToEntity(articleDto));
    }

    private String replaceAllEmojisToTag(String getCommentWords) {
        List<Emoticon> emoticonList = emoticonService.getAllEmoticon();

        for (Emoticon emoticon : emoticonList) {
            getCommentWords = getCommentWords.replaceAll("\\Q" + emoticon.getEmoticon() + "\\E", emoticon.getCategory().equals("positive") ? " emoticonPositive " : " c ");
        }
        return getCommentWords;
    }

    private List<String> splitCommnetWords(List<String> words) {
        List<String> splitedCommentWithMainForm = new ArrayList<>();

        for (String word : words) {
            List<String> mainWordForm = wordInitialDatabaseService.getResponseVduTekstynas(word);

            String wordByMainWordForm =
                    wordInitialDatabaseService.findOneByMainWordFormLTOrMainWordFormWithoutLTLetter(mainWordForm.get(0).trim().length() != 0 ? mainWordForm.get(0) : word);

            if (wordByMainWordForm != null) {
                splitedCommentWithMainForm.add(wordByMainWordForm);
            } else {
                String wordToFind = word.length() > 3 ?
                        word.substring(0, (int) (word.length() - (word.length() - word.length() * 0.8))) :
                        word;
                wordInitialDatabaseService.saveNewWords(word, annotateWord(word, wordToFind));
                splitedCommentWithMainForm.add(word);
            }
        }
        return splitedCommentWithMainForm;
    }

    private List<String> removeLtLetters(List<String> commentSplitedWithMainForms) {
        List<String> wordListWithMainForms = new ArrayList<>();
        log.info("Removing LT letters");

        commentSplitedWithMainForms.stream().forEach(word -> {
            wordListWithMainForms.add(wordInitialDatabaseService.removeLtLetters(word));
        });
        return wordListWithMainForms;
    }

    private List<String> removeStopWords(String commentSplitedWithMainForms) {
        List<String> wordsListWithoutStopWords = new ArrayList<>();
        log.info("Removing stop words");

        String[] words = commentSplitedWithMainForms.split("[^A-Za-zčąįžęėūųšČĄĮŽĘĖŪŲŠ0-9]+");

        for (String commentWord : words) {
            StopWord commentWordIsStopWord = stopWordService.isWordIsStopWord(commentWord.toLowerCase());
            if (commentWordIsStopWord == null) {
                wordsListWithoutStopWords.add(commentWord.toLowerCase());
            }
        }
        return wordsListWithoutStopWords;
    }

    private List<String> getWordRootsAndLtLetters(List<String> wordsListWithoutStopWords) {
        List<String> wordRootsListWithoutLtLetters = new ArrayList<>();
        log.info("Getting comment words roots");

        for (String commentWord : wordsListWithoutStopWords) {
            String word = wordInitialDatabaseService.findWordRootByMainWordFormLT(commentWord.toLowerCase());
            if (word != null) {
                wordRootsListWithoutLtLetters.add(word);
            }
        }
        return wordRootsListWithoutLtLetters;
    }

    private String annotateWord(String fullWord, String word) {
//      TODO ieškoti tol kol kažką rasiu...
        List<Word> dbWords = wordRepository.findWordByMainWordFormLTOrMainWordFormWithoutLTLetter(word, word);
//        List<Word> dbWords = wordRepository.findAll();

        if (dbWords.isEmpty()) {
            System.out.println("set category for word " + fullWord);
            Scanner in = new Scanner(System.in);
            switch (in.nextLine()) {
                case "n":
                   return "negative";
                case "p":
                    return "positive";
                case "m":
                    return "neutral";
                default:
                    break;
            }
        }
        return getMapMinDistanceWord(dbWords, fullWord).getCategory();
    }

    private Word getMapMinDistanceWord(List<Word> dbWords, String fullWord) {
        Map<Word, Integer> levenstheinDistances = new HashMap<>();

        do {
            String sorterWord = fullWord.length() > 1 ? fullWord.substring(0, fullWord.length() - 1) : fullWord;
            if (dbWords.size() == 0) {
                dbWords = wordRepository.findWordByMainWordFormLTOrMainWordFormWithoutLTLetter(sorterWord, sorterWord);
            }
        } while (dbWords.size() == 0);

        if (dbWords.size() == 0) {
            String sorterWord = fullWord.substring(0, fullWord.length() - 1);
            dbWords = wordRepository.findWordByMainWordFormLTOrMainWordFormWithoutLTLetter(sorterWord, sorterWord);
        }

        dbWords.stream().forEach(dbWord -> {
            levenstheinDistances.put(dbWord, levenshteinDistance(dbWord.getMainWordFormLT(), fullWord));
            levenstheinDistances.put(dbWord, levenshteinDistance(dbWord.getMainWordFormWithoutLTLetter(), fullWord));
        });

        Integer minValue = levenstheinDistances.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();
        long countAllSameMinDistanceWords = levenstheinDistances.entrySet().stream()
                .filter(p -> p.getValue().equals(minValue))
                .collect(Collectors.counting());

        if (countAllSameMinDistanceWords > 1 && fullWord.length() > 1) {
            getMapMinDistanceWord(dbWords, fullWord.substring(0, fullWord.length() - 1));
        }
        Word aw = new Word();
        Set<Word> w = levenstheinDistances.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), minValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return w.iterator().next();
    }

    private String getCleanedComment(List<String> commentWords) {
        StringBuilder stringBuilder = new StringBuilder();
        commentWords.stream().forEach(word -> {
//            List<Word> dbWords = wordRepository.findAll();
            List<Word> dbWords = wordRepository.findWordByMainWordFormLTOrMainWordFormWithoutLTLetter(word, word);
            Word nearestWord = getMapMinDistanceWord(dbWords, word);
            stringBuilder.append(nearestWord.getMainWordFormLT());
            stringBuilder.append(" ");
        });
        return stringBuilder.toString();
    }

    private Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    private int levenshteinDistance(String firstWord, String secondWord) {
        firstWord = firstWord.toLowerCase();
        secondWord = secondWord.toLowerCase();

        int[] costs = new int[secondWord.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= firstWord.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= secondWord.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), firstWord.charAt(i - 1) == secondWord.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[secondWord.length()];
    }
}