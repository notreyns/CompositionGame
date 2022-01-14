package com.neobis.compositiongame.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.neobis.compositiongame.R
import com.neobis.compositiongame.domain.entities.GameResult


@BindingAdapter("requiredCount")
fun bindRequiredQuestions(text: TextView, count: Int) {
    text.text = String.format(
        text.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(text: TextView, perc: Int) {
    text.text = String.format(
        text.context.getString(R.string.required_percentage),
        perc
    )
}

@BindingAdapter("score")
fun bindScore(text: TextView, score: Int) {
    text.text = String.format(
        text.context.getString(R.string.score_answers),
        score
    )
}
@BindingAdapter("resultEmoji")
fun bindImg(image: ImageView, winner: Boolean){
    val imageRes = if (winner) {
        R.drawable.ic_smile
    } else R.drawable.ic_sad
    image.setImageResource(imageRes)
}

@BindingAdapter("percent")
fun bindPercent(text: TextView, result: GameResult) {
    text.text = String.format(
        text.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(result)
    )
}

private fun getPercentOfRightAnswers(result: GameResult) = with(result) {
    if (countOfRightAnswers == 0) 0
    else ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
}