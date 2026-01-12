package com.konivan.stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.konivan.scenes.LandingSceneObserver;
import games.rednblack.editor.renderer.components.TransformComponent;

public class LandingHUD extends Stage {

    private static final float sliderMaxValue = 100f;
    private static final float sliderMinValue = 0f;
    private static final float sliderStep = 1f;
    private static final float sliderMinHeight = 1f;
    private static final float sliderMinWidth = 1.15f;
    private static final float sliderPadRight = 0.15f;
    private final LandingSceneObserver observer;
    private final Vector2 platformPosition;
    private TextField altText;

    public LandingHUD(Skin skin, Viewport viewport, Batch batch, LandingSceneObserver observer) {
        super(viewport, batch);

        float commonPadding = (float) viewport.getScreenWidth() / 40;
        float toolsAreaHeight = (float) viewport.getScreenHeight() / 20;
        float toolsTextScale = (float) viewport.getScreenWidth() /500;
        float sliderWidth = (float) viewport.getScreenWidth() / 5;
        float sliderHeight = (float) viewport.getScreenHeight() / 3;
        float sliderPadBottom = (float) viewport.getScreenHeight() / 5;

        this.observer = observer;

        Table root = new Table();
        root.setFillParent(true);
        root.setDebug(true);


        altText = new TextField("", skin);
        altText.getStyle().font.getData().setScale(toolsTextScale);
        altText.setDisabled(true);
        Label altTextLabel = new Label("alti: ", skin);
        Table toolsArea = new Table();
        root.add(toolsArea).height(toolsAreaHeight).left().center().fillX().expandX().colspan(2).row();
        toolsArea.setDebug(true);
        toolsArea.add(altTextLabel).left().center().padLeft(commonPadding).expandY();
        toolsArea.add(altText).center().expandY().fillX().expandX();

        Table mainArea = new Table();
        mainArea.setDebug(true);
        root.add(mainArea).expandX().expandY();

        Table enginArea = new Table();
        enginArea.setDebug(true);
        Slider slider = new Slider(sliderMinValue, sliderMaxValue, sliderStep, true, skin);
        slider.setSize(sliderWidth, sliderHeight);

        root.add(slider)
            .minHeight(Value.percentHeight(sliderMinHeight))
            .minWidth(Value.percentWidth(sliderMinWidth))
            .bottom()
            .padRight(Value.percentWidth(sliderPadRight))
            .padBottom(sliderPadBottom);

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
