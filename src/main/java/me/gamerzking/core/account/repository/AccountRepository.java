package me.gamerzking.core.account.repository;

/**
 * Created by GamerzKing on 6/6/2016.
 */
public class AccountRepository {

    private static String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS accounts (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(100), name VARCHAR(40), coins INT, rank VARCHAR(40), firstLogin LONG, lastLogin LONG, totalPlayTime LONG, PRIMARY KEY (id));";
    private static String CREATE_ACCOUNT_PROFILE =  "INSERT INTO accounts (uuid, name, firstLogin, lastLogin) values(?, ?, now(), now());";
}