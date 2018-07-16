package com.cg.plp.service;

import com.cg.plp.bean.Wallet;
import com.cg.plp.exception.WalletException;

public interface IWalletService {
	Wallet getAccountDetails (String mobile) throws WalletException;
	String createAccount(Wallet account) throws WalletException;
	double showBalance(String mobile) throws WalletException;
	double deposit(String mobile, double amt) throws WalletException;
	double withdraw(String mobile, double amt) throws WalletException;
	boolean fundTransfer(String mobile1, String mobile2, double amount) throws WalletException;
	
	boolean validateAccount(Wallet account) throws WalletException;
	boolean validateMobile(String mobile) throws WalletException;
	boolean validateName(String name) throws WalletException;
	boolean validateEmail(String email) throws WalletException;
	boolean validateBalance(double balance) throws WalletException;

}

