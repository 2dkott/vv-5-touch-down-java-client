package com.konivan.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.konivan.scripts.ShipMovement;

public class LandingHUD extends Stage {
    public LandingHUD(Skin skin, Viewport viewport, Batch batch, ShipMovement shipMovement) {
        super(viewport, batch);

        float sectionWidth = (float) viewport.getScreenWidth() / 4;

        Table root = new Table();
        root.setFillParent(true);
        root.setDebug(true);

        Table toolsArea = new Table();
        toolsArea.setDebug(true);
        root.add(toolsArea).width(sectionWidth).expandY();

        Table mainArea = new Table();
        mainArea.setDebug(true);
        root.add(mainArea).expandX().expandY();

        Table enginArea = new Table();
        enginArea.setDebug(true);
        Slider slider = new Slider(0, 100, 1, true, skin);
        slider.setSize((float) viewport.getScreenWidth() / 5, (float) viewport.getScreenHeight() / 3);

        root.add(slider)
            .minHeight(Value.percentHeight(1f))
            .minWidth(Value.percentWidth(1.15f))
            .bottom()
            .padRight(Value.percentWidth(0.15f))
            .padBottom((float) viewport.getScreenHeight() / 5);

        addActor(root);

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                shipMovement.setBrakeForce(slider.getValue());
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
