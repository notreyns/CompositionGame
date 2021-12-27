package com.neobis.compositiongame.domain.usecases

import com.neobis.compositiongame.domain.entities.Question
import com.neobis.compositiongame.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository){
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }
    private companion object{
        private const val COUNT_OF_OPTIONS = 6
    }
}