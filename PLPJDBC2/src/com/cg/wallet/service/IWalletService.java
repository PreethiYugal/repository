package com.cg.wallet.service;


	import com.cg.wallet.bean.Account;
	import com.cg.wallet.exception.WalletException;

	public interface IWalletService {
		public boolean validatePhone(String phone) throws WalletException;
		public String createWallet(Account wallet) throws WalletException;
		public boolean validateName(String name) throws WalletException;
		public boolean validateEmail(String email) throws WalletException;
		public boolean validateBalance(double balance) throws WalletException ;
		public boolean validateWallet(Account wallet) throws WalletException ;
		public double showBalance(String phone) throws WalletException;
		public double deposit(String phone,double amount) throws WalletException;
		public double withdraw(String phone,double amount) throws WalletException;
		public boolean fundTransfer(String phone, String phone1, double amount) throws WalletException ;
		Account getAccountDetails (String phone) throws WalletException;



}