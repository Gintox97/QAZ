/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacion;

import configuracion.DBHelper;
import entidades.Game;
import interfaces.IGameDAO;
import java.util.List;

/**
 *
 * @author Miste
 */
public class GameDAOImp implements IGameDAO{

    DBHelper db = new DBHelper();
    
    
    @Override
    public boolean crear(Game game) {
        
        boolean resultado = false;
        
        try {
            String query  ="INSERT INTO game(name,description,"
                    +"price,tipo,genero,plataforma,image,conttype) VALUES("
                    +"'"+game.getName()+"',"
                    +"'"+game.getDescription()+"',"
                    +""+game.getPrice()+","
                    +"'"+game.getType()+"',"
                    +"'"+game.getGenero()+"',"
                    +"'"+game.getPlatform()+"',"
                    +"'"+game.getImage()+"',"
                    +"'"+game.getConttype()+"'"
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
    public boolean editar(Game t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game obtenerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
