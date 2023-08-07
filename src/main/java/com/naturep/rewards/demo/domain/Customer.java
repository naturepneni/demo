package com.naturep.rewards.demo.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "customer_id")
    private String customerId;

    @CreatedDate
    @Column(name = "created_date")
    protected LocalDate creationDate;

}
