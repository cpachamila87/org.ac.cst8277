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
@Table(name = "subscriber")
public class Subscriber extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "subscriber_id")
    private long subscriberId;
    @Column(name = "publisher_id")
    private long publisherId;
    private boolean isSubscribed = false;

    public Subscriber(long subscriberId, long publisherId) {
        this.subscriberId = subscriberId;
        this.publisherId = publisherId;
    }
}
