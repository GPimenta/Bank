package rumos.banco.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import rumos.banco.accounts.model.Account;
import rumos.banco.accounts.repository.IAccountRepository;
import rumos.banco.accounts.repository.InMemAccountRepository;
import rumos.banco.cards.model.CreditCard;
import rumos.banco.cards.model.DebitCard;
import rumos.banco.cards.repository.ICardRepository;
import rumos.banco.cards.repository.InMemCardRepositoryImpl;
import rumos.banco.common.repository.IRepository;
import rumos.banco.customers.model.Customer;
import rumos.banco.customers.repository.ICustomerRepository;
import rumos.banco.customers.repository.InMemCustomerRepositoryImpl;

public class MainTest {

	public static void main(String[] args) {
		List<Integer> secondaryOwnersId = List.of(1,2,3);
		ICustomerRepository customerRepository = new InMemCustomerRepositoryImpl();
		IAccountRepository accountRepository = new InMemAccountRepository();
		ICardRepository cardRepository = new InMemCardRepositoryImpl();
		
		

//		Customer customer1 = new Customer.Builder()
//										.withId(1)
//										.withName("name")
//										.withEmail("email")
//										.withTaxId("0123456_1")
//										.withBirthday(LocalDate.of(1900, 03, 02))
//										.build();
//		
//		Customer customer2 = new Customer.Builder()
//				.withId(2)
//				.withName("name2")
//				.withEmail("email2")
//				.withTaxId("0123456_2")
//				.withBirthday(LocalDate.of(1900, 03, 02))
//				.build();
//		
//		Customer customer3 = new Customer.Builder()
//				.withId(3)
//				.withName("name3")
//				.withEmail("email3")
//				.withTaxId("0123456_3")
//				.withBirthday(LocalDate.of(1900, 03, 02))
//				.build();
//		
//		Customer customer4 = new Customer.Builder()
//				.withId(4)
//				.withName("name4")
//				.withEmail("email4")
//				.withTaxId("0123456_4")
//				.withBirthday(LocalDate.of(1900, 03, 02))
//				.build();
//				
//		Customer customer5 = new Customer.Builder()
//				.withId(5)
//				.withName("name5")
//				.withEmail("email5")
//				.withTaxId("0123456_5")
//				.withBirthday(LocalDate.of(1900, 03, 02))
//				.build();
//		
//		Customer customer6 = new Customer.Builder()
//				.withId(1)
//				.withName("name6")
//				.withEmail("email6")
//				.withTaxId("0123456_6")
//				.withBirthday(LocalDate.of(1900, 03, 02))
//				.build();
//		
//		Customer customer7 = new Customer.Builder()
//				.withId(1)
//				.withName("name7")
//				.withEmail("email7")
//				.withTaxId("0123456_6")
//				.withBirthday(LocalDate.of(1900, 03, 02))
//				.build();
//		
//		System.out.println(customer1.toString());
//		System.out.println(customer2.toString());
//		System.out.println(customer3.toString());
//		
//		customerRepository.create(customer1);
//		customerRepository.create(customer2);
//		customerRepository.create(customer3);
//		customerRepository.create(customer4);
//		customerRepository.create(customer5);
//		customerRepository.create(customer7);
//		
//		customerRepository.deleteById(1);
//		customerRepository.deleteById(3);
//		customerRepository.deleteById(5);
//		customerRepository.deleteById(6);
//		
//		customerRepository.update(customer6);
//		
//		System.out.println(customerRepository.getById(2).get());
//		System.out.println(customerRepository.getByTaxId("0123456_6").get());
//		
//		
//		for (Customer customers : customerRepository.getAll()) {
//			System.out.println(customers);
//		}
		
		
//		Account account1 = new Account.Builder()
//									 .withId(1)
//									 .withCustomerId(1)
//									 .withAccountNumber("123_1")
//									 .withBalance(2.0)
//									 .withPasswordAccount("1234")
//									 .withSecondaryOwnersId(secondaryOwnersId)
//									 .build();
//		
//		Account account2 = new Account.Builder()
//				 .withId(1)
//				 .withCustomerId(2)
//				 .withAccountNumber("123_2")
//				 .withBalance(2.0)
//				 .withPasswordAccount("1234")
//				 .withSecondaryOwnersId(secondaryOwnersId)
//				 .build();
//		
//		Account account3 = new Account.Builder()
//				 .withId(1)
//				 .withCustomerId(3)
//				 .withAccountNumber("123_3")
//				 .withBalance(2.0)
//				 .withPasswordAccount("1234")
//				 .withSecondaryOwnersId(List.of(4))
//				 .build();
//		
//		Account account4 = new Account.Builder()
//				 .withId(1)
//				 .withCustomerId(4)
//				 .withAccountNumber("123_4")
//				 .withBalance(2.0)
//				 .withPasswordAccount("1234")
//				 .withSecondaryOwnersId(secondaryOwnersId)
//				 .build();
		
//		Account account5 = new Account.Builder()
//				 .withId(1)
//				 .withCustomerId(5)
//				 .withAccountNumber("123_5")
//				 .withBalance(2.0)
//				 .withPasswordAccount("1234")
//				 .withSecondaryOwnersId(secondaryOwnersId)
//				 .build();
//		
//		Account account6 = new Account.Builder()
//				 .withId(1)
//				 .withCustomerId(6)
//				 .withAccountNumber("123_6")
//				 .withBalance(2.0)
//				 .withPasswordAccount("1234")
//				 .withSecondaryOwnersId(secondaryOwnersId)
//				 .build();
//		
//		Account account7 = new Account.Builder()
//				 .withId(1)
//				 .withCustomerId(7)
//				 .withAccountNumber("123_7")
//				 .withBalance(2.0)
//				 .withPasswordAccount("1234")
//				 .withSecondaryOwnersId(secondaryOwnersId)
//				 .build();
		
		
//		System.out.println(account1.toString());
		
//		accountRepository.create(account1);
//		accountRepository.create(account2);
//		accountRepository.create(account3);
//		accountRepository.create(account4);
//		accountRepository.create(account5);
//		accountRepository.create(account6);
//		accountRepository.create(account7);
		
//		accountRepository.deleteById(1);
//		accountRepository.deleteById(3);
//		accountRepository.deleteById(7);
//		
		
//		Account account8 = new Account.Builder()
//				 .withId(2)
//				 .withCustomerId(2)
//				 .withAccountNumber("123_8")
//				 .withBalance(2.0)
//				 .withPasswordAccount("1234")
//				 .withSecondaryOwnersId(secondaryOwnersId)
//				 .build();
		
//		accountRepository.update(account1);
//		accountRepository.update(account8);
//		accountRepository.update(account4);
		
//		System.out.println(accountRepository.getById(4).get());
//		System.out.println(accountRepository.findByHolderCustomerId(2).get());
//		System.out.println(accountRepository.findBySecondaryCustomerId(3));
//		accountRepository.findByAllCustomerId(3).forEach(System.out::println);
		
		
//		accountRepository.getAll().stream().forEach(System.out::println);
		
		
		
		DebitCard debitCard1 = new DebitCard.Builder()
										   .withId(1)
										   .withCustomerId(1)
										   .withAccountId(1)
										   .withCardNumber("123_1")
										   .withPin("1234")
										   .isUsed(false)
										   .build();
		
		DebitCard debitCard2 = new DebitCard.Builder()
				   .withId(2)
				   .withCustomerId(1)
				   .withAccountId(1)
				   .withCardNumber("123_2")
				   .withPin("1234")
				   .isUsed(false)
				   .build();
		
		DebitCard debitCard3 = new DebitCard.Builder()
				   .withId(3)
				   .withCustomerId(1)
				   .withAccountId(1)
				   .withCardNumber("123_3")
				   .withPin("1234")
				   .isUsed(false)
				   .build();

		
//		CreditCard creditCard1 = new CreditCard.Builder()
//											   .withId(1)
//											   .withCustomerId(1)
//											   .withAccountId(1)
//											   .withCardNumber("12345")
//											   .withPin("1234")
//											   .withCashAdvance(249)
//											   .isUsed(false)
//											   .build();
//		
//		CreditCard creditCard2 = new CreditCard.Builder()
//				   .withId(1)
//				   .withCustomerId(1)
//				   .withAccountId(1)
//				   .withCardNumber("12345")
//				   .withPin("1234")
//				   .withCashAdvance(249)
//				   .isUsed(false)
//				   .build();
//		
//		CreditCard creditCard3 = new CreditCard.Builder()
//				   .withId(1)
//				   .withCustomerId(1)
//				   .withAccountId(1)
//				   .withCardNumber("12345")
//				   .withPin("1234")
//				   .withCashAdvance(249)
//				   .isUsed(false)
//				   .build();
		
		cardRepository.create(debitCard1);
		cardRepository.create(debitCard2);
		cardRepository.create(debitCard3);
		
		cardRepository.getAll().stream().forEach(System.out::println);		
		
		
//		cardRepository.create(creditCard1);
//		cardRepository.create(creditCard2);
//		cardRepository.create(creditCard3);

	}

}
