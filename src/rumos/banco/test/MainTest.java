package rumos.banco.test;

import java.time.LocalDate;
import java.util.List;

import rumos.banco.accounts.model.Account;
import rumos.banco.cards.model.CreditCard;
import rumos.banco.cards.model.DebitCard;
import rumos.banco.customers.model.Customer;
import rumos.banco.customers.repository.ICustomerRepository;
import rumos.banco.customers.repository.InMemCustomerRepositoryImpl;

public class MainTest {

	public static void main(String[] args) {
		List<Integer> secondaryOwnersId = List.of(1,2,3);
		ICustomerRepository customerRepository = new InMemCustomerRepositoryImpl();
		
		customerRepository.
		
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
		
		DebitCard debitCard = new DebitCard.Builder()
										   .withId(1)
										   .withCustomerId(1)
										   .withAccountId(1)
										   .withCardNumber("12345")
										   .withPin("1234")
										   .isUsed(false)
										   .build();
		
		System.out.println(debitCard.toString());
		
		CreditCard creditCard = new CreditCard.Builder()
											   .withId(1)
											   .withCustomerId(1)
											   .withAccountId(1)
											   .withCardNumber("12345")
											   .withPin("1234")
											   .withCashAdvance(249)
											   .isUsed(false)
											   .build();
		
		System.out.println(creditCard.toString());
		
	}

}
