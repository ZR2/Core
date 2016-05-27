package me.gamerzking.core.punishment;

/**
 * Created by GamerzKing on 5/18/2016.
 */
public class Punishment {

    private int id;
    private PunishmentType type;
    private String reason;
    private String punisher;
    private long time;

    public Punishment(int id, PunishmentType type, String reason, String punisher, long time) {

        this.id = id;
        this.type = type;
        this.reason = reason;
        this.punisher = punisher;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public PunishmentType getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public String getPunisher() {
        return punisher;
    }

    public long getTime() {
        return time;
    }
}