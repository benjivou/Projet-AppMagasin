package com.example.myapplication;

import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.config.passwordvalidator.PasswordValidator;


import static org.junit.Assert.assertEquals;


import org.junit.Test;



public class ValidationTests {
	

	
	@Test
	public void testLengthRule() {
		PasswordValidator pv = new PasswordValidator();
		assertEquals(pv.validate("A1a"), false);								// too short
		assertEquals(pv.validate("A1A123abc123"), true);						// test valid
		assertEquals(pv.validate("aA(azdae"), true);							// test valid
		assertEquals(pv.validate("aA(azdaeazdazdafzrzgffsf'fzfzfz'f"), false); // too long

	}
	
	@Test
	public void testMajRule() {
		PasswordValidator pv = new PasswordValidator();
		assertEquals(pv.validate("12345678902a"), false);
		assertEquals(pv.validate("123456789012345a"), false);
		assertEquals(pv.validate("1aAzfzefdfzsef"), true);
		assertEquals(pv.validate("?azeadaA"), true);
	}
	
	@Test
	public void testAtLeastOneNumber() {
		PasswordValidator pv = new PasswordValidator();
		assertEquals(pv.validate("abadsfas"), false);
		assertEquals(pv.validate("1abadsfa"), false);
		assertEquals(pv.validate("bja1414fA"), true);
		assertEquals(pv.validate("#@)($*#@()$"), false);
	}
	
	@Test
	public void testAtLeastOneLowercaseLetter() {
		PasswordValidator pv = new PasswordValidator();
		assertEquals(pv.validate("aB14324234"), true);
		assertEquals(pv.validate("112312312"), false);
		assertEquals(pv.validate("badfkbjaB1414"), true);
		assertEquals(pv.validate("#@)($*#@()$"), false);
	}
	
	@Test
	public void testSpecialCharLowercaseUpperCaseOnly() {
		PasswordValidator pv = new PasswordValidator();
		assertEquals(pv.validate("ab2342"), false);
		assertEquals(pv.validate("123aaaaa"), false);
		assertEquals(pv.validate("@#$#$"), false);
		assertEquals(pv.validate("1Aabc"), false);
		assertEquals(pv.validate("Ab1i;;ekn"),true);

	}
	public  void testErrorMessage(){
		PasswordValidator pv = new PasswordValidator();
		assertEquals(pv.validateWithMessages("123aaaaa").equals(ConfigFront.ERROR_CREATION_PASSWORD_CORRESPONDANCE_PROBLEM), false);
		assertEquals(pv.validateWithMessages("123aaaaa").equals(ConfigFront.ERROR_CREATION_PASSWORD_MISSING_MAJ), true);
	}
	
}
