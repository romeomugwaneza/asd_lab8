package bankwithadapter.domain;

import java.util.List;

public interface IAccountAdapter {
    AccountDTO getAccount(long accountNumber);
    List<AccountDTO> getAllAccounts();

}
