package com.cg.plp.db;

import java.time.LocalDate;
import java.util.HashMap;

import com.cg.plp.bean.Wallet;

public class WalletDb {
	private static HashMap<String, Wallet> accountDb = new HashMap<String, Wallet>();

	public static HashMap<String, Wallet> getAccountDb() {
		return accountDb;
	}

	static {
		accountDb.put("1111111111", new Wallet("Preethi", "1111111111",
				"preethi@gmail.com", 500.0, LocalDate.now()));
		
		accountDb.put("2222222222", new Wallet("Yugal", "2222222222",
				"yugal@gmail.com", 4000.0, LocalDate.now()));
		
		accountDb.put("3333333333", new Wallet("Preetham", "3333333333",
				"preetham@gmail.com", 2100.0, LocalDate.now()));
		
		accountDb.put("4444444444", new Wallet("Chandra", "4444444444",
				"chandra@gmail.com", 6000.0, LocalDate.now()));

	}

}

