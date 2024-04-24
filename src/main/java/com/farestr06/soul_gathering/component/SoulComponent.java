package com.farestr06.soul_gathering.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;

public interface SoulComponent extends Component, CommonTickingComponent {
    int getSoulCount();
    void setSoulCount(int soulCount);
    void addSouls(int amount);
    void removeSouls(int amount);


}
