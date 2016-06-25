package me.gamerzking.core.account.repository;

/**
 * Created by GamerzKing on 6/6/2016.
 */
public class AccountRepository {

    private static String CREATE_ACCOUNT_TABLE = "CREATE TABLE `accounts` (`id` INT NOT NULL AUTO_INCREMENT,`uuid` VARCHAR(50) NULL DEFAULT NULL,`coins` INT(11) NULL DEFAULT NULL,`rank` VARCHAR(50) NULL DEFAULT NULL,`firstLogin` TIMESTAMP NULL DEFAULT NULL,`lastLogin` TIMESTAMP NULL DEFAULT NULL,`totalTime` TIMESTAMP NULL DEFAULT NULL, INDEX `id` (`id`));";
    private static String CREATE_ACCOUNT_PROFILE =  "INSERT INTO accounts (uuid, name, firstLogin, lastLogin) VALUES (?, ?, now(), now());";
}