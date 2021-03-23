package org.ac.cst8277.ahmed.basit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "publisher")
public class Publisher extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "publisher_id")
    private long publisherId;

    private String topic;

    @Column(name = "is_active")
    private boolean isActive;

    public Publisher(long publisherId, String topic, boolean isActive) {
        this.publisherId = publisherId;
        this.topic = topic;
        this.isActive = isActive;
    }
}
