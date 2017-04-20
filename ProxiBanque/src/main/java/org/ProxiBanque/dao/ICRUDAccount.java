package org.ProxiBanque.dao;

import org.ProxiBanque.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDAccount extends JpaRepository<BankAccount, Long> {

}
