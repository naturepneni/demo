package com.naturep.rewards.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.naturep.rewards.demo.domain.CustomerTransaction;

@Repository
public interface CustomerTransactionRepo extends CrudRepository<CustomerTransaction, Long> {

    List<CustomerTransaction> findByCustomerId(String customerId);

}
