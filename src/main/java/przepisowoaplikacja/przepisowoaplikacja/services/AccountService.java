package przepisowoaplikacja.przepisowoaplikacja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.repositories.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account){
        return  accountRepository.save(account);
    }


    public Optional<Account> findByEmail(String email){
        return  accountRepository.findByEmail(email);
    }

}
