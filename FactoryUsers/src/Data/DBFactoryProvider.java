package Data;

public class DBFactoryProvider {
	private static DBConnectionFactory oracleFactory;
    private static DBConnectionFactory sqliteFactory;

    public static DBConnectionFactory getFactory(String dbType) {
        switch (dbType.toLowerCase()) {
            case "UsuarioOracle":
                if (oracleFactory == null) {
                    oracleFactory = new OracleConnectionFactory();
                }
                return oracleFactory;
            case "UsuarioSQLite":
                if (sqliteFactory == null) {
                    sqliteFactory = new SQLiteConnectionFactory();
                }
                return sqliteFactory;
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + dbType);
        }
    }
}
