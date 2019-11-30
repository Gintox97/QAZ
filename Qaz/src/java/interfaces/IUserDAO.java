/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.User;

/**
 *
 * @author Miste
 */
public interface IUserDAO extends CRUD<User> {
     public User login(User usuario);
}
