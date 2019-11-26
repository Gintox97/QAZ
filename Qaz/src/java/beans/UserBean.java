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
    private User user;
    private String error;
    private UserDAOImp DAO;
    private boolean rememberMe = false;
    
    
    
    public UserBean(){
        user = new User();
        error = "";
        DAO = new UserDAOImp();
    }
    
    
    
    public String login(){
        
        if(user.getNickname() != null
           && !user.getNickname().equals("")
           && user.getPassword() != null
           && !user.getPassword().equals("")){
            this.user = DAO.login(user);
            if(this.user != null && (DAO.getError()== null || DAO.getError().isEmpty())){
            if(this.rememberMe){
                guardarC();
            }
            return "inicio";
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
                
                this.user.setId(Integer.valueOf(user[0]));
                this.user.setFullname(user[1]);
                this.user.setNickname(user[2]);
                this.user.setEmail(user[3]);
                this.user.setImage(user[4]);
                this.user.setConntype(user[5]);
                
                fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "Inicio");
            } catch (Exception e) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, e);
                
                
            }
        }
    }
    
    private void guardarC(){
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            
            Cookie cookie = null;
            Cookie userCookies[] = request.getCookies();
            if(userCookies != null && userCookies.length > 0){
                for(Cookie ck: userCookies){
                    if(ck.getName().equals("rememberme")){
                        cookie = ck;
                        break;
                    }
                }
            }
            
            String valor = this.user.getId()+":"+this.user.getFullname()+":";
                   valor+= this.user.getNickname()+":"+this.user.getEmail();
                   
                   
                   valor = Seguridad.encrypt(valor);
                   if(cookie != null){
                       cookie.setValue(valor);
                   }else{
                       cookie = new Cookie("rememberme",valor);
                       cookie.setPath(request.getContextPath());
                   }
                   cookie.setMaxAge(3600);
                   HttpServletResponse response = (HttpServletResponse)fc.getExternalContext().getResponse();
                   response.addCookie(cookie);
            
        } catch (Exception e) {
        }
    }
    
    public Cookie getCookie(String name){
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest)
                    fc.getExternalContext().getRequest();
            
            Cookie cookie = null;
            Cookie userCookie[] = request.getCookies();
            if(userCookie != null && userCookie.length > 0){
                for(Cookie ck:userCookie){
                    if(ck.getName().equals("rememberme")){
                        cookie = ck;
                        return cookie;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
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

    public UserDAOImp getDAO() {
        return DAO;
    }

    public void setDAO(UserDAOImp DAO) {
        this.DAO = DAO;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

   
}
