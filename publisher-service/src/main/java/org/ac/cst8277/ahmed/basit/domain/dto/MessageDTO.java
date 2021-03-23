package org.ac.cst8277.ahmed.basit.domain.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private long id;
    private String message;
    private long publisherId;
}
