package com.konivan.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.konivan.game_objects.PlayerShipEntity;
import com.konivan.stage.LandingHUD;
import com.konivan.static_data.EntityNames;
import com.konivan.static_data.HudSource;
import com.konivan.static_data.ships.ShipSkins;

public class TestScene extends BaseScene {

    private PlayerShipEntity mainShip;
    private LandingHUD hud;

    public TestScene() {
        super("TestScene");

        try {

            this.mainShip = buildFromScene(PlayerShipEntity.class, EntityNames.PLAYER_SHIP_NAME_ID);
            addResoucrecFromLibraryToParent(mainShip.getItemWrapper(), ShipSkins.DEFAULT_SHIP.getGameSkin());

            gameService.getRender().getCameraSystem().setFocus(this.mainShip.getEntity());

            var landingPlatform = buildFromScene(EntityNames.LANDING_ZONE_NAME_ID);

            var landingSceneObserver = LandingSceneObserver.builder()
                    .playerShipEntity(this.mainShip)
                    .landingPlatform(landingPlatform)
                    .build();

            Skin skin = gameService.getAssetManager().get(HudSource.landingHUDSkinPath);
            skin.addRegions(gameService.getAssetManager().get(HudSource.landingHUDAtlasPath));

            hud = new LandingHUD(
                    skin,
                    new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()),
                    gameService.getSceneLoader().getBatch(),
                    landingSceneObserver);

            Gdx.input.setInputProcessor(hud);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        hud.act(Gdx.graphics.getDeltaTime());
        hud.draw();
    }
}
