package org.ac.cst8277.ahmed.basit.domain.dao;

import lombok.Data;
import org.ac.cst8277.ahmed.basit.util.BaseMessage;

@Data
public class MessageDAO extends BaseMessage {
    private long id;
    private String message;
    private long publisherId;
    private boolean isPublished;
}
