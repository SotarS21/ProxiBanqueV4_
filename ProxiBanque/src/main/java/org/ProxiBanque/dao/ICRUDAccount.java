package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDAccount extends JpaRepository<BankAccount, Long> {

	public List<BankAccount> findAllByClient_Id(Long id);
}
