package org.ac.cst8277.ahmed.basit.domain.dao;

import lombok.Data;
import org.ac.cst8277.ahmed.basit.util.BaseMessage;

@Data
public class PublisherDAO extends BaseMessage {
    private long id;
    private long publisherId;
    private String topic;
    private boolean isActive;
}
