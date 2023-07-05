package bankwithadapter.service;

import bankwithadapter.dao.AccountDAO;
import bankwithadapter.dao.IAccountDAO;
import bankwithadapter.domain.Account;
import bankwithadapter.domain.Customer;
import bankwithadapter.domain.proxies.LoggingProxy;
import bankwithadapter.domain.proxies.StopWatchProxy;

import java.lang.reflect.Proxy;
import java.util.Collection;


public class AccountService implements IAccountService {
	IAccountDAO accountDAO = new AccountDAO();
	ClassLoader classLoader = AccountDAO.class.getClassLoader();
	IAccountDAO loggingProxy = (IAccountDAO) Proxy.newProxyInstance(
			classLoader,
			new Class[] {IAccountDAO.class},
			new LoggingProxy(accountDAO)
	);
	IAccountDAO stopWatchProxy = (IAccountDAO) Proxy.newProxyInstance(
			classLoader,
			new Class[] {IAccountDAO.class},
			new StopWatchProxy(loggingProxy)
	);

	public Account createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		stopWatchProxy.saveAccount(account);
		return account;
	}

	public void deposit(long accountNumber, double amount) {
		Account account = stopWatchProxy.loadAccount(accountNumber);
		account.deposit(amount);
		stopWatchProxy.updateAccount(account);
	}

	public Account getAccount(long accountNumber) {
		Account account = stopWatchProxy.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return stopWatchProxy.getAccounts();
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = stopWatchProxy.loadAccount(accountNumber);
		account.withdraw(amount);
		stopWatchProxy.updateAccount(account);
	}



	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = stopWatchProxy.loadAccount(fromAccountNumber);
		Account toAccount = stopWatchProxy.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		stopWatchProxy.updateAccount(fromAccount);
		stopWatchProxy.updateAccount(toAccount);
	}
}
