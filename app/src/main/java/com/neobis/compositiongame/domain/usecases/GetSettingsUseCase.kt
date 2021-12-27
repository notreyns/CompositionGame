package com.neobis.compositiongame.domain.usecases

import com.neobis.compositiongame.domain.entities.GameSettings
import com.neobis.compositiongame.domain.entities.Level
import com.neobis.compositiongame.domain.repository.GameRepository

class GetSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}