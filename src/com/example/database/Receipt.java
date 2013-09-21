package com.example.database;

public class Receipt {
	// Receipt identifiers.
	int receipt_id;
	String receipt_name;
	int user_id;
	
	// Receipt details.
	String receipt_desc;
	String receipt_date;
	double payable_amount;
	
	// Receipt status.
	String user_conf;
	String company_conf;
	
	public Receipt() {	}
		
	public Receipt(int receipt_id, String receipt_name, int user_id, 
			String receipt_desc, String receipt_date,
			double payable_amount, String user_conf, String company_conf) {
		super();
		this.receipt_id = receipt_id;
		this.receipt_name = receipt_name;
		this.user_id = user_id;
		this.receipt_desc = receipt_desc;
		this.receipt_date = receipt_date;
		this.payable_amount = payable_amount;
		this.user_conf = user_conf;
		this.company_conf = company_conf;
	}

	public Receipt(int receipt_id, String receipt_name, int user_id,
			String receipt_desc, String receipt_date, double payable_amount) {
		super();
		this.receipt_id = receipt_id;
		this.receipt_name = receipt_name;
		this.user_id = user_id;
		this.receipt_desc = receipt_desc;
		this.receipt_date = receipt_date;
		this.payable_amount = payable_amount;
		user_conf = "false";
		company_conf = "false";
	}

	public int getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(int receipt_id) {
		this.receipt_id = receipt_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getReceipt_name() {
		return receipt_name;
	}

	public void setReceipt_name(String receipt_name) {
		this.receipt_name = receipt_name;
	}

	public String getReceipt_desc() {
		return receipt_desc;
	}

	public void setReceipt_desc(String receipt_desc) {
		this.receipt_desc = receipt_desc;
	}

	public String getReceipt_date() {
		return receipt_date;
	}

	public void setReceipt_date(String receipt_date) {
		this.receipt_date = receipt_date;
	}

	public double getPayable_amount() {
		return payable_amount;
	}

	public void setPayable_amount(double payable_amount) {
		this.payable_amount = payable_amount;
	}

	public String getUser_conf() {
		return user_conf;
	}

	public void setUser_conf(String user_conf) {
		this.user_conf = user_conf;
	}

	public String getCompany_conf() {
		return company_conf;
	}

	public void setCompany_conf(String company_conf) {
		this.company_conf = company_conf;
	}
	
	public String toString() {
		String user = "Yes", company = "Yes";
		if(user_conf.equals("false"))
			user = "No";
		if(company_conf.equals("false"))
			company = "No";
		
		return "Receipt Name: " + receipt_name + 
				"\n\nExpenditure Date: " + receipt_date +
				"\n\nPayable Amount: $" + payable_amount +
				"\n\nDescription: " + receipt_desc +
				"\n\nUser Confirmation: " + user + 
				"\n\nCompany Confirmation: " + company;	
	}
	
}
