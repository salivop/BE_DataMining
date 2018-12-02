package com.vaitkevicius.main.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.util.Arrays;
import java.util.Locale;

/**
 * *
 * Created By Povilas 13/11/2018
 * *
 **/
@Service
public class MessageUtils {

    @Autowired
    protected MessageSource messages;

    public String getMessage(String messageCode, Object... args) {
        if (StringUtils.isNotBlank(messageCode)) {
            return messages.getMessage(messageCode, args, LocaleContextHolder.getLocale());
        }
        return "";
    }

    public String serialize(String code, Object... args) {
        StringBuilder sb = new StringBuilder("i18n://").append(UriUtils.encode(code, "UTF-8"));
        for (Object arg : args) {
            sb.append("/").append(UriUtils.encode(arg.toString(), "UTF-8"));
        }
        return sb.toString();
    }

    public String deserialize(String i18nUri) {
        return deserialize(i18nUri, LocaleContextHolder.getLocale());
    }

    public String deserialize(String i18nUri, Locale locale) {
        if (i18nUri == null || !i18nUri.startsWith("i18n://")) {
            return i18nUri;
        }

        String[] parts = i18nUri.replace("i18n://", "").split("/");

        String code = UriUtils.decode(parts[0], "UTF-8");
        String[] params = Arrays.stream(parts).skip(1).map(param -> UriUtils.decode(param, "UTF-8")).toArray(String[]::new);

        String result = messages.getMessage(code, params, locale);

        return result.equals(code) ? i18nUri : result;
    }
}
