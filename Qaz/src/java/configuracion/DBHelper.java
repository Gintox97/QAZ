package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    private Connection connection;
    private String error;
    private String controller = "com.mysql.jdbc.Driver";
    private String database = "jdbc:mysql://localhost/qaz";
    private String user = "root";
    private String password = "Gintox@97";

    public DBHelper() {

    }

    public DBHelper(String controller, String database, String user, String password) {
        this.controller = controller;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public boolean connect() {
        try {
            /*Esta línea crea una asociación entre nuestra aplicación
             * y la clase Driver que está empaquetada en el  jar de conexión.*/
            Class.forName(this.controller);
            /*La conexón se obtiene con una cadena que usa
             *los parámetros que recibe el constructor*/
            setConnection(DriverManager.getConnection(this.database, this.user, this.password));
        } catch (ClassNotFoundException e) {//Sucede si no se encuentra el driver
            error = e.getMessage();
        } catch (SQLException e) {//Sucede si la conexión falla
            error = e.getMessage();
        }
        return true;
    }

    /**
     *
     * @param sql String with the query sql
     * @param data_manipule Boolean TRUE(INSERT-UPDATE-DELETE) FALSE(SELECT)
     * @return return an object if data_manipule is true return a boolean but if
     * data_manipule is false return an ResultSet Object and return false if had
     * errors. Don't forget close the ResultSet when finish
     */
    public Object execute(String sql, boolean data_manipule) {
        try {

            Statement sentencia = getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            if (data_manipule) {
                if (sentencia.executeUpdate(sql) > 0) {

                    sentencia.close();
                    return true;
                } else {
                    sentencia.close();
                    return false;
                }
            } else {

                ResultSet resultado = sentencia.executeQuery(sql);
                return resultado;
            }

        } catch (SQLException ex) {

            this.error = ex.getMessage();
            return false;

        }

    }

    public void disconnect() {
        try {
            getConnection().close();
        } catch (Exception ex) {
            this.error = ex.getMessage();
        }
    }

    public String getMensajeError() {
        return getError();
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the conexion
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param conexion the conexion to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
