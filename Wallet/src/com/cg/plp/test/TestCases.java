package com.cg.plp.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.plp.bean.Wallet;
import com.cg.plp.exception.WalletException;
import com.cg.plp.service.WalletService;
import com.cg.plp.service.IWalletService;

public class TestCases {
	/*IAccountDao dao;
	AccountDb db;*/
	Wallet acc;
	IWalletService service;
	
	@Before
	public void init() {
		// TODO Auto-generated method stub
		service = new WalletService();
		acc = new Wallet();
	}
	
	@Test
	//Given wrong mobile number.
	public void testAccountMobile(){
		
		acc.setName("Preethi");
		acc.setMobile("111111");
		acc.setEmail("preethi@gmail.com");
		acc.setBalance(500.0);
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("wrong mobile:"+e.getMessage());
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	// Given wrong name i.e. contains numbers 
	public void testAccountName(){
		
		acc.setName("1232456");
		acc.setMobile("1234567890");
		acc.setEmail("preethi@gmail.com");
		acc.setBalance(500.0);
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("name with numbers:"+e.getMessage());
			assertEquals("Name must contain only alphabets", e.getMessage());
		}	
	}
	@Test
	// given an empty name string
	public void testAccountName1(){
		
		acc.setName("");
		acc.setMobile("1234567890");
		acc.setEmail("preethi@gmail.com");
		acc.setBalance(500.0);
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("empty name:"+e.getMessage());
			assertEquals("Name cannot be empty", e.getMessage());
		}	
	}
	
	@Test
	public void testAccountEmail(){
		
		acc.setName("Preethi");
		acc.setMobile("1234567890");
		acc.setEmail("preethigmail.com");
		acc.setBalance(500.0);
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("wrong id:"+e.getMessage());
			assertEquals("Invalid Email ID", e.getMessage());
		}	
	}
	
	@Test
	public void testAccountBalance(){
		
		acc.setName("Preethi");
		acc.setMobile("1234567890");
		acc.setEmail("pree@cg.com");
		acc.setBalance(-500);
		//System.out.println(acc.getBalance());
		try {
			
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("negative balance:"+e.getMessage());
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}	
	}
	
	@Test
	public void testCreateAccount(){
		
		acc.setName("Preethi");
		acc.setMobile("1234567890");
		acc.setEmail("pree@cg.com");
		acc.setBalance(500);
		
		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testExistingAccount(){
		
		acc.setMobile("1111111111");
		try {
			service.createAccount(acc);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Account already exists in the Database", e.getMessage());
		}
		
	}
	
	@Test
	public void testShowBalanceNonExistingAccount(){
		acc.setMobile("1212121212");
		try {
			service.showBalance(acc.getMobile());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Account Does not exist", e.getMessage());
		}
	}
	
	@Test
	public void testShowBalanceExistingAccountWrongNumber(){
		acc.setMobile("11111111");
		try {
			service.showBalance(acc.getMobile());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testShowBalanceExistingName(){
		acc.setMobile("1111111111");
		try {
			acc = service.getAccountDetails(acc.getMobile());
			service.showBalance(acc.getMobile());
			assertEquals("Preethi",acc.getName() );
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testShowBalanceExisting(){
		acc.setMobile("1111111111");
		try {
			//acc = service.getAccountDetails(acc.getMobile());
			//System.out.println(service.showBalance(acc.getMobile()));
			assertEquals(500.0, service.showBalance(acc.getMobile()),0.5);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testDepositCheckMobile(){
		acc.setMobile("1111111");
		double depositAmt = 1500.0;
		try {
			if(service.validateMobile(acc.getMobile())){
				//acc = service.getAccountDetails(acc.getMobile());
				double bal = service.deposit(acc.getMobile(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testDepositCheckBalance(){
		acc.setMobile("1111111111");
		double depositAmt = -1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				//acc = service.getAccountDetails(acc.getMobile());
				double bal = service.deposit(acc.getMobile(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
		
	}
	
	@Test
	public void testDepositCorrect(){
		acc.setMobile("1111111111");
		double depositAmt = 1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				double bal = service.deposit(acc.getMobile(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
		}
	} //Account balance is Low
	
	@Test
	public void testWithdrawCheckMobile(){
		acc.setMobile("11111111");
		double withdrawAmt = 1500.0;
		try {
			if(service.validateMobile(acc.getMobile())){
				double bal = service.withdraw(acc.getMobile(), withdrawAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testWithdrawCheckBalance(){
		acc.setMobile("1111111111");
		double amt = -1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				//acc = service.getAccountDetails(acc.getMobile());
				double bal = service.withdraw(acc.getMobile(), amt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
		
	}
	
	@Test
	public void testWithdrawMoreAmount(){
		acc.setMobile("1111111111");
		double withdrawAmt = 1500.0;
		try {
			double bal = service.withdraw(acc.getMobile(), withdrawAmt);
			assertNotEquals(acc.getBalance(), bal);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Account balance is Low", e.getMessage());
			
		}
	}
	
	@Test
	public void testWithdrawCorrect(){
		acc.setMobile("1111111111");
		double withdrawAmt = 1500.0;
		try {
			double bal = service.withdraw(acc.getMobile(), withdrawAmt);
			assertNotEquals(acc.getBalance(), bal);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Account balance is Low", e.getMessage());
			
		}
	}
	
	@Test
	public void testFundTransferMobile1Valid(){
		Wallet acc1 = new Wallet();
		Wallet acc2 = new Wallet();
		acc1.setMobile("333333");
		acc2.setMobile("2222222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferMobile2Valid(){
		Wallet acc1 = new Wallet();
		Wallet acc2 = new Wallet();
		acc1.setMobile("3333333333");
		acc2.setMobile("222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferAmountValidation(){
		Wallet acc1 = new Wallet();
		Wallet acc2 = new Wallet();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = -100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Amount must be a number greater than zero", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferMoreAmount(){
		Wallet acc1 = new Wallet();
		Wallet acc2 = new Wallet();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = 1500;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Account balance is low", e.getMessage());
		}
	}	
	
	@Test
	public void testFundTransferNonExistingAmount(){
		Wallet acc1 = new Wallet();
		Wallet acc2 = new Wallet();
		acc1.setMobile("3333333333");
		acc2.setMobile("1234567890");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Account doesnot exist. Amount can't be transferred", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferExistingAmount(){
		Wallet acc1 = new Wallet();
		Wallet acc2 = new Wallet();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testPrintTransaction(){
		acc = new Wallet();
		acc.setMobile("1111111111");
		try {
			Wallet ac = service.getAccountDetails(acc.getMobile());
			assertNotNull(ac);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
