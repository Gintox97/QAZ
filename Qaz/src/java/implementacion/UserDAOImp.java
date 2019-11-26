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
public class UserDAOImp implements IUserDAO {
    private User user;
    private String error;
    private DBHelper db;
    
    public UserDAOImp(){
    this.user = new User();
    db = new DBHelper();
}

    @Override
    public User login(User user) {
        try {
            String query = "SELECT * FROM usuarios WHERE";
            query += "username LIKE '"+user.getNickname()+ "' ";
            query += " AND password LIKE AES_ENCRYPT('"+user.getPassword()+"','qazmovie')";
        
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                if(resultado.next()){
                    this.user.setId(resultado.getInt("id"));
                    this.user.setFullname(resultado.getString("fullname"));
                    this.user.setNickname(resultado.getString("nickname"));
                    this.user.setEmail(resultado.getString("email"));
                    this.user.setPassword("");
                    this.user.setImage("image");
                    this.user.setConntype("conttype");
                   
                    
                    
                    return this.user;
                }
            }else{
                error = "We can not connect to the server";
            }
            
            if(this.user.getFullname() == null || this.user.getFullname().isEmpty()){
                error = "Nickname or Password are not valid";
            }
        
        } catch (Exception e) {
            System.out.println("Exception "+e.getMessage());
            error = "We can not connect to the server";
        }finally{
        db.disconnect();
    }
    return null;
    }
    

    @Override
    public boolean crear(User user) {
 boolean resultado  = false;
        try {
            String query ="INSERT INTO usuarios (fullname,nickname,email,password,image,conttype)";
                   query += "VALUES ('"+user.getFullname()+"',"
                           +"'"+user.getNickname()+"',"
                           +"'"+user.getEmail()+"',"
                           +"AES_ENCRYPT('"+user.getPassword()+"','qazmovie')),"
                           +"'"+user.getImage()+"',"
                           +"'"+user.getConntype()+"'"
                           +")";
                   if(db.connect()){
                       return (boolean) db.execute(query, true);
                   }
        } catch (Exception e) {
            
        }finally{
            db.disconnect();
        }
return resultado;
    }

    @Override
    public boolean editar(User user) {

        try {
            String query="UPDATE usuarios SET";
            query += " fullname='"+user.getFullname()
                    +"', nickname = '"+user.getNickname()
                    +"',email='"+user.getEmail()+"',"
                    +"password = AES_ENCRYPT('"+user.getPassword()+"','qazmovie)"+"','"
                    +"',image ='"+user.getImage()+"', conttype='"+user.getConntype();
            query += "WHERE id = "+user.getId();
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
            String query = "DELETE FROM usuarios WHERE id="+id;
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
            String query = "SELECT * FROM usuarios";
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                while(resultado.next()){
                    this.user.setId(resultado.getInt("id"));
                    this.user.setFullname(resultado.getString("fullname"));
                    this.user.setNickname(resultado.getString("nickname"));
                    this.user.setEmail(resultado.getString("email"));
                    this.user.setPassword("");
                    this.user.setImage("image");
                    
                    
                    usuarios.add(this.user);
                }
            }else{
                error = "We can not connect to the server";
            }
            if(this.user.getFullname() == null || this.user.getFullname().isEmpty()){
                error = "Nickname or Password are not valid";
            }
        } catch (Exception e) {
       System.out.println("Exception "+e.getMessage());
            error = "We can not connect to the server";
        }finally{
        db.disconnect();
    }
    return usuarios;
    }

    @Override
    public User obtenerId(int id) {
     try {
            String query = "SELECT * FROM usuarios WHERE";
            query += "username LIKE '"+user.getNickname()+ "' ";
            query += " AND password LIKE AES_ENCRYPT('"+user.getPassword()+"','qazmovie')";
        
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                if(resultado.next()){
                    this.user.setId(resultado.getInt("id"));
                    this.user.setFullname(resultado.getString("fullname"));
                    this.user.setNickname(resultado.getString("nickname"));
                    this.user.setEmail(resultado.getString("email"));
                    this.user.setPassword("");
                    this.user.setImage("imagen");
                    this.user.setConntype("conttype");
                    
                    
                    return this.user;
                }
            }else{
                error = "We can not connect to the server";
            }
            
            if(this.user.getFullname() == null || this.user.getFullname().isEmpty()){
                error = "Nickname or Password are not valid";
            }
        
        } catch (Exception e) {
            System.out.println("Exception "+e.getMessage());
            error = "We can not connect to the server";
        }finally{
        db.disconnect();
    }
    return null;
    }
    public String getError(){
        return error;
    }
}
