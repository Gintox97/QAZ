/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Movie;
import implementacion.MovieDAOImp;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Miste
 */
@ManagedBean(name="movie")
@RequestScoped
public class MovieBean {
    private Movie mov = new Movie();
    private Part file;
    private String conttype;
    private String imgB64;
    private MovieDAOImp dao;
    
    public MovieBean(){
        file=null;
        dao = new MovieDAOImp();
    }
    
    public void add(){
        if(imgB64 != null && !imgB64.isEmpty()){
            mov.setConttype(conttype);
            mov.setImage(imgB64);
            
            dao.crear(mov);
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

    public Movie getMov() {
        return mov;
    }

    public void setMov(Movie mov) {
        this.mov = mov;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
        getBase64();
    }
}
