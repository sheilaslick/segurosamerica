package com.segurosamerica.nicaragua.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Database extends Activity {
	private String log = "Database";
	private DatabaseHelper databaseHelper;
	public SQLiteDatabase sqlite = null;

	public static final int DB_WRITE_AND_READ = 1;
	public static final int DB_READ = 2;

	public Database(Context context) {
		super();

		databaseHelper = new DatabaseHelper(context);

		databaseHelper.initializeDataBase();

		connect(DB_READ);

	}

	/*
	 * connect
	 * 
	 * @description Open connection
	 * 
	 * @return none
	 */
	public void connect(int type) {

		try {
			switch (type) {

			case DB_WRITE_AND_READ:
				sqlite = databaseHelper.getWritableDatabase();
				break;
			case DB_READ:
				sqlite = databaseHelper.getReadableDatabase();
				break;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}

	/*
	 * disconnect
	 * 
	 * @description Close connection
	 * 
	 * @return none
	 */
	public void disconnect() {

		try {
			databaseHelper.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlite.close();
		}
	}

	/*
	 * insertPolicy
	 * 
	 * @description Set a insertPolicy
	 * 
	 * @return none
	 */
	public void insertPolicy(JSONObject row) {
		connect(DB_WRITE_AND_READ);
		String query;
		try {
			query = "INSERT INTO tblPolicy VALUES ( null, '" + row.getString("insured") + "', '" + row.getString("policy_number") + "', '" + row.getString("validity") + "', '" + row.getString("expires") + "' )";
			sqlite.execSQL(query);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void updatePolicy(JSONObject row) {
		connect(DB_WRITE_AND_READ);
		String query;
		try {
			query = "UPDATE tblPolicy SET policy_insured = '"+row.getString("insured")+"', policy_insurance_number = '"+row.getString("policy_number")+"', policy_validity = '"+row.getString("validity")+"', policy_expires = '"+row.getString("expires")+"' WHERE policy_id = '"+row.getString("id")+"'";
			sqlite.execSQL(query);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void deletePolicy(String id) {
		connect(DB_WRITE_AND_READ);
		String query = "DELETE FROM tblPolicy WHERE policy_id = '"+id+"'";
			sqlite.execSQL(query);
		
	}

	/*
	 * getPolicy
	 * 
	 * @description Return a cursor from id
	 * 
	 * @return Cursor
	 */
	@SuppressWarnings("deprecation")
	public Cursor getPolicy(String id) {
		Log.i(log, "getArticle:" + id);
		connect(DB_READ);
		String[] where = new String[] { id };

		String query = "";
		query = "SELECT * FROM tblPolicy WHERE policy_id = ?";
		Cursor cursor = sqlite.rawQuery(query, where);
		startManagingCursor(cursor);
		return cursor;
	}

	/*
	 * getAllPolicies
	 * 
	 * @description
	 * 
	 * @return Cursor
	 */
	@SuppressWarnings("deprecation")
	public Cursor getAllPolicies() {
		connect(DB_READ);
		String query = "SELECT * FROM tblPolicy ORDER BY policy_id DESC LIMIT 5";
		Cursor cursor = sqlite.rawQuery(query, null);
		startManagingCursor(cursor);
		return cursor;
	}

	/*
	 * setConfig
	 * 
	 * @description Set a config based in config_key
	 * 
	 * @return none
	 */
	public void setConfig(String config_key, String config_value) {
		Log.i(log, "setConfig");
		connect(DB_WRITE_AND_READ);
		String query = "";
		String[] key = new String[] { config_key };
		if (getConfig(key).equals("")) {
			query = "INSERT INTO tblConfig VALUES ( null, '" + config_key
					+ "','" + config_value + "')";
		} else {
			query = "UPDATE tblConfig SET config_value = '" + config_value
					+ "' WHERE config_key = '" + config_key + "'";
		}
		sqlite.execSQL(query);

	}

	/*
	 * deleteConfig
	 * 
	 * @description Delete a row in tblConfig
	 * 
	 * @return none
	 */
	public void deleteConfig(String config_key) {
		Log.i(log, "deleteConfig");
		connect(DB_READ);
		String query = "DELETE FROM tblConfig WHERE config_key = '"
				+ config_key + "'";

		sqlite.execSQL(query);

	}

	/*
	 * getConfig
	 * 
	 * @description Return a String value based in config_key
	 * 
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public String getConfig(String[] where) {
		Log.i(log, "getConfig");
		connect(DB_READ);
		String returnData = "";
		String query = "SELECT config_value FROM tblConfig WHERE config_key = ?";
		Cursor cursor = sqlite.rawQuery(query, where);
		startManagingCursor(cursor);

		if (cursor.getCount() == 0) {
			cursor.close();
			return returnData;
		}
		while (cursor.moveToNext()) {
			returnData = cursor
					.getString(cursor.getColumnIndex("config_value"));
		}
		cursor.close();
		return returnData;
	}

	/*
	 * truncateTable
	 * 
	 * @description empty rows in table
	 * 
	 * @return Cursor
	 */

	public void truncateTable(String table) {
		Log.i(log, "truncateTable " + table);
		connect(DB_READ);

		String query = "DELETE FROM " + table;

		sqlite.execSQL(query);

	}
}
