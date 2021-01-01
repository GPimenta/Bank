package rumos.banco.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Account;

public class AccountRepositoryImpl implements IAccountRepository{

	private static ArrayList<Account> accounts = new ArrayList<>();
	private static Integer id = 0;
	
	@Override
	public Optional<Account> getById(Integer id) {
		for(Account account: accounts) {
			if(account.getId().equals(id)) {
				return Optional.of(account);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<Account> getAll() {
		ArrayList<Account> result = new ArrayList<Account>();
		for(Account account : accounts) {
			result.add(account);
		}
		return result;
	}

	@Override
	public void create(Account account) {
		account.setId(++id);
		accounts.add(account);
		
	}

	@Override
	public void deleteById(Integer id) {
		for(Account account :accounts) {
			if(account.getId().equals(id)) {
				accounts.remove(account);
			}
		}
		
	}

	@Override
	public void update(Account account) {
		accounts.add(account);
		
	}

}
