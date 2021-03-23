package org.ac.cst8277.ahmed.basit.domain.dto;

import lombok.Data;

@Data
public class PublisherDTO {
    private long publisherId;
    private String topic;
    private boolean isActive;
}
