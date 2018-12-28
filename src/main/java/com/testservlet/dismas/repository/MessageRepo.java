package com.testservlet.dismas.repository;

import com.testservlet.dismas.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Dmitry on 14.11.2018.
 */
public interface MessageRepo extends CrudRepository <Message, Long> {

    List<Message> findByTag(String tag);

}
