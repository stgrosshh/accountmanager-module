/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package org.bcbhh;

import java.util.HashMap;
import java.util.Map;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;


@Kroll.module(name="Accman2", id="org.bcbhh")
public class Accman2Module extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "Accman2Module";
	private static final boolean DBG = TiConfig.LOGD;
	private static AccountManager _accMan;
	
	private static Map<String,AuthenticatorDescription>_authenticatorLookup;
	private static Resources _resources;
	private static PackageManager _packageMgr; 
	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public Accman2Module()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		
		_packageMgr = app.getPackageManager();
		_resources = app.getResources();
		_accMan = AccountManager.get(app.getApplicationContext());
		AuthenticatorDescription[] accTypes = _accMan.getAuthenticatorTypes();
		_authenticatorLookup = new HashMap<String,AuthenticatorDescription>();
		for(AuthenticatorDescription authDesc:accTypes){
			_authenticatorLookup.put(authDesc.type, authDesc);
		}
		Log.d(LCAT, "inside onAppCreate");
	}
	
	
	@Kroll.method
	public KrollDict[] getAccounts(){
		
		Account[] accounts = _accMan.getAccounts();
		KrollDict[] accountList = new KrollDict[accounts.length];
		int idx = 0;
		for(Account account : accounts){
			KrollDict accountDict = new KrollDict();
			accountDict.put("name", account.name);
			accountDict.put("type", account.type);
			accountDict.put("accountType", labelForAuthenticator(_authenticatorLookup.get(account.type)));
			accountList[idx] = accountDict;
			idx++;
		}
		return accountList;
	}

	private String labelForAuthenticator(AuthenticatorDescription authenticatorDescription) {
		Resources resources;
		try {
			resources = _packageMgr.getResourcesForApplication(authenticatorDescription.packageName);
		} catch (NameNotFoundException e) {
			return authenticatorDescription.type;
		}
		return resources.getString(authenticatorDescription.labelId);
	}

	
}
