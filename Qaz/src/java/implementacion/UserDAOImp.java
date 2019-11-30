/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacion;

import configuracion.DBHelper;
import entidades.User;
import interfaces.IUserDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miste
 */
public class UserDAOImp implements IUserDAO  {

    private User usuario;
    private String error;
    private DBHelper db;
    
    public UserDAOImp(){
        this.usuario = new User();
        db = new DBHelper();
    }
    
    @Override
    public User login(User usuario) {
        try {
            String query = "SELECT * FROM user WHERE ";
                   query += " username LIKE '"+usuario.getUsername()+ "' ";
                   query += " AND password LIKE AES_ENCRYPT ('"+usuario.getPassword()+ "','qazstore') ";
                   
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                if(resultado.next()){
                    this.usuario.setId(resultado.getInt("id"));
                    this.usuario.setFullname(resultado.getString("fullname"));
                    this.usuario.setEmail(resultado.getString("email"));
                    this.usuario.setUsername(resultado.getString("username"));
                    this.usuario.setPassword("");
                    
                    return this.usuario;
                }
            }else{
                error = "We can not connect to server";
                
            }
            if(this.usuario.getFullname() == null || this.usuario.getFullname().isEmpty()){
               error = "Username or password are not valid"; 
            }
        } catch (Exception e) {
            System.out.println("Exception "+e.getMessage());
            error = "We can not connect to server";
        }finally{
            db.disconnect();
        }
        return null;
    }

    @Override
    public boolean crear(User usuario) {
        
        try {
            String query ="INSERT INTO user (fullname,email,username,password) ";
                   query += " VALUES ('"+usuario.getFullname()+"','"
                           +usuario.getEmail()+"','"+usuario.getUsername()+"',"
                           + " AES_ENCRYPT('"+usuario.getPassword()+"','qazstore') )";
                   
            if(db.connect()){
                return (boolean) db.execute(query, true);
            }
        } catch (Exception e) {
        }finally{
            db.disconnect();
        }
        return false;
    }

    @Override
    public boolean editar(User usuario) {
            
        try {
            String query ="UPDATE user SET ";
                   query += " fullname='"+usuario.getFullname()+"', email ='"
                           +usuario.getEmail()+"',username='"+usuario.getUsername()+"',"
                           + "password = AES_ENCRYPT('"+usuario.getPassword()+"','qazstore') ";
                   query += " WHERE id = "+usuario.getId();
       if(db.connect()){
           return (boolean) db.execute(query, true);
       }
        } catch (Exception e) {
        }finally{
            db.disconnect();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
            
        try {
            String query = "DELETE FROM user WHERE id="+id;
            
            if(db.connect()){
                return (boolean) db.execute(query, true);
            }
        } catch (Exception e) {
        }finally{
            db.disconnect();
        }
        return false;
    }

    @Override
    public List<User> obtenerTodos() {
            List<User> usuarios = new ArrayList();
            
            try {
            String query = "SELECT * FROM user ";
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                while(resultado.next()){
                    this.usuario.setId(resultado.getInt("id"));
                    this.usuario.setFullname(resultado.getString("fullname"));
                    this.usuario.setEmail(resultado.getString("email"));
                    this.usuario.setUsername(resultado.getString("username"));
                    this.usuario.setPassword("");
                    
                    usuarios.add(this.usuario);
                }
            }else{
                error = "We can not connect to server";
                
            }
            if(this.usuario.getFullname() == null || this.usuario.getFullname().isEmpty()){
                error ="Username or password are not valid";
            }
        } catch (Exception e) {
                System.out.println("Exception "+e.getMessage());
                error = "We can not connect to server";
        }finally{
                db.disconnect();
            }
                return usuarios;
    }

    @Override
    public User obtenerId(int id) {

        try {
            String query = "SELECT * FROM user WHERE id ="+id;
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                if(resultado.next()){
                    this.usuario.setId(resultado.getInt("id"));
                    this.usuario.setFullname(resultado.getString("fullname"));
                    this.usuario.setEmail(resultado.getString("email"));
                    this.usuario.setUsername(resultado.getString("username"));
                    this.usuario.setPassword("");
                    
                    return this.usuario;
                }
            }else{
                error = "We can not connect to server";
            }
            if(this.usuario.getFullname() == null || this.usuario.getFullname().isEmpty()){
                error= "Username or password are not valid";
            }
        } catch (Exception e) {
            System.out.println("Exception "+e.getMessage());
            error = "We can not connect to server";
            
        }finally{
            db.disconnect();
        }
return null;
    }

    public String getError() {
        return error;
    }
    
}
