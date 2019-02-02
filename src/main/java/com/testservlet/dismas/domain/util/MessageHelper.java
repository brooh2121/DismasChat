package com.testservlet.dismas.domain.util;

import com.testservlet.dismas.domain.User;

/**
 * Created by Dmitry on 29.01.2019.
 */
public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author !=null ? author.getUsername() : "<none>";
    }
}
