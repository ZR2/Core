package me.gamerzking.core.tutorial;

/**
 * Created by GamerzKing on 6/19/2016.
 */
public class Tutorial {

    private String name;
    private int coins;

    private String header;
    private String message;

    private int i;


    /**
     * @param name The name of the tutorial.
     * @param coins How many coins will be rewarded to the player.
     */

    public Tutorial(String name, int coins) {

        this.name = name;
        this.coins = coins;
    }
}