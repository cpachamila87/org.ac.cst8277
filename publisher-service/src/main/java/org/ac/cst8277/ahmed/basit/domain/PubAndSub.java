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
@Table(name = "publisher_and_subscriber")
public class PubAndSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "publisher_id")
    private long publisherId;

    @Column(name = "subscriber_id")
    private long subscriberId;

    public PubAndSub(long publisherId, long subscriberId) {
        this.publisherId = publisherId;
        this.subscriberId = subscriberId;
    }
}
