/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacion;

import configuracion.DBHelper;
import entidades.Game;
import interfaces.IGameDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miste
 */
public class GameDAOImp implements IGameDAO{
    private Game game = new Game();
    private DBHelper db = new DBHelper();
    private String error;
    
    @Override
    public boolean crear(Game game) {
        
        boolean resultado = false;
        
        try {
            String query  ="INSERT INTO game(nombre,descripcion,"
                    + "precio,genero,plataforma,imagen,conttype) VALUES("
                    + "'"+game.getName()+"',"
                    + "'"+game.getDescription()+"',"
                    + ""+game.getPrice()+","
                    + "'"+game.getGenero()+"',"
                    + "'"+game.getPlatform()+"',"
                    + "'"+game.getImage()+"',"
                    + "'"+game.getConttype()+"'"
                    + ")";
             
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
    public boolean editar(Game t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> obtenerTodos() {
            List<Game> games = new ArrayList();
            
            try {
            String query = "SELECT * FROM game";
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                while(resultado.next()){
                    this.game.setId(resultado.getInt("id"));
                    this.game.setName(resultado.getString("nombre"));
                    this.game.setDescription(resultado.getString("descripcion"));
                    this.game.setPrice(resultado.getDouble("precio"));
                    this.game.setGenero(resultado.getString("genero"));
                    this.game.setPlatform(resultado.getString("plataforma"));
                    this.game.setImage(resultado.getString("imagen"));
                    
                    games.add(this.game);
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
            return games;

    }

    @Override
    public Game obtenerId(int id) {
         try {
            String query = "SELECT * FROM game WHERE id ="+id;
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                while(resultado.next()){
                    this.game.setId(resultado.getInt("id"));
                    this.game.setName(resultado.getString("nombre"));
                    this.game.setDescription(resultado.getString("descripcion"));
                    this.game.setPrice(resultado.getDouble("precio"));
                    this.game.setGenero(resultado.getString("genero"));
                    this.game.setPlatform(resultado.getString("platforma"));
                    this.game.setImage(resultado.getString("imagen"));
                    
                  return this.game;
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
    
    public String getError() {
        return error;
    }
}
