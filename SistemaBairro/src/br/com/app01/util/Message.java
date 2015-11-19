package br.com.app01.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Message {
	public static void infoMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage("Info",  facesMessage);
	}
	
	public static void warningMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
	    FacesContext.getCurrentInstance().addMessage("Atenção",  facesMessage);
	}
	
	public static void successMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage("Sucesso",  facesMessage);
	}
	
	public static void errorMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
	    FacesContext.getCurrentInstance().addMessage("Erro",  facesMessage);
	}
}
