
package beans;

import entidades.User;
import implementacion.UserDAOImp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="userDAO")
@RequestScoped
public class UserDAOBean {
 
    private User usuario;
    private String error;
    private UserDAOImp dao;
    
    public UserDAOBean(){
        error="";
        dao = new UserDAOImp();
        usuario = new User();
    }
    
    public String registrar(){
        String resultado = "registrar";
        boolean result = dao.crear(usuario);
        if(result){
            resultado = "index";
        }else{
            error = "We can not register the user";
        }
        return resultado;
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

    public void setError(String error) {
        this.error = error;
    }
    
    
}
