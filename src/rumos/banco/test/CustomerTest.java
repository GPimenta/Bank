package rumos.banco.test;

import java.time.LocalDate;

import rumos.banco.customers.model.Customer;

public class CustomerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer customer = new Customer.Builder()
										.withId(1)
										.withName("name")
										.withEmail("email")
										.withTaxId("012345678910")
										.withBirthday(LocalDate.of(1900, 03, 02))
										.build();
		
		System.out.println(customer.toString());
	}

}
