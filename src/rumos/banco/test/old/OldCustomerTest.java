package rumos.banco.test.old;

import java.time.LocalDate;

import rumos.banco.customers.model.old.OldCustomer;
import rumos.banco.customers.repository.old.OldDBCustomerRepositoryImpl;
import rumos.banco.customers.repository.old.OldICustomerRepository;
import rumos.banco.customers.repository.old.OldInMemCustomerRepositoryImpl;
import rumos.banco.customers.service.OldCustomerService;

public class OldCustomerTest {
	
	public static void main(String[] args) {
		OldICustomerRepository repository0 = new OldInMemCustomerRepositoryImpl();
		OldICustomerRepository repository = new OldDBCustomerRepositoryImpl("jdbc:mysql://localhost:3306/banco?allowMultiQueries=true","bancoUser","banco");
		OldCustomerService customerService = new OldCustomerService(repository0);
		OldCustomer customer1 = new OldCustomer();
		customer1.setId(1);
		customer1.setDateOfBirth(LocalDate.now());
		customer1.setEmail("1");
		customer1.setName("1");
		customer1.setTaxId("1");
		OldCustomer customer2 = new OldCustomer();
		customer2.setId(2);
		customer2.setDateOfBirth(LocalDate.now());
		customer2.setEmail("2");
		customer2.setName("2");
		customer2.setTaxId("2");
		
//		customerService.create(customer1);
//		customerService.create(customer2);
		
//		System.out.println(customerService.getCustomerById(2));
//		System.out.println(customerService.getCustomerById(3));
		
		System.out.println(repository0.getAll());
		
		
		
	}
}
