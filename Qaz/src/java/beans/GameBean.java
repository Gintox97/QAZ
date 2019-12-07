/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Game;
import implementacion.GameDAOImp;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Miste
 */
@ManagedBean(name="game")
@RequestScoped
public class GameBean {
    private Game game = new Game();
    private Part file;
    private String conttype;
    private String imgB64;
    private GameDAOImp dao;
    
    public GameBean(){
        file = null;
        dao = new GameDAOImp();
    }
    
    public void add(){
        if(imgB64!=null && !imgB64.isEmpty()){ // png jpg gif bmp  //mp4 mpeg wav 
           
            game.setConttype(conttype);
            game.setImage(imgB64);
            
            dao.crear(game);
        }
    }
    
   private void getBase64(){
        try {
            InputStream is = null;            
            if(file != null){
                conttype = file.getContentType();
//                file.getSize()
                is = file.getInputStream();                
                byte[] archivo = new byte[is.available()];
                is.read(archivo,0,archivo.length);                
                imgB64 = DatatypeConverter.printBase64Binary(archivo);                
                System.out.println("Img b64 = "+imgB64);
            }
            
        } catch (Exception e) {
            
        }
    }
   
//   public void tabla() throws SQLException{
//       PreparedStatement ps;
//       ResultSet rs;
//       ps = (PreparedStatement) dao.obtenerTodos();
//       rs=ps.executeQuery();
//       
//       while(rs.next()){
//           rs.getString("nombre");
//           rs.getString("descripcion");
//           rs.getDouble("precio");
//           rs.getString("genero");
//           rs.getString("plataforma");
//           rs.getBlob("imagen");
//       }
//   }
   
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
        getBase64();
    }

  
   

   
    
    
}
