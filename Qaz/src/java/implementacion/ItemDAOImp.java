/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacion;

import configuracion.DBHelper;
import entidades.Item;
import interfaces.IItemDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miste
 */
public class ItemDAOImp implements IItemDAO{

    private Item item = new Item();
    private DBHelper db = new DBHelper();
    private String error;

    @Override
    public boolean crear(Item item) {

        boolean resultado = false;
        try{
        String query = "INSERT INTO item(name,description,price,image,conttype)"
                +"VALUES("
                +"'"+item.getName()+"',"
                +"'"+item.getDescription()+"',"
                +""+item.getPrice()+","
                +"'"+item.getImage()+"',"
                +"'"+item.getConttype()+"'"
                +")";
        
        if(db.connect()){
            resultado = (boolean) db.execute(query, true);
            
        }
        }catch(Exception e){
            
        }finally{
            db.disconnect();
        }
        return resultado;

    }

    @Override
    public boolean editar(Item t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> obtenerTodos() {

            List<Item> items = new ArrayList();
            
            try {
            String query = "SELECT * FROM item";
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                
                while(resultado.next()){
                    this.item.setId(resultado.getInt("id"));
                    this.item.setName(resultado.getString("name"));
                    this.item.setDescription(resultado.getString("description"));
                    this.item.setPrice(resultado.getDouble("price"));
                    this.item.setImage(resultado.getString("image"));
                    
                    items.add(this.item);
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
            return items;

    }

    @Override
    public Item obtenerId(int id) {

 try {
            String query = "SELECT * FROM item WHERE id="+id;
            
            if(db.connect()){
                ResultSet resultado = (ResultSet) db.execute(query, false);
                
                while(resultado.next()){
                    this.item.setId(resultado.getInt("id"));
                    this.item.setName(resultado.getString("name"));
                    this.item.setDescription(resultado.getString("description"));
                    this.item.setPrice(resultado.getDouble("price"));
                    this.item.setImage(resultado.getString("image"));
                    
                    return this.item;
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
