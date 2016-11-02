package junit;

import model.datastore.mysql.DBConnection;

import static org.junit.Assert.assertNotNull;

public class Test {
	DBConnection con = new DBConnection();
	@org.junit.Test
	public void test() {
		assertNotNull(con.getConnection());
	}

}
