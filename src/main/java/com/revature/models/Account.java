package com.revature.models;

public class Account {
	
	private int accountID;
	private String uName;
	private String pWord;
	private String fname;
	private String lname;
	private String accessType;
	private boolean has_checkings;
	private boolean has_savings;
	private double checking_balance;
	private double saving_balance;
	
	public Account(int accountID, String uName, String pWord, String fname, String lname, String accessType, boolean has_savings, boolean has_checkings,
			double checking_balance, double saving_balance) {
		super();
		this.accountID = accountID;
		this.uName = uName;
		this.pWord = pWord;
		this.fname = fname;
		this.lname = lname;
		this.accessType = accessType;
		this.has_checkings = has_checkings;
		this.has_savings = has_savings;
		if (checking_balance >=0 && has_checkings)
			this.checking_balance = checking_balance;
		if (saving_balance >=0 && has_savings)
			this.saving_balance = saving_balance;
	}

	public Account() {
		super();
	}

	
	
	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getpWord() {
		return pWord;
	}

	public void setpWord(String pWord) {
		this.pWord = pWord;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public boolean isHas_checkings() {
		return has_checkings;
	}

	public void setHas_checkings(boolean has_checkings) {
		this.has_checkings = has_checkings;
	}

	public boolean isHas_savings() {
		return has_savings;
	}

	public void setHas_savings(boolean has_savings) {
		this.has_savings = has_savings;
	}

	public double getChecking_balance() {
		return checking_balance;
	}

	public void setChecking_balance(double checking_balance) {
		this.checking_balance = checking_balance;
	}

	public double getSaving_balance() {
		return saving_balance;
	}

	public void setSaving_balance(double saving_balance) {
		this.saving_balance = saving_balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessType == null) ? 0 : accessType.hashCode());
		result = prime * result + accountID;
		long temp;
		temp = Double.doubleToLongBits(checking_balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + (has_checkings ? 1231 : 1237);
		result = prime * result + (has_savings ? 1231 : 1237);
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((pWord == null) ? 0 : pWord.hashCode());
		temp = Double.doubleToLongBits(saving_balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((uName == null) ? 0 : uName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accessType == null) {
			if (other.accessType != null)
				return false;
		} else if (!accessType.equals(other.accessType))
			return false;
		if (accountID != other.accountID)
			return false;
		if (Double.doubleToLongBits(checking_balance) != Double.doubleToLongBits(other.checking_balance))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (has_checkings != other.has_checkings)
			return false;
		if (has_savings != other.has_savings)
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (pWord == null) {
			if (other.pWord != null)
				return false;
		} else if (!pWord.equals(other.pWord))
			return false;
		if (Double.doubleToLongBits(saving_balance) != Double.doubleToLongBits(other.saving_balance))
			return false;
		if (uName == null) {
			if (other.uName != null)
				return false;
		} else if (!uName.equals(other.uName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", uName=" + uName + ", pWord=" + pWord + ", fname=" + fname
				+ ", lname=" + lname + ", accessType=" + accessType + ", has_checkings=" + has_checkings
				+ ", has_savings=" + has_savings + ", checking_balance=" + checking_balance + ", saving_balance="
				+ saving_balance + "]";
	}

	
	
	
	

	
	
	
}