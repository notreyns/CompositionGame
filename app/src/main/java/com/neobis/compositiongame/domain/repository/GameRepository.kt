package com.neobis.compositiongame.domain.repository

import com.neobis.compositiongame.domain.entities.GameSettings
import com.neobis.compositiongame.domain.entities.Level
import com.neobis.compositiongame.domain.entities.Question

interface GameRepository {

    fun getGameSettings(lever: Level): GameSettings

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question
}