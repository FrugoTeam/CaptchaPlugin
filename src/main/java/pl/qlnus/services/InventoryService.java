package pl.qlnus.services;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class InventoryService {
    private final Set<UUID> uuids;

    public InventoryService() {
        this.uuids = new HashSet<>();
    }

    public void addUser(UUID uniqueId) {
        uuids.add(uniqueId);
    }

    public void removeUser(UUID uniqueId) {
        uuids.remove(uniqueId);
    }

    public boolean containsUser(UUID uniqueId) {
        return uuids.contains(uniqueId);
    }
}
