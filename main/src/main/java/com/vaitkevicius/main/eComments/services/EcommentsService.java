package com.vaitkevicius.main.eComments.services;

import com.vaitkevicius.main.comment.converters.CommentConverter;
import com.vaitkevicius.main.comment.data.db.Comment;
import com.vaitkevicius.main.comment.data.dto.CommentDto;
import com.vaitkevicius.main.comment.services.CommentService;
import com.vaitkevicius.main.common.PostTags;
import com.vaitkevicius.main.eComments.data.repository.EcommentsRepository;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * *
 * Created By Povilas 02/12/2018
 * *
 **/

@Service
@Log4j2
public class EcommentsService {

    static protected final String PUBLIC_COMMENT = "&com=1&reg=0&no=0&s=2";
    static protected final String COMMENTS_LIST_PAGE_NO = "&no=";

    @Autowired
    EcommentsRepository ecommentsRepository;

    @Autowired
    CommentService commentService;

    public void saveEcomments(String url) throws Exception {
        String pageUrl = url + PUBLIC_COMMENT;
        readHtmlPage(pageUrl);
    }

    private void readHtmlPage(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();

        String commentsCountOnPost = doc.getElementsByClass(PostTags.COMMENT_COUNT).select("span").text().replaceAll("[^a-zA-Z0-9]+", "");
        int commentsOnPost = Integer.parseInt(commentsCountOnPost);

        saveCommentsToDb(commentsOnPost, url);
    }

    private void saveCommentsToDb(int commentsOnPost, String url) throws IOException {
        for (int i = 0; i <= commentsOnPost; i += 20) {
            Document commentListOnPage = Jsoup.connect(url + COMMENTS_LIST_PAGE_NO + i).get();

            Elements commentDate = commentListOnPage.getElementsByClass(PostTags.COMMENT_DATE);
            Elements commentContent = commentListOnPage.getElementsByClass(PostTags.COMMENT_CONTENT);
            Elements commentAuthor = commentListOnPage.getElementsByClass(PostTags.COMMENT_AUTHOR);

            for (int j = 0; j < commentDate.size(); j++) {
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentAuthor(commentAuthor.get(j).text());
                commentDto.setDate(commentDate.get(j).text());
                commentDto.setComment(commentContent.get(j).text());
//                CommentDto commentDto = new CommentDto(commentAuthor.get(j).text(), commentDate.get(j).text(), commentContent.get(j).text(), null);
                commentService.saveOnlyComments(new CommentConverter().convertToEntity(commentDto));
            }
        }
    }
}
