package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;

public class ModuleList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private boolean AccountTransactionModule;
	private boolean EmailPhoneTransactionModule;
	private boolean AppointmentModule;
	private boolean RequestModule;
	private boolean ViewAccountModule;
	private boolean ViewAndModifyModule;
	private boolean ViewLogFile;
	private boolean BankingStatementModule;
	private boolean OpenAccount;
	public boolean isAccountTransactionModule() {
		return AccountTransactionModule;
	}
	public void setAccountTransactionModule(boolean accountTransactionModule) {
		AccountTransactionModule = accountTransactionModule;
	}
	public boolean isEmailPhoneTransactionModule() {
		return EmailPhoneTransactionModule;
	}
	public void setEmailPhoneTransactionModule(boolean emailPhoneTransactionModule) {
		EmailPhoneTransactionModule = emailPhoneTransactionModule;
	}
	public boolean isAppointmentModule() {
		return AppointmentModule;
	}
	public void setAppointmentModule(boolean appointmentModule) {
		AppointmentModule = appointmentModule;
	}
	public boolean isRequestModule() {
		return RequestModule;
	}
	public void setRequestModule(boolean requestModule) {
		RequestModule = requestModule;
	}
	public boolean isViewAccountModule() {
		return ViewAccountModule;
	}
	public void setViewAccountModule(boolean viewAccountModule) {
		ViewAccountModule = viewAccountModule;
	}
	public boolean isViewAndModifyModule() {
		return ViewAndModifyModule;
	}
	public void setViewAndModifyModule(boolean viewAndModifyModule) {
		ViewAndModifyModule = viewAndModifyModule;
	}
	public boolean isViewLogFile() {
		return ViewLogFile;
	}
	public void setViewLogFile(boolean viewLogFile) {
		ViewLogFile = viewLogFile;
	}
	public boolean isBankingStatementModule() {
		return BankingStatementModule;
	}
	public void setBankingStatementModule(boolean bankingStatementModule) {
		BankingStatementModule = bankingStatementModule;
	}
	public boolean isOpenAccount() {
		return OpenAccount;
	}
	public void setOpenAccount(boolean openAccount) {
		OpenAccount = openAccount;
	}
	}