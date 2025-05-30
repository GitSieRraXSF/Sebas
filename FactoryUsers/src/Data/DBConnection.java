package Data;

import java.sql.Connection;

public interface DBConnection {
	
    Connection getConnection();
    String getConnectionString();
}