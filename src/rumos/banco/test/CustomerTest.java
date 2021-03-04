package rumos.banco.test;

import java.time.LocalDate;

import rumos.banco.customers.model.Customer;
import rumos.banco.customers.repository.DBCustomerRepositoryImpl;
import rumos.banco.customers.repository.ICustomerRepository;
import rumos.banco.customers.repository.InMemCustomerRepositoryImpl;
import rumos.banco.customers.service.CustomerService;

public class CustomerTest {
	
	public static void main(String[] args) {
		ICustomerRepository repository0 = new InMemCustomerRepositoryImpl();
		ICustomerRepository repository = new DBCustomerRepositoryImpl("jdbc:mysql://localhost:3306/banco?allowMultiQueries=true","bancoUser","banco");
		CustomerService customerService = new CustomerService(repository0);
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setDateOfBirth(LocalDate.now());
		customer1.setEmail("1");
		customer1.setName("1");
		customer1.setTaxId("1");
		Customer customer2 = new Customer();
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
