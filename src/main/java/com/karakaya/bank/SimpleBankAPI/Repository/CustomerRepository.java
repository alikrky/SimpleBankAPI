package com.karakaya.bank.SimpleBankAPI.Repository;

import com.karakaya.bank.SimpleBankAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
