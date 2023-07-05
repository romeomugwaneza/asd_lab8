package bankwithadapter.domain;

import bankwithadapter.service.AccountService;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter implements IAccountAdapter{
    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public AccountDTO getAccount(long accountNumber){
        Account account = accountService.getAccount(accountNumber);
        return createAccountDtoFromAccount(account);
    }

    public List<AccountDTO> getAllAccounts(){
        List<AccountDTO> accountDTOs = new ArrayList<>();
        var accounts = accountService.getAllAccounts();
        for (Account account : accounts)
            accountDTOs.add(createAccountDtoFromAccount(account));
        return accountDTOs;
    }
    private AccountDTO createAccountDtoFromAccount(Account account){
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setAccountnumber(account.getAccountnumber());
        accountDTO.setCustomer(account.getCustomer());
        accountDTO.setEntryList(account.getEntryList());
        accountDTO.setBalance(account.getBalance());

        return accountDTO;
    }
}
