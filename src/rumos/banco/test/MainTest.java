package rumos.banco.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
		
		
		
		// TODO Auto-generated method stub
		Customer customer1 = new Customer.Builder()
										.withId(1)
										.withName("name")
										.withEmail("email")
										.withTaxId("0123456_1")
										.withBirthday(LocalDate.of(1900, 03, 02))
										.build();
		
		Customer customer2 = new Customer.Builder()
				.withId(2)
				.withName("name2")
				.withEmail("email2")
				.withTaxId("0123456_2")
				.withBirthday(LocalDate.of(1900, 03, 02))
				.build();
		
		Customer customer3 = new Customer.Builder()
				.withId(3)
				.withName("name3")
				.withEmail("email3")
				.withTaxId("0123456_3")
				.withBirthday(LocalDate.of(1900, 03, 02))
				.build();
		
		Customer customer4 = new Customer.Builder()
				.withId(4)
				.withName("name4")
				.withEmail("email4")
				.withTaxId("0123456_4")
				.withBirthday(LocalDate.of(1900, 03, 02))
				.build();
				
		Customer customer5 = new Customer.Builder()
				.withId(5)
				.withName("name5")
				.withEmail("email5")
				.withTaxId("0123456_5")
				.withBirthday(LocalDate.of(1900, 03, 02))
				.build();
		
		Customer customer6 = new Customer.Builder()
				.withId(1)
				.withName("name6")
				.withEmail("email6")
				.withTaxId("0123456_6")
				.withBirthday(LocalDate.of(1900, 03, 02))
				.build();
		
//		System.out.println(customer1.toString());
//		System.out.println(customer2.toString());
//		System.out.println(customer3.toString());
		
		customerRepository.create(customer1);
		customerRepository.create(customer2);
		customerRepository.create(customer3);
		customerRepository.create(customer4);
		customerRepository.create(customer5);
		
//		customerRepository.deleteById(1);
//		customerRepository.deleteById(3);
//		customerRepository.deleteById(5);
//		customerRepository.deleteById(6);
		
		customerRepository.update(customer6);
		
		System.out.println(customerRepository.getById(2).get());
		System.out.println(customerRepository.getByTaxId("0123456_6").get());
		
		
		for (Customer customers : customerRepository.getAll()) {
			System.out.println(customers);
		}
		
		
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
