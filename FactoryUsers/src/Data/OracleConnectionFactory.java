package Data;

public class OracleConnectionFactory implements DBConnectionFactory{
	@Override
    public DBConnection createUsuarioConnection() {
        return new UsuarioConnectionOracle();
    }
}
