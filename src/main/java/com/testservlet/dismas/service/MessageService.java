package com.testservlet.dismas.service;

import com.testservlet.dismas.domain.User;
import com.testservlet.dismas.domain.dto.MessageDto;
import com.testservlet.dismas.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Dmitry on 22.01.2019.
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepo repo;

    public Page<MessageDto> messageList (Pageable pageable, String filter, User user){
        if (filter!=null && !filter.isEmpty()){
            return repo.findByTag(filter, pageable, user);
        } else {
            return repo.findAll(pageable,user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User author, User user) {
        return  repo.findByUser(pageable, author, user);
    }
}
