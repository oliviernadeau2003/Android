package ca.qc.cstj.premiereapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class GameActivity : AppCompatActivity() {

    private var nbTries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val txvWelcomePhrase = findViewById<TextView>(R.id.txvWelcomePhrase)
        val txvTries = findViewById<TextView>(R.id.txvTries)
        val nprNumber = findViewById<NumberPicker>(R.id.nprNumber)
        val btnValidate = findViewById<Button>(R.id.btnValidate)

        txvWelcomePhrase.text =
            getString(R.string.msg_Game, intent.getStringExtra(PLAYER_NAME_EXTRA))

        nprNumber.minValue = 0
        nprNumber.maxValue = 100

        val theGoodNumber = (0..100).random()

        btnValidate.setOnClickListener {
            nbTries++
            val choice = nprNumber.value

//            if (choice == theGoodNumber) {
//                Snackbar.make(btnValidate, "Bravo", Snackbar.LENGTH_INDEFINITE).show()
//            } else if (choice > theGoodNumber) {
//                Snackbar.make(btnValidate, "Le nombre recherché est plus petit", Snackbar.LENGTH_LONG).show()
//            } else {
//                Snackbar.make(btnValidate, "Le nombre recherché est plus grand", Snackbar.LENGTH_LONG).show()
//            }

            when{
                choice == theGoodNumber -> {
                    Snackbar.make(btnValidate, "Bravo", Snackbar.LENGTH_INDEFINITE).show()
                    finish()
                }
                choice > theGoodNumber -> {
                    Snackbar.make(btnValidate, "Le nombre recherché est plus petit", Snackbar.LENGTH_LONG).show()

                } else -> {
                    Snackbar.make(btnValidate, "Le nombre recherché est plus grand", Snackbar.LENGTH_LONG).show()
                }
            }



            txvTries.text = applicationContext.resources.getQuantityString(R.plurals.nbTries,nbTries,nbTries)

        }


    }

    //  La partie static Intent newIntent()
    companion object {
        const val PLAYER_NAME_EXTRA = "PLAYER_NAME"
        fun newIntent(context: Context, playerName: String): Intent {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(PLAYER_NAME_EXTRA, playerName)
            return intent
        }
    }


}