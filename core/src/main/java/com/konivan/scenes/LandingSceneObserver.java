package com.konivan.scenes;

import com.konivan.game_objects.GameEntity;
import com.konivan.game_objects.PlayerShipEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LandingSceneObserver {
    private PlayerShipEntity playerShipEntity;
    private GameEntity landingPlatform;
}
