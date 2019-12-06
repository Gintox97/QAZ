/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import configuracion.DBHelper;
import configuracion.Seguridad;
import entidades.User;
import implementacion.UserDAOImp;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;
/**
 *
 * @author Miste
 */
@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable {

    private User usuario;
    private String error;
    private UserDAOImp DAO;
    private boolean rememberme= false;
    
    public UserBean(){
        usuario = new User();
        error = "";
        DAO = new UserDAOImp();
    }

    
    public String login(){
        
        if(usuario.getUsername() != null
        && !usuario.getUsername().equals("")
        && usuario.getPassword() != null
        && !usuario.getPassword().equals("")){
            
            this.usuario = DAO.login(usuario);
            if(this.usuario != null && (DAO.getError() == null || DAO.getError().isEmpty())){
                if(this.rememberme){
                    guardarCookie();
                }
                
                return "qazStudio";
            }else{
                this.error = DAO.getError();
            }
        }
        return "index";
    }
    
        public void loadCookie(){
            Cookie cookie = getCookie("rememberme");
            if(cookie != null){
                try {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    String user[] = Seguridad.decrypt(cookie.getValue()).split(":");
                    
                    this.usuario.setId(Integer.valueOf(user[0]));
                    this.usuario.setFullname(user[1]);
                    this.usuario.setEmail(user[2]);
                    this.usuario.setUsername(user[3]);
                    
                    fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "qazStudio");
                    
                } catch (Exception e) {
                    Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, e);
                    
                }
            }
        }
    
        public void guardarCookie(){
            try {
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
                
                Cookie cookie = null;
                Cookie userCookies[] = request.getCookies();
                if(userCookies != null && userCookies.length > 0){
                    for(Cookie ck:userCookies){
                        if(ck.getName().equals("rememberme")){
                            cookie = ck;
                            break;
                        }
                    }
                }
                
                String valor = this.usuario.getId()+":"+this.usuario.getFullname()+":";
                       valor+= this.usuario.getEmail()+":"+this.usuario.getUsername();
                       
                       valor = Seguridad.encrypt(valor);
                       if(cookie != null){
                           cookie.setValue(valor);
                       }else{
                           cookie = new Cookie("rememberme", valor);
                           cookie.setPath(request.getContextPath());
                           
                       }
                       
                       cookie.setMaxAge(3600);
                       HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
                       
                       response.addCookie(cookie);
            } catch (Exception e) {
            }
        }
        
        public Cookie getCookie(String name){
           try{
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            
            Cookie cookie = null;
            Cookie userCookies[] = request.getCookies();
            if(userCookies != null && userCookies.length > 0){
            for(Cookie ck: userCookies){
                if(ck.getName().equals("rememberme")){
                    cookie = ck;
                    return cookie;
                }
            }
        }
           }catch(Exception e){
               
           }
           return null;
        }
        
        public User getUsuario() {
        return usuario;
    }
    

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getError() {
        return error;
    }

   

    public boolean isRememberme() {
        return rememberme;
    }

    public void setRememberme(boolean rememberme) {
        this.rememberme = rememberme;
    }
   
}
