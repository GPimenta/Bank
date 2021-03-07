package rumos.banco.accounts.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.accounts.model.old.OldAccount;

public class OldInMemAccountRepositoryImpl implements OldIAccountRepository {

	private static ArrayList<OldAccount> accounts = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public Optional<OldAccount> getById(Integer id) {
		for (OldAccount account : accounts) {
			if (account.getId().equals(id)) {
				return Optional.of(account);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<OldAccount> getAll() {
		ArrayList<OldAccount> result = new ArrayList<OldAccount>();
		for (OldAccount account : accounts) {
			result.add(account);
		}
		return result;
	}

	@Override
	public void create(OldAccount account) {
		account.setId(++id);
		account.setCustomerId(id);
		accounts.add(account);

	}

	@Override
	public void deleteById(Integer id) {
		for (OldAccount account : accounts) {
			if (account.getId().equals(id)) {
				accounts.remove(account);
			}
		}

	}
	
	@Override
	public void deleteByCustomerId(Integer customerId) {
		for (OldAccount account : accounts) {
			if (account.getCustomerId().equals(customerId)) {
				accounts.remove(account);
				return;
			}
		}

	}

	@Override
	public void update(OldAccount account) {
		accounts.add(account);

	}

}
