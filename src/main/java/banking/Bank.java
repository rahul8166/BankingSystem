package banking;

import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;
	private Long accountNumberCount = 0L;

	public Bank() {
		accounts = new LinkedHashMap<>();
	}

	private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		Long newAccountNumber = this.accountNumberCount++;
		Account commercialAccount = new CommercialAccount(company, newAccountNumber, pin, startingDeposit);
		accounts.put(newAccountNumber, commercialAccount);
        return newAccountNumber;
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		Long newAccountNumber = this.accountNumberCount++;
		Account consumerAccount = new ConsumerAccount(person, newAccountNumber, pin, startingDeposit);
		accounts.put(newAccountNumber, consumerAccount);
		return newAccountNumber;
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		return this.accounts.get(accountNumber).validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
        return this.accounts.get(accountNumber).getBalance();
	}

	public void credit(Long accountNumber, double amount) {
		this.accounts.get(accountNumber).creditAccount(amount);
	}

	public boolean debit(Long accountNumber, double amount) {
		if(this.accounts.get(accountNumber).getBalance() >= amount){
			this.accounts.get(accountNumber).debitAccount(amount);
			return true;
		}
		return false;
	}
}
