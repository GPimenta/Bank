package rumos.banco.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import rumos.banco.accounts.model.Account;
import rumos.banco.customers.model.Customer;

public class MainTest {

	public static void main(String[] args) {
		List<Integer> secondaryOwnersId = List.of(1,2,3);
		// TODO Auto-generated method stub
		Customer customer = new Customer.Builder()
										.withId(1)
										.withName("name")
										.withEmail("email")
										.withTaxId("012345678")
										.withBirthday(LocalDate.of(1900, 03, 02))
										.build();
		
		System.out.println(customer.toString());
		
		Account account = new Account.Builder()
									 .withId(1)
									 .withCustomerId(1)
									 .withAccountNumber("12345")
									 .withBalance(2.0)
									 .withPasswordAccount("1234")
									 .withSecondaryOwnersId(secondaryOwnersId)
									 .build();
		
		
		System.out.println(account.toString());
		
	}

}
