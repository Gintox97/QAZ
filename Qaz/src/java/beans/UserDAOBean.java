/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.User;
import implementacion.UserDAOImp;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Miste
 */

@ManagedBean(name="userDAO")
@RequestScoped
public class UserDAOBean {
    
    private User user = new User();
    private String error;
    private UserDAOImp dao;
    private Part file;
    private String imgB64;
    private String conttype;
    
    public UserDAOBean(){
        error = "";
        dao = new UserDAOImp();
        user =new User();
        
    }
    
//    public void agregar(){
//        if(imgB64 != null && !imgB64.isEmpty()){
//           
//            user.setConntype(conttype);
//            user.setImage(imgB64);
//            
//            
//            dao.crear(user);
//        }
//    }
//    
//    private void getBase64(){
//        try {
//            InputStream is = null;
//            if(file != null){
//                conttype = file.getContentType();
//                is = file.getInputStream();
//                byte[] archivo = new byte[is.available()];
//                is.read(archivo,0,archivo.length);
//                imgB64 = DatatypeConverter.printBase64Binary(archivo);
//                System.out.println("Img b64 = "+imgB64);
//            }
//        } catch (Exception e) {
//        }
//    }
    
    public String registrar(){
        String resultado = "registrar";
        boolean result = dao.crear(user);
        if(result){
            resultado = "index";
           
        }else{
            error = "We can not register the user";
        }
        return resultado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

//     public Part getFile() {
//        return file;
//    }
//
//    public void setFile(Part file) {
//        this.file = file;
//        getBase64();
//    }
//
//    public String getImgB64() {
//        return imgB64;
//    }
//
//    public void setImgB64(String imgB64) {
//        this.imgB64 = imgB64;
//    }
//
//    public String getConttype() {
//        return conttype;
//    }
//
//    public void setConttype(String conttype) {
//        this.conttype = conttype;
//    }
    
}
