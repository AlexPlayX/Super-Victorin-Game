package com.itGamePart.supervictoringame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.itGamePart.supervictoringame.R.drawable.*
import android.app.AlertDialog
import android.content.pm.ActivityInfo


class GameActivity : AppCompatActivity() {

    var score = 0
    var quewtionNum = 0
    val questionTextCon = arrayOf( arrayOf("Какой из скриншотов принадлежит серии игр GTA", "", "",gta_1, mafia_1, 1),
        arrayOf("Какая из этих игр даёт возможность почувствовать себя диктатором","","", simcity, tropica, 2),
        arrayOf("Какая из этих игр сделана в СНГ","S.T.A.L.K.E.R.","Fallout NW", stalker, fallout_nw,1),
        arrayOf("Какой из этих скриншотов принадлежит белорусской игре", "", "", war_thander,wot,2),
        arrayOf("Какой из скринов принадлежит великой серии игр про скрытных убийц", "", "", assasin_odeci, total_war,1),
        arrayOf("Какая из этих игр эксклюзив Microsoft", "", "", forza, gt,1),
        arrayOf("Недавно создатели Dark Souls III выпустили игру Sekiro, какой скрин принадлежит этой игре", "", "", sekira, nioh, 1),
        arrayOf("Про Skyrim говорят, что его излазали вдоль и поперёк, и узнают там каждый камень - проверим, сможете ли вы отгадать где горы из этого шедера", "", "", skyrim_stone, whitcher_stone,1),
        arrayOf("Немного классики, в каком из миров произошла ядерная война", "", "", fallout, diablo,1),
        arrayOf("В какой из игр больше эротических сцен", "Mass Effect", "The Witcher", masseffect, whitcer,2),
        arrayOf("Какая игра старше?", "Morowind", "Gothic", morrowind, gothic1,2),
        arrayOf("Выживаемость после какого ритуала выше", "Становлений Cерым Стражем", "Проверка травами", dragon_age, whitcer,1),
        arrayOf("На каком из скриншотов изображена игра из серии Tomb Raider", "", "", horizone, tomerader,2),
        arrayOf("Какой из этих монстров из Doom", "", "", serissem, doom,2),
        arrayOf("Какой скорпион из Mortal Kombat? ", "", "", mkx, injast,1)
    )


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
       // overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


        val questionText = findViewById<TextView>(R.id.textQuestionV)
        val leftText = findViewById<TextView>(R.id.leftTextG)
        val rightText = findViewById<TextView>(R.id.rightTextG)
        val scoreText = findViewById<TextView>(R.id.textViewScore)

        val leftButton = findViewById<Button>(R.id.leftButtonG)
        val rightButton = findViewById<Button>(R.id.rightButG)
        val backButton = findViewById<Button>(R.id.backButtonAct)

        scoreText.text = "Счёт : " + score.toString()
        questionText.text = (quewtionNum+1).toString() + ". " + questionTextCon[quewtionNum][0].toString()
        leftText.text = questionTextCon[quewtionNum][1].toString()
        rightText.text = questionTextCon[quewtionNum][2].toString()

        leftButton.background = getDrawable(questionTextCon[quewtionNum][3] as Int)
        rightButton.background = getDrawable(questionTextCon[quewtionNum][4] as Int)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        rightButton.setOnClickListener {

            if( questionTextCon[quewtionNum][5] == 2) {
                score += 1
            } else {
                score -=2
            }
            quewtionNum=quewtionNum + 1

            if (questionTextCon.size > quewtionNum) {
                updateScreen(scoreText,
                    questionText,
                    leftText,
                    rightText,
                    leftButton,
                    rightButton)
            }
            else {
                alertBild("Викторина окончена ","Твой счёт :  "+score.toString())
            }
        }
        leftButton.setOnClickListener {

                if (questionTextCon[quewtionNum][5] == 1) {
                    score += 1
                } else {
                    score -= 2
                }
                quewtionNum = quewtionNum + 1
                if (questionTextCon.size > quewtionNum) {
                    updateScreen(
                        scoreText,
                        questionText,
                        leftText,
                        rightText,
                        leftButton,
                        rightButton
                    )
                } else {
                    alertBild("Викторина окончена","Твой счёт :  "+score.toString())

                }
        }
    }

    fun updateScreen(scoreText:TextView, question:TextView, leftText:TextView, rightText:TextView,leftButton:Button,rightButton:Button){

        scoreText.text = "Счёт : " + score.toString()
        question.text = (quewtionNum+1).toString() + ". " + questionTextCon[quewtionNum][0].toString()
        leftText.text = questionTextCon[quewtionNum][1].toString()
        rightText.text = questionTextCon[quewtionNum][2].toString()
        leftButton.background = getDrawable(questionTextCon[quewtionNum][3] as Int)
        rightButton.background = getDrawable(questionTextCon[quewtionNum][4] as Int)
    }

    fun alertBild(titleText:String,messageText:String){
        val builder = AlertDialog.Builder(this)

        builder.setTitle(titleText)
        builder.setMessage(messageText)

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        builder.show()
    }
}
