package com.example.wordle
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.jinatonic.confetti.ConfettiManager
import com.github.jinatonic.confetti.ConfettiView


class MainActivity : AppCompatActivity() {

    var wordToGuess = FourLetterWordList().getRandomFourLetterWord()
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // for debugging
       var textView14 = findViewById<TextView>(R.id.textView14)
//        textView14.text = wordToGuess

        //edittext
        val simpleTextEdit = findViewById<EditText>(R.id.text_entry)

        //buttons
        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)

        // declaration for textViews
        // first line
        val textView = findViewById<TextView>(R.id.textView)
        val textView7 = findViewById<TextView>(R.id.textView7)
        //second line
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView8 = findViewById<TextView>(R.id.textView8)
        //third line
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView9 = findViewById<TextView>(R.id.textView9)

        //fourth line
        val textView4 = findViewById<TextView>(R.id.textView4)
        val textView10 = findViewById<TextView>(R.id.textView10)

        //fifth line
        val textView5 = findViewById<TextView>(R.id.textView5)
        val textView11 = findViewById<TextView>(R.id.textView11)

        //sixth line
        val textView6 = findViewById<TextView>(R.id.textView6)
        val textView12 = findViewById<TextView>(R.id.textView12)

        val textView13 = findViewById<TextView>(R.id.textView13)


// when it is submitted it will pop up in the screen what word is submitted

        button1.setOnClickListener {
            val guess = simpleTextEdit.text.toString().uppercase()
            simpleTextEdit.onEditorAction(EditorInfo.IME_ACTION_DONE)
            simpleTextEdit.setText(null);
            counter++
            when (counter) {
                1 -> {
                    textView.visibility = View.VISIBLE
                    textView7.visibility = View.VISIBLE
                    textView7.text = guess
                    textView2.visibility = View.VISIBLE
                    val check1 = checkGuess(guess)
                    textView8.visibility = View.VISIBLE
                    textView8.text = check1

                }
                2 -> {
                    textView3.visibility = View.VISIBLE
                    textView9.visibility = View.VISIBLE
                    textView9.text = guess

                    textView4.visibility = View.VISIBLE
                    val check2 = checkGuess(guess)
                    textView10.visibility = View.VISIBLE
                    textView10.text = check2

                }
                3 -> {
                    textView5.visibility = View.VISIBLE
                    textView11.visibility = View.VISIBLE
                    textView11.text = guess

                    textView6.visibility = View.VISIBLE
                    val check3 = checkGuess(guess)
                    textView12.visibility = View.VISIBLE
                    textView12.text = check3

                }

            }
            if (guess == wordToGuess) {
                Toast.makeText(
                    getApplicationContext(),
                    "Congratulations! You did it!",
                    Toast.LENGTH_LONG
                ).show()

                // Hide upgrade button again
                button1.visibility = View.INVISIBLE
                button2.visibility = View.VISIBLE
                textView13.visibility = View.VISIBLE
                textView13.text = "Wanna play again press Reset "


            }
            if (counter > 2) {
                // Hide upgrade button again
                textView14.visibility = View.VISIBLE
                textView13.visibility = View.VISIBLE
                textView14.text = "Correct Answer: $wordToGuess"
                button1.visibility = View.INVISIBLE
                button2.visibility = View.VISIBLE
                textView13.text = "Want to restart press Reset "
            }

        }

// for Reset button
        button2.setOnClickListener {
            textView13.visibility = View.INVISIBLE
            textView14.visibility = View.INVISIBLE
            if (counter < 2) {
                Toast.makeText(
                    getApplicationContext(),
                    "Congratulations! You did it!",
                    Toast.LENGTH_LONG
                ).show()

            }

            wordToGuess = FourLetterWordList().getRandomFourLetterWord()
            counter = 0
            // for debugging
//            textView14 = findViewById<TextView>(R.id.textView14)


            button1.visibility = View.VISIBLE
            button2.visibility = View.INVISIBLE

            //turn all the textviews invisible
            textView.visibility = View.INVISIBLE
            textView7.visibility = View.INVISIBLE
            textView2.visibility = View.INVISIBLE
            textView8.visibility = View.INVISIBLE
            textView3.visibility = View.INVISIBLE
            textView9.visibility = View.INVISIBLE
            textView4.visibility = View.INVISIBLE
            textView10.visibility = View.INVISIBLE
            textView5.visibility = View.INVISIBLE
            textView11.visibility = View.INVISIBLE
            textView6.visibility = View.INVISIBLE
            textView12.visibility = View.INVISIBLE

        }

    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */


    private fun checkGuess(guess: String): SpannableStringBuilder {
        // for color
        val coloredchar = SpannableStringBuilder("")
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                val charToString1 = SpannableStringBuilder(guess[i].toString())
                charToString1.setSpan(
                    ForegroundColorSpan(Color.GREEN),
                    0,
                    1,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                coloredchar.append(charToString1)
            } else if (guess[i] in wordToGuess) {
                val charToString2 = SpannableStringBuilder(guess[i].toString())
                charToString2.setSpan(
                    ForegroundColorSpan(Color.BLUE), 0, 1,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                coloredchar.append(charToString2)
            } else {
                val charToString3 = SpannableStringBuilder(guess[i].toString())
                charToString3.setSpan(
                    ForegroundColorSpan(Color.RED), 0, 1,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                coloredchar.append(charToString3)
            }
        }
        return coloredchar
    }
}



