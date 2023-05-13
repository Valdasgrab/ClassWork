package lt.vgrabauskas.classwork

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : ActivityLifecycles() {

    lateinit var idEditText: EditText
    lateinit var text01EditText: EditText
    lateinit var text02EditText: EditText
    lateinit var text03EditText: EditText
    lateinit var closeButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        text03EditText = findViewById(R.id.text03EditText)
        closeButton = findViewById(R.id.closeButton)

        getIntentExtra()

        setClickListenerOfCloseButton()
    }



    private fun getIntentExtra() {
        idEditText.setText(
            intent.getIntExtra(Employees.MAIN_ACTIVITY_ITEM_ID, -1).toString()
        )
        text01EditText.setText(
            intent.getStringExtra(Employees.MAIN_ACTIVITY_ITEM_TEXT01)
        )
        text02EditText.setText(
            intent.getStringExtra(Employees.MAIN_ACTIVITY_ITEM_TEXT02)
        )
        text03EditText.setText(
            intent.getStringExtra(Employees.MAIN_ACTIVITY_ITEM_TEXT02)
        )
    }
    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            val finishIntent = Intent()
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT03, text03EditText.text.toString())
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }

    companion object {
        const val SECOND_ACTIVITY_ITEM_ID = "package lt.vcs.androidtopics.secondactivity_item_id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 = "package lt.vcs.androidtopics.secondactivity_item_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 = "package lt.vcs.androidtopics.secondactivity_item_text02"
        const val SECOND_ACTIVITY_ITEM_TEXT03 = "package lt.vcs.androidtopics.secondactivity_item_text03"
    }
}