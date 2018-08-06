package com.cg.wallet.bean;
import com.cg.wallet.service.IWalletService;
	import com.cg.wallet.service.WalletService;

	public class Account {
		@Override
		public String toString() {
			return "Wallet [ name=" + name + ", phone="
					+ phone + ", email=" + email + ", balance=" + balance + "]";
		}
		IWalletService service=new WalletService();
		private String name;
		private String phone;
		private String email;
		private double balance;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public Account(String name, String phone, String email, double balance) {
			super();
			this.name = name;
			this.phone = phone;
			this.email = email;
			this.balance = balance;
		}
		public Account() {
			
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}

	}