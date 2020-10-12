package com.karakaya.bank.SimpleBankAPI.Repository;

import com.karakaya.bank.SimpleBankAPI.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
