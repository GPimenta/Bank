package rumos.banco.repository;

import java.util.Optional;

import rumos.banco.model.Account;

public interface IAccountRepository {

	Optional<Account> getById(Integer id);
}
