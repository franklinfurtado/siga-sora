/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 *
 * @author scm
 */
@Controller("loginBean")
@Scope("view")
public class LoginBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    public String login() {
    	return "/pastoral_home.xhtml?faces-redirect=true";
    }
}
