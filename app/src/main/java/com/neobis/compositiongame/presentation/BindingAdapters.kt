package com.neobis.compositiongame.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.neobis.compositiongame.R
import com.neobis.compositiongame.domain.entities.GameResult

interface OnOptionClickListener {

    fun onOptionClick(option: Int)
}


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

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}
