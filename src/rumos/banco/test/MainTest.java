package rumos.banco.test;

import java.util.List;

import rumos.banco.accounts.exceptions.AccountConflictException;
import rumos.banco.accounts.exceptions.AccountNotFoundException;
import rumos.banco.accounts.model.Account;
import rumos.banco.accounts.repository.IAccountRepository;
import rumos.banco.accounts.repository.InMemAccountRepository;
import rumos.banco.accounts.service.AccountService;
import rumos.banco.accounts.service.IAccountService;
import rumos.banco.cards.repository.ICardRepository;
import rumos.banco.cards.repository.InMemCardRepositoryImpl;
import rumos.banco.customers.repository.ICustomerRepository;
import rumos.banco.customers.repository.InMemCustomerRepositoryImpl;
import rumos.banco.transaction.repositoy.ITransactionRepository;
import rumos.banco.transaction.repositoy.InMemTransactionRepository;

public class MainTest {

	public static void main(String[] args) throws AccountConflictException, AccountNotFoundException {
		List<Integer> secondaryOwnersId = List.of(1,2,3);
		ICustomerRepository customerRepository = new InMemCustomerRepositoryImpl();
		IAccountRepository accountRepository = new InMemAccountRepository();
		ICardRepository cardRepository = new InMemCardRepositoryImpl();
		ITransactionRepository transactionRepository = new InMemTransactionRepository();
		
		IAccountService accountService = new AccountService(accountRepository);
		
		
//		accountService.createAccount(1);
//		accountService.createAccount(2);
//		accountService.createAccount(3);
//		accountService.createAccount(4);
//		accountService.deleteAccount(4);
//		accountService.deleteAccount(3);
//		accountService.deleteAccount(2);
//		accountService.deleteAccount(1);
		
		
		
		Account account1 = new Account.Builder()
		.withId(1)
		.withCustomerId(1)
		.withAccountNumber("123_1")
		.withBalance(2.0)
		.withPasswordAccount("1234")
		.withSecondaryOwnersId(secondaryOwnersId)
		.build();
		
		Account account2 = new Account.Builder()
		.withId(1)
		.withCustomerId(1)
		.withAccountNumber("123_2")
		.withBalance(2.0)
		.withPasswordAccount("1234")
		.withSecondaryOwnersId(secondaryOwnersId)
		.build();
		
		Account account3 = new Account.Builder()
		.withId(1)
		.withCustomerId(1)
		.withAccountNumber("123_3")
		.withBalance(2.0)
		.withPasswordAccount("1234")
		.withSecondaryOwnersId(List.of(4))
		.build();
		
		Account account4 = new Account.Builder()
		.withId(1)
		.withCustomerId(4)
		.withAccountNumber("123_4")
		.withBalance(2.0)
		.withPasswordAccount("1234")
		.withSecondaryOwnersId(secondaryOwnersId)
		.build();
//		
//		
		accountRepository.create(account1);
		accountRepository.create(account2);
		accountRepository.create(account3);
		accountRepository.create(account4);
		

//		accountRepository.getAll().stream().forEach(account -> System.out.println(account));
//		System.out.println(accountService.getAccount(5));
//		accountService.findAllAccountsByCustomerId(1).stream().forEach(System.out::println);
//		accountRepository.findByAllCustomerId(1).forEach(System.out::println);
//		System.out.println(accountService.findAccountByHolderCustomerId(5));
//		accountService.findAccountsBySecondaryCustomerId(4).stream().forEach(System.out::println);
		accountService.findAllAccountsByCustomerId(4).stream().forEach(account -> System.out.println(account));


		
	}

}
//Customer customer1 = new Customer.Builder()
//.withId(1)
//.withName("name")
//.withEmail("email")
//.withTaxId("0123456_1")
//.withBirthday(LocalDate.of(1900, 03, 02))
//.build();
//
//Customer customer2 = new Customer.Builder()
//.withId(2)
//.withName("name2")
//.withEmail("email2")
//.withTaxId("0123456_2")
//.withBirthday(LocalDate.of(1900, 03, 02))
//.build();
//
//Customer customer3 = new Customer.Builder()
//.withId(3)
//.withName("name3")
//.withEmail("email3")
//.withTaxId("0123456_3")
//.withBirthday(LocalDate.of(1900, 03, 02))
//.build();
//
//Customer customer4 = new Customer.Builder()
//.withId(4)
//.withName("name4")
//.withEmail("email4")
//.withTaxId("0123456_4")
//.withBirthday(LocalDate.of(1900, 03, 02))
//.build();
//
//Customer customer5 = new Customer.Builder()
//.withId(5)
//.withName("name5")
//.withEmail("email5")
//.withTaxId("0123456_5")
//.withBirthday(LocalDate.of(1900, 03, 02))
//.build();
//
//Customer customer6 = new Customer.Builder()
//.withId(1)
//.withName("name6")
//.withEmail("email6")
//.withTaxId("0123456_6")
//.withBirthday(LocalDate.of(1900, 03, 02))
//.build();
//
//Customer customer7 = new Customer.Builder()
//.withId(1)
//.withName("name7")
//.withEmail("email7")
//.withTaxId("0123456_6")
//.withBirthday(LocalDate.of(1900, 03, 02))
//.build();
//
//System.out.println(customer1.toString());
//System.out.println(customer2.toString());
//System.out.println(customer3.toString());
//
//customerRepository.create(customer1);
//customerRepository.create(customer2);
//customerRepository.create(customer3);
//customerRepository.create(customer4);
//customerRepository.create(customer5);
//customerRepository.create(customer7);
//
//customerRepository.deleteById(1);
//customerRepository.deleteById(3);
//customerRepository.deleteById(5);
//customerRepository.deleteById(6);
//
//customerRepository.update(customer6);
//
//System.out.println(customerRepository.getById(2).get());
//System.out.println(customerRepository.getByTaxId("0123456_6").get());
//
//
//for (Customer customers : customerRepository.getAll()) {
//System.out.println(customers);
//}


//Account account1 = new Account.Builder()
//.withId(1)
//.withCustomerId(1)
//.withAccountNumber("123_1")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(secondaryOwnersId)
//.build();
//
//Account account2 = new Account.Builder()
//.withId(1)
//.withCustomerId(2)
//.withAccountNumber("123_2")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(secondaryOwnersId)
//.build();
//
//Account account3 = new Account.Builder()
//.withId(1)
//.withCustomerId(3)
//.withAccountNumber("123_3")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(List.of(4))
//.build();
//
//Account account4 = new Account.Builder()
//.withId(1)
//.withCustomerId(4)
//.withAccountNumber("123_4")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(secondaryOwnersId)
//.build();

//Account account5 = new Account.Builder()
//.withId(1)
//.withCustomerId(5)
//.withAccountNumber("123_5")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(secondaryOwnersId)
//.build();
//
//Account account6 = new Account.Builder()
//.withId(1)
//.withCustomerId(6)
//.withAccountNumber("123_6")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(secondaryOwnersId)
//.build();
//
//Account account7 = new Account.Builder()
//.withId(1)
//.withCustomerId(7)
//.withAccountNumber("123_7")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(secondaryOwnersId)
//.build();


//System.out.println(account1.toString());

//accountRepository.create(account1);
//accountRepository.create(account2);
//accountRepository.create(account3);
//accountRepository.create(account4);
//accountRepository.create(account5);
//accountRepository.create(account6);
//accountRepository.create(account7);

//accountRepository.deleteById(1);
//accountRepository.deleteById(3);
//accountRepository.deleteById(7);
//

//Account account8 = new Account.Builder()
//.withId(2)
//.withCustomerId(2)
//.withAccountNumber("123_8")
//.withBalance(2.0)
//.withPasswordAccount("1234")
//.withSecondaryOwnersId(secondaryOwnersId)
//.build();

//accountRepository.update(account1);
//accountRepository.update(account8);
//accountRepository.update(account4);

//System.out.println(accountRepository.getById(4).get());
//System.out.println(accountRepository.findByHolderCustomerId(2).get());
//System.out.println(accountRepository.findBySecondaryCustomerId(3));
//accountRepository.findByAllCustomerId(3).forEach(System.out::println);


//accountRepository.getAll().stream().forEach(System.out::println);



//DebitCard debitCard1 = new DebitCard.Builder()
//   .withId(1)
//   .withCustomerId(1)
//   .withAccountId(1)
//   .withCardNumber("123_1")
//   .withPin("1234")
//   .isUsed(false)
//   .build();
//
//DebitCard debitCard2 = new DebitCard.Builder()
//.withId(2)
//.withCustomerId(2)
//.withAccountId(1)
//.withCardNumber("123_2")
//.withPin("1234")
//.isUsed(false)
//.build();
//
//DebitCard debitCard3 = new DebitCard.Builder()
//.withId(3)
//.withCustomerId(1)
//.withAccountId(1)
//.withCardNumber("123_3")
//.withPin("1234")
//.isUsed(false)
//.build();
//
//DebitCard debitCard4 = new DebitCard.Builder()
//.withId(2)
//.withCustomerId(1)
//.withAccountId(1)
//.withCardNumber("123_4")
//.withPin("1234")
//.isUsed(false)
//.build();
//
//CreditCard creditCard1 = new CreditCard.Builder()
//	   .withId(1)
//	   .withCustomerId(1)
//	   .withAccountId(1)
//	   .withCardNumber("1_345")
//	   .withPin("1234")
//	   .withCashAdvance(249)
//	   .isUsed(false)
//	   .build();
//
//CreditCard creditCard2 = new CreditCard.Builder()
//.withId(1)
//.withCustomerId(2)
//.withAccountId(2)
//.withCardNumber("2_345")
//.withPin("1234")
//.withCashAdvance(249)
//.isUsed(false)
//.build();
//
//CreditCard creditCard3 = new CreditCard.Builder()
//.withId(1)
//.withCustomerId(1)
//.withAccountId(1)
//.withCardNumber("3_345")
//.withPin("1234")
//.withCashAdvance(249)
//.isUsed(false)
//.build();
//
//CreditCard creditCard4 = new CreditCard.Builder()
//.withId(3)
//.withCustomerId(2)
//.withAccountId(1)
//.withCardNumber("4_345")
//.withPin("1234")
//.withCashAdvance(249)
//.isUsed(false)
//.build();
//
//cardRepository.create(debitCard1);
//cardRepository.create(debitCard2);
//cardRepository.create(debitCard3);
//
//cardRepository.create(creditCard1);
//cardRepository.create(creditCard2);
//cardRepository.create(creditCard3);

//cardRepository.deleteById(2);
//cardRepository.deleteById(5);
//cardRepository.update(debitCard4);
//cardRepository.update(creditCard4);

//cardRepository.getAll().stream().forEach(System.out::println);

//System.out.println(cardRepository.getById(1));
//System.out.println(cardRepository.getById(4));

//cardRepository.findByAccountIdAndCustomerId(1, 1).forEach(card -> System.out.println(card));

//System.out.println(cardRepository.findByCardNumber("2_345"));
//System.out.println(cardRepository.findByCardNumber("123_3"));

//cardRepository.findByCustomerId(1).forEach(System.out::println);
//
//System.out.println(cardRepository.getDebitCardByCustomerId(1));
//
//System.out.println(cardRepository.getCreditCardByCustomerId(5));

//Transaction transaction1 = new Transaction.Builder()
//.withId(1)
//.withAccountId(1)
//.withCardId(1)
//.withAmount("1")
//.withTimeStamp(LocalDateTime.now())
//.build();
//
//Transaction transaction2 = new Transaction.Builder()
//.withAccountId(1)
//.withCardId(1)
//.withAmount("2")
//.withTimeStamp(LocalDateTime.now())
//.build();
//
//Transaction transaction3 = new Transaction.Builder()
//.withAccountId(3)
//.withCardId(3)
//.withAmount("1")
//.withTimeStamp(LocalDateTime.now())
//.build();
//
//Transaction transaction4 = new Transaction.Builder()
//.withId(1)
//.withAccountId(4)
//.withCardId(4)
//.withAmount("4")
//.withTimeStamp(LocalDateTime.now())
//.build();
//
//transactionRepository.create(transaction1);
//transactionRepository.create(transaction2);
//transactionRepository.create(transaction3);

//transactionRepository.deleteById(4);
//transactionRepository.update(transaction4);
//
//transactionRepository.getAll().forEach(transaction -> System.out.println(transaction));

//System.out.println(transactionRepository.getById(2).get());


//System.out.println(transactionRepository.findByCardId(2));
//System.out.println(transactionRepository.findByAccountId(3));