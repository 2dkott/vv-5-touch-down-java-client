package com.konivan.scenes;

import com.konivan.game_objects.GameObject;
import com.konivan.game_objects.MainShipObject;

import games.rednblack.editor.renderer.utils.ComponentRetriever;

import java.lang.reflect.InvocationTargetException;

public class TestScene extends BaseScene{

    private final GameObject mainShip;
    public TestScene() {
        super("TestScene");

        this.mainShip = findInScene(MainShipObject.class);
        mainShip.getComponents().forEach(ComponentRetriever::addMapper);

        gameService.getRender().getCameraSystem().setFocus(this.mainShip.getEntity());
    }

}
