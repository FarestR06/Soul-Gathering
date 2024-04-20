package com.farestr06.soul_gathering.component;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface SoulComponent extends Component {
    int getSoulCount();
    void addSouls(int amount);
    void removeSouls(int amount);
}
