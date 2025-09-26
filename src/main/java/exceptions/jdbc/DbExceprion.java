package exceptions.jdbc;

public class DbExceprion extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DbExceprion(String message) {
        super(message);
    }
}
