package rumos.banco.customers.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.customers.model.old.OldCustomer;

public class OldDBCustomerRepositoryImpl implements OldICustomerRepository {

	private final String username;
	private final String password;
	private final String url;

	public OldDBCustomerRepositoryImpl(String url, String username, String password) {
		this.username = username;
		this.password = password;
		this.url = url;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<OldCustomer> getAll() {
		ArrayList<OldCustomer> result = new ArrayList<>();
		
		String sql = "SELECT * FROM customer";
		

		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(sql);) {


			boolean isQueary = stmt.execute();

			if (isQueary) {
				try (ResultSet rs = stmt.getResultSet()) {
					while (rs.next()) {
						OldCustomer customer = new OldCustomer();
						customer.setId(rs.getInt(1));
						customer.setTaxId(rs.getString("taxId"));
						customer.setName(rs.getString("name"));	
						customer.setEmail(rs.getString("email"));
						customer.setDateOfBirth(LocalDate.parse(rs.getString("birthday")));
						result.add(customer);
					}

				}

			} else {
				int affectedRows = stmt.getUpdateCount();
				System.out.println(affectedRows);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro nao consigo ligar à BD " + e.getMessage());
		}

		return result;
	}

	@Override
	public void create(OldCustomer customer) {

		String preQuery = "INSERT INTO customer(name, taxId, email, birthday)\r\n" + "VALUES (?, ?, ?, ?);";

		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(preQuery);) {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getTaxId());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getDateOfBirth().toString());

			stmt.execute();

//			if(affectRows !=1 ) {
//				throw new RuntimeException("Failed to insert User");
//			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Optional<OldCustomer> getById(Integer id) {

		String sql = "SELECT * FROM customer WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setInt(1, id);

			boolean isQueary = stmt.execute();

			if (isQueary) {
				try (ResultSet rs = stmt.getResultSet()) {
					while (rs.next()) {
						OldCustomer customer = new OldCustomer();
						customer.setId(rs.getInt(1));
						customer.setTaxId(rs.getString("taxId"));
						customer.setName(rs.getString("name"));	
						customer.setEmail(rs.getString("email"));
						customer.setDateOfBirth(LocalDate.parse(rs.getString("birthday")));
						return Optional.of(customer);
					}

				}

			} else {
				int affectedRows = stmt.getUpdateCount();
				System.out.println(affectedRows);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro nao consigo ligar à BD " + e.getMessage());
		}

		return Optional.empty();
	}

	@Override
	public void update(OldCustomer customer) {
		// TODO Auto-generated method stub

	}

}
