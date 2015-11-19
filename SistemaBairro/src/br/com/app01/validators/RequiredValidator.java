package br.com.app01.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("requiredValidator")
public class RequiredValidator implements Validator {

	private static final String REQUIRED_PATTERN = "^(.+)$";

	private Pattern pattern;
	private Matcher matcher;
	
	public RequiredValidator(){
		  pattern = Pattern.compile(REQUIRED_PATTERN);
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(!continueValidation()) {
			return;
		}
		matcher = pattern.matcher(String.valueOf((value == null) ? "" : value));
		if(!matcher.matches()){
			FacesMessage msg = 
				new FacesMessage(component.getId() + ": O preenchimento é obrigatório.", 
						"O preenchimento do campo é obrigatório.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}
	
	protected boolean continueValidation() {
		String skipValidator= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidator");
		if (skipValidator != null && skipValidator.equalsIgnoreCase("true")) {
			return false;
		}
		
		return true;
	} 
}
