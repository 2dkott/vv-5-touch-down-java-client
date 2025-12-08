package com.konivan.stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.konivan.scenes.LandingSceneObserver;
import com.konivan.scripts.ShipMovementScript;
import games.rednblack.editor.renderer.components.TransformComponent;

public class LandingHUD extends Stage {

    private final LandingSceneObserver observer;
    private final Vector2 platformPosition;
    private TextField altText;

    private float commonPadding;
    public LandingHUD(Skin skin, Viewport viewport, Batch batch, LandingSceneObserver observer) {
        super(viewport, batch);

        commonPadding = (float) viewport.getScreenWidth() / 40;

        this.observer = observer;
        float sectionWidth = (float) viewport.getScreenWidth() / 4;

        Table root = new Table();
        root.setFillParent(true);
        root.setDebug(true);

        float toolsAreaHeight = (float) viewport.getScreenHeight() / 20;
        altText = new TextField("", skin);
        altText.getStyle().font.getData().setScale((float) viewport.getScreenWidth() /500);
        altText.setDisabled(true);
        Label altTextLabel = new Label("alti: ", skin);
        Table toolsArea = new Table();
        toolsArea.setDebug(true);
        toolsArea.add(altTextLabel).left().center().padLeft(commonPadding).expandY();
        toolsArea.add(altText).center().expandY();

        root.add(toolsArea).height(toolsAreaHeight).left().expandX().colspan(2).row();

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

        var platformTransformComponent = observer.getLandingPlatform().getComponent(TransformComponent.class);
        platformPosition = new Vector2(0, platformTransformComponent.y);

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                observer.getPlayerShipEntity().getMovementScript().setBrakeForce(slider.getValue());
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        var shipTransformComponent = observer.getPlayerShipEntity().getComponent(TransformComponent.class);
        var shipPosition = new Vector2(0, shipTransformComponent.y);
        var distance = shipPosition.dst(platformPosition);
        altText.setText(String.format("%.1f", distance));

    }
}
