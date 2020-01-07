/**
 * 
 */
package com.example.myapplication.config.passwordvalidator;

import com.example.myapplication.config.ConfigFront;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dm
 *
 */
public class PasswordValidator {
	
	private List<ValidationRule> rules;
	
	/**
	 * Runs all your validation rules and returns true if it's valid, false otherwise
	 * @param inPassword
	 * @return boolean true if valid, false if otherwise
	 */
	public boolean validate(String inPassword) {
		for (ValidationRule rule : this.getRules()) {
			if (!rule.validate(inPassword)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Runs all your validation rules and returns a {@link ValidationResult} with messages if
	 * the password was invalid
	 * @param inPassword
	 * @return {@link ValidationResult} The validation result
	 */
	public ValidationResult validateWithMessages(String inPassword) {
		ValidationResult returnValue = new ValidationResult();
		List<String> messages = new ArrayList<String>();
		for (ValidationRule rule : this.getRules()) {
			if (!rule.validate(inPassword)) {
				messages.add(rule.getMessage());
			}
		}
		if (messages.size() > 0) {
			returnValue.setValid(false);
		}
		returnValue.setMessages(messages);
		return returnValue;
	}
	
	public List<ValidationRule> getRules() {
		return this.rules == null ? this.getDefaultRules() : this.rules;
	}
	
	public void setRules(List<ValidationRule> inRules) {
		this.rules = inRules;
	}
	
	private List<ValidationRule> getDefaultRules() {
		List<ValidationRule> returnValue = new ArrayList<ValidationRule>();


		returnValue.add(new RegexValidationRule(".*[a-z].*", ConfigFront.ERROR_CREATION_PASSWORD_MISSING_MIN));
		returnValue.add(new RegexValidationRule(".*[A-Z].*", ConfigFront.ERROR_CREATION_PASSWORD_MISSING_MAJ));
		returnValue.add(new RegexValidationRule(".*[^[A-Za-z]].*", ConfigFront.ERROR_CREATION_PASSWORD_MISSING_SPECIAL));
		returnValue.add(new RegexValidationRule(".{8,20}", ConfigFront.ERROR_CREATION_PASSWORD_LENGTH_PROBLEM));
		return returnValue;
	}

} 
