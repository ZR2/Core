package me.gamerzking.core.account;

import me.gamerzking.core.account.repository.AccountRepository;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by GamerzKing on 6/6/2016.
 */
public class AccountManager {

    private AccountRepository repository;

    public AccountManager(JavaPlugin plugin) {

        repository = new AccountRepository(plugin);
    }

    public AccountRepository getRepository() {
        return repository;
    }
}