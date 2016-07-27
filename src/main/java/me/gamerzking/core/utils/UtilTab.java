package me.gamerzking.core.utils;

/**
 * Created by GamerzKing on 7/26/2016.
 */
public class UtilTab {

    /*

    public void sendTabHF(Player player, String header, String footer)
    {
        Object craftPlayer = getCraftPlayer (player);
        Object connection = getConnection (craftPlayer);
        try {
            Object headerJSON = chatSerA.invoke (header);
            Object footerJSON = chatSerA.invoke(footer);
            Object packet = getNMS ("PacketPlayOutPlayerListHeaderFooter").getConstructor ().newInstance ();
            Field headerField = packet.getClass ().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, headerJSON);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footerJSON);
            footerField.setAccessible(!footerField.isAccessible());

            connection.getClass ().getMethod ("sendPacket", packetClass).invoke (player, packet);
        }catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    */
}