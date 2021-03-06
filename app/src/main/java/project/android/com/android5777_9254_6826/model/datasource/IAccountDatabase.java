//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : IAccountDatabase.java
//  @ Date : 2016-11-21
//  @ Author : 
//
//
package project.android.com.android5777_9254_6826.model.datasource;


import java.util.ArrayList;

import project.android.com.android5777_9254_6826.model.entities.Account;

public interface IAccountDatabase extends IDatabase {
	/**
	 *
	 * @param UserName
	 * @param Password
     * @return true - if succeeded , else false;
     */
	public boolean addNewAccount(String UserName, String Password);
	public ArrayList<Account> getAccountList();
	public Account getAccount(long id) throws Exception;
	public Account getAccount(String username) throws Exception;
	public boolean isRegistered(String userName);
}
