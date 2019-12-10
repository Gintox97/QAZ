/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Item;
import implementacion.ItemDAOImp;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Miste
 */
@ManagedBean(name="item")
@RequestScoped
public class ItemBean {
    private Item it = new Item();
    private List<Item> lista = new ArrayList();   
    private Part file;
    private String conttype;
    private String imgB64;
    private ItemDAOImp dao;
    
    public ItemBean(){
        file = null;
        dao= new ItemDAOImp();
        
        ItemDAOImp ito =new ItemDAOImp();
        try{
        lista =  ito.obtenerTodos();
        }catch(Exception e){
            
        }
    }
    
   
    
    public void add(){
        if(imgB64 != null && !imgB64.isEmpty()){
            it.setConttype(conttype);
            it.setImage(imgB64);
            
            dao.crear(it);
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
    
    

    public Item getIt() {
        return it;
    }

    public void setIt(Item it) {
        this.it = it;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
        getBase64();
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }
    
    
}
