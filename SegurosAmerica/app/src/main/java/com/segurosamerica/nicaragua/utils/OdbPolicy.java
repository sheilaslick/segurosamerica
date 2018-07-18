package com.segurosamerica.nicaragua.utils;

import android.database.Cursor;

public class OdbPolicy {
	private int count = 0;

    // vars
    private int policy_id;
    private String policy_insured;
    private String policy_insurance_number;
    private String policy_validity;
    private String policy_expires;

    // columnName
    private String column_policy_id = "policy_id";
    private String column_policy_insured = "policy_insured";
    private String column_policy_insurance_number = "policy_insurance_number";
    private String column_policy_validity = "policy_validity";
    private String column_policy_expires = "policy_expires";
    
    public OdbPolicy(){
            super();
    }
    
    public OdbPolicy( Cursor c ){
            super();
            
            this.count = c.getCount();

            if (c.getCount() > 0) {
                    while (c.moveToNext()) {
                            this.policy_id = c.getInt(c.getColumnIndex(column_policy_id));
                            this.policy_insured = c.getString(c.getColumnIndex(column_policy_insured));
                            this.policy_insurance_number = c.getString(c.getColumnIndex(column_policy_insurance_number));
                            this.policy_validity = c.getString(c.getColumnIndex(column_policy_validity));
                            this.policy_expires = c.getString(c.getColumnIndex(column_policy_expires));
                    }
                    c.close();
            }
            else{
                    c.close();
            }
    }
    
    public int getCount(){
            return this.count;
    }
    
    public int getId() {
            return this.policy_id;
    }
    
    public String getInsured(){
            return this.policy_insured;
    }
    
    public String getInsuranceNumber(){
            return this.policy_insurance_number;
    }
    
    public String getValidity(){
            return this.policy_validity;
    }
    
    public String getExpires(){
            return this.policy_expires;
    }

}