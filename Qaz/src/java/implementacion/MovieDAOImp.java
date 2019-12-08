/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacion;

import configuracion.DBHelper;
import entidades.Game;
import entidades.Movie;
import interfaces.IMovieDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miste
 */
public class MovieDAOImp implements IMovieDAO{
private Movie movie = new Movie();
DBHelper db = new DBHelper();
String error;
    @Override
    public boolean crear(Movie movie) {
     boolean resultado = false;
     
        try {
            String query = "INSERT INTO movie(name,sinopsis,price,genre,image,conttype)"
                    + "VALUES("
                    +"'"+movie.getName()+"',"
                    +"'"+movie.getSinopsis()+"',"
                    +""+movie.getPrice()+","
                    +"'"+movie.getGenre()+"',"
                    +"'"+movie.getImage()+"',"
                    +"'"+movie.getConttype()+"'"
                    +")";
            
            if(db.connect()){
                resultado = (boolean) db.execute(query, true);
            }
        } catch (Exception e) {
        }finally{
            db.disconnect();
        }
        return resultado;
    }

    @Override
    public boolean editar(Movie t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> obtenerTodos() {
     List<Movie> movies = new ArrayList();
            
            try {
            String query = "SELECT * FROM movie";
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                while(resultado.next()){
                    this.movie.setId(resultado.getInt("id"));
                    this.movie.setName(resultado.getString("name"));
                    this.movie.setSinopsis(resultado.getString("sinopsis"));
                    this.movie.setPrice(resultado.getDouble("precio"));
                    this.movie.setGenre(resultado.getString("genre"));
                    this.movie.setImage(resultado.getString("imagen"));
                    
                    movies.add(this.movie);
                }
                
            }else{
                error = "We can not connect to server";
            }
            
        } catch (Exception e) {
                System.out.println("Exception: "+e.getMessage());
                error = "We can not connect to server";
        }finally{
                db.disconnect();
            }
            return movies;

    }

    @Override
    public Movie obtenerId(int id) {
     try {
            String query = "SELECT * FROM movie WHERE id ="+id;
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                while(resultado.next()){
                    this.movie.setId(resultado.getInt("id"));
                    this.movie.setName(resultado.getString("name"));
                    this.movie.setSinopsis(resultado.getString("sinopsis"));
                    this.movie.setPrice(resultado.getDouble("precio"));
                    this.movie.setGenre(resultado.getString("genre"));
                    this.movie.setImage(resultado.getString("imagen"));
                    
                    return this.movie;
                }
                
            }else{
                error = "We can not connect to server";
            }
            
        } catch (Exception e) {
                System.out.println("Exception: "+e.getMessage());
                error = "We can not connect to server";
        }finally{
                db.disconnect();
            }
            return null;

    }
    
    

    
}
