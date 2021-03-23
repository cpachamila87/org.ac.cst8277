package org.ac.cst8277.ahmed.basit.domain.dao;

import lombok.Data;
import org.ac.cst8277.ahmed.basit.util.BaseMessage;

import java.util.List;

@Data
public class BulkMessageDAO extends BaseMessage {
    private long publisherId;
    private List<MessageDAO> messages;
}
