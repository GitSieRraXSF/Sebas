package Data;

public class SQLiteConnectionFactory implements DBConnectionFactory{
	@Override
    public DBConnection createUsuarioConnection() {
        return new UsuarioConnectionSQLite();
    }
}
