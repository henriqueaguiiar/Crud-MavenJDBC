package util;

import exceptions.jdbc.DbExceprion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author Henrique Aguiar Pacheco
 * @version 1.0.0
 * @since 2025
 * Classe responsavel por carregar as propriedades do postgresql e realizar uma conexão com o banco de dados.
 */
public class DB {

    private static Connection conn = null;

    /**
     *
     * @return realiza a conexão com o banco de dados instanciando um objeto do tipo Connection.
     */
    public static Connection getConnection(){
        if(conn == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e){
                throw new DbExceprion(e.getMessage());
            }
        }

        System.out.println("Connection: " + conn);
        return conn;
    }


    public static void closeConnection(){
        try{
            if(conn != null){
                conn.close();
                System.out.println("Conexão encerrada!");
            }
        }
        catch (SQLException e){
            throw new DbExceprion(e.getMessage());
        }

    }

    /**
     *
     * @return metodo responsavel por carregar e retornar as propriedades do banco de dados em um objeto Properties.
     */
    private static Properties loadProperties(){

        try(FileInputStream fs = new FileInputStream("db.properties")){

            Properties props = new Properties();

            props.load(fs);
            return props;
        }
        catch (IOException e){
            throw new DbExceprion(e.getMessage());
        }
    }
}
