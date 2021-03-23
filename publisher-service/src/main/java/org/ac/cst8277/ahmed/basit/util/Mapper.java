package org.ac.cst8277.ahmed.basit.util;

import org.ac.cst8277.ahmed.basit.domain.Message;
import org.ac.cst8277.ahmed.basit.domain.Publisher;
import org.ac.cst8277.ahmed.basit.domain.dao.MessageDAO;
import org.ac.cst8277.ahmed.basit.domain.dao.PublisherDAO;
import org.ac.cst8277.ahmed.basit.domain.dto.MessageDTO;

public class Mapper {

    public static Message convertToMessage(MessageDTO messageDTO){
        Message message= new Message();
        message.setMessage(messageDTO.getMessage());
        message.setPublisherId(messageDTO.getPublisherId());
        return message;
    }

    public static MessageDAO convertToMessageDAO(Message message){
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.setId(message.getId());
        messageDAO.setMessage(message.getMessage());
        messageDAO.setPublisherId(message.getPublisherId());
        messageDAO.setPublished(message.isPublished());
        return messageDAO;
    }

    public static PublisherDAO convertToPublisherDAO(Publisher publisher){
        PublisherDAO publisherDAO = new PublisherDAO();
        publisherDAO.setId(publisher.getId());
        publisherDAO.setPublisherId(publisher.getPublisherId());
        publisherDAO.setTopic(publisher.getTopic());
        publisherDAO.setActive(publisher.isActive());
        return publisherDAO;
    }

//    public static PublisherDAO convertToUserDAO(Publisher publisherDAO, Publisher publisher){
//        publisherDAO.setMessage(publisher.getMessage());
//        if (publisher.isPublished()) {
//            publisherDAO.setPublished(publisher.isPublished());
//        }
//        publisherDAO.setPublisherId(publisher.getPublisherId());
//        publisherDAO.setSubscriberId(publisher.getSubscriberId());
//        return publisherDAO;
//    }

}
