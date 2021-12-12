package com.tms.floball.model

class FloballGoModelService: FloballService {

    override fun onGoClicked(question: String): Boolean = question.isValidQuestion()

    private fun String.isValidQuestion(): Boolean = !isNullOrEmpty()

}