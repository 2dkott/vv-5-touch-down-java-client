package com.konivan;

import com.konivan.component.MainShipComponent;
import com.konivan.component.ShipComponent;
import games.rednblack.editor.renderer.utils.ComponentRetriever;

public class ComponentHandler {
    public static void addAllComponents() {
        ComponentRetriever.addMapper(MainShipComponent.class);
        ComponentRetriever.addMapper(ShipComponent.class);
    }
}
