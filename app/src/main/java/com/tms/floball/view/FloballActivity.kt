package com.tms.floball.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.tms.floball.R
import java.util.ArrayList

class FloballActivity : AppCompatActivity() {

    private lateinit var viewModel: FloballViewModel
    private lateinit var question: TextInputLayout
    private lateinit var goButton: AppCompatButton
    private lateinit var resultField: AppCompatTextView

    private val answer: ArrayList<String> =
        arrayListOf("Yes!", "No!", "Might!", "Probably not!", "Maybe...")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[FloballViewModel::class.java]
        question = findViewById(R.id.textField)
        goButton = findViewById(R.id.appCompatButton)
        resultField = findViewById(R.id.result)

        goButton.setOnClickListener {
            val questionText: String = question.editText?.text.toString().questionCorrection()
            question.editText?.setText(questionText)
            viewModel.onGoClicked(questionText)
        }

        subscribeOnLiveData()
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeOnLiveData() {
        viewModel.isQuestionSuccessLiveData.observe(this, {
            resultField.text = answer.shuffled().first()
            showAnswerField()
        })

        viewModel.isQuestionFailedLiveData.observe(this, {
            resultField.text = "Wrong question!"
            showAnswerField()
        })
    }

    private fun String.questionCorrection(): String = if (this.isNotEmpty()) {
        if (this.last() != '?') {
            this[0].uppercaseChar() + this.substring(1) + "?"
        } else {
            this[0].uppercaseChar() + this.substring(1)
        }
    } else {
        this
    }

    private fun showAnswerField() {
        resultField.isVisible = true
    }
}