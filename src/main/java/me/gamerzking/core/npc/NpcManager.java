package me.gamerzking.core.npc;

import me.gamerzking.core.npc.repository.NpcRepository;
import org.bukkit.entity.NPC;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by GamerzKing on 6/24/2016.
 */
public class NpcManager {

    private NpcRepository repository;

    private Map<UUID, NPC> npcMap;
    private Set<UUID> removing = new HashSet<>();

    public NpcManager(JavaPlugin plugin) {

        repository = new NpcRepository(plugin);
    }

    public NpcRepository getRepository() {
        return repository;
    }
}