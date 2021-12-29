package com.neobis.compositiongame.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameSettings (
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
): Parcelable