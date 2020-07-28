package com.example.ticktacktoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.children
import androidx.core.view.iterator
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var jugador = true
    var tabler = IntArray(9)
    var guanyadesJugador1 = 0
    var guanyadesJugador2 = 0
    var guanyador = false
    var movimentsFets = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun moveDone(view: View) {
        val botoPolsat = view as Button

        //No està ocupada
        if(botoPolsat.text != "X" && botoPolsat.text != "O" && !guanyador) {
            movimentsFets++
            //Posem al tauler que eixa posició l'ocupa eixe jugador
            if(jugador) tabler[botoPolsat.text.toString().toInt()] = 1
            else tabler[botoPolsat.text.toString().toInt()] = 2

            println(tabler.contentToString())
            botoPolsat.setTextColor(Color.parseColor("#000000"))
            if (jugador) botoPolsat.text = "X"
            else botoPolsat.text = "O"

            comprovacioQuiGuanya()

            //canviem de jugador
            if(!guanyador) {
                jugador = !jugador
                if (jugador) tornJugador.text = "Torn del jugador 1"
                else tornJugador.text = "Torn del jugador 2"
            }
        }

    }

    fun onReiniciarTot(view: View) {
        onReiniciarTabler(view)

        tabler.fill(0)

        jugador = true
        tornJugador.text = "Torn del jugador 1"
        guanyadesJugador1 = 0
        guanyadesJugador2 = 0
        textJugador1.text = "Jugador 1:        $guanyadesJugador1"
        textJugador2.text = "Jugador 2:        $guanyadesJugador2"
    }

    fun onReiniciarTabler(view: View) {
        var tornarAlLloc = 0
        movimentsFets = 0
        guanyador = false
        for(x in linearOne){
            (x as Button).setTextColor(Color.parseColor("#E4D7D7"))
            x.text = "$tornarAlLloc"
            tornarAlLloc++
        }

        for(x in linearTwo){
            (x as Button).setTextColor(Color.parseColor("#E4D7D7"))
            x.text = "$tornarAlLloc"
            tornarAlLloc++
        }

        for(x in linearThree){
            (x as Button).setTextColor(Color.parseColor("#E4D7D7"))
            x.text = "$tornarAlLloc"
            tornarAlLloc++
        }

        tabler.fill(0)

        jugador = true
        tornJugador.text = "Torn del jugador 1"

    }

    private fun comprovacioQuiGuanya() {
        //Verticals i horitzontals
        if((tabler[0] == tabler[1] && tabler[0] == tabler[2] && tabler[0] != 0) ||
            ((tabler[3] == tabler[4] && tabler[3] == tabler[5] && tabler[3] != 0)) ||
            ((tabler[6] == tabler[7] && tabler[6] == tabler[8] && tabler[6] != 0))||
            ((tabler[0] == tabler[3] && tabler[0] == tabler[6] && tabler[0] != 0)) ||
            ((tabler[1] == tabler[4] && tabler[1] == tabler[7] && tabler[1] != 0)) ||
            ((tabler[2] == tabler[5] && tabler[2] == tabler[8] && tabler[2] != 0)) ||
            //Diagonals
            ((tabler[0] == tabler[4] && tabler[0] == tabler[8] && tabler[0] != 0)) ||
            ((tabler[2] == tabler[4] && tabler[2] == tabler[6] && tabler[2] != 0)))
        {
            guanyador = true

            if(jugador) {
                tornJugador.text = "HA GUANYAT EL JUGADOR 1"
                guanyadesJugador1++
                textJugador1.text = "Jugador 1:        $guanyadesJugador1"
            }
            else {
                tornJugador.text = "HA GUANYAT EL JUGADOR 2"
                guanyadesJugador2++
                textJugador2.text = "Jugador 2:        $guanyadesJugador2"
            }
        }

        if(movimentsFets == 9 && !guanyador) {
            guanyador = true
            tornJugador.text = "EMPAT"
        }
    }
}