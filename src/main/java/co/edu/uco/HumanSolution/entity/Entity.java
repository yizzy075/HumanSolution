package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public abstract class Entity {

    private UUID id;

    protected Entity(UUID id) {
        setId(id);
    }

    protected Entity() {
        setId(UUIDHelper.getDefaultUUID());
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefaultUUID());
    }
}