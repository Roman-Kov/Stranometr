package com.rojer_ko.stranometr.presentation.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rojer_ko.stranometr.R
import kotlinx.android.synthetic.main.fragment_current_game.*

class CurrentGameFragment : Fragment() {

    companion object {
        private const val ROUND_TIME_TOTAL: Long = 30000 // milliseconds
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timer_textview.text = getTimeString(ROUND_TIME_TOTAL)
    }

    override fun onStart() {
        super.onStart()
        val mp = MediaPlayer.create(context, R.raw.finish)
        startTimer(mp)
    }

    private fun startTimer(mp: MediaPlayer) {

        round_start_btn.setOnClickListener {
            if (mp.isPlaying){
                mp.pause()
                mp.seekTo(0)
            }
            timer_textview.text = getTimeString(ROUND_TIME_TOTAL)
            round_start_btn.isEnabled = false
            val timer = object : CountDownTimer(ROUND_TIME_TOTAL, 1000) {
                override fun onTick(time: Long) {
                    timer_textview.text = getTimeString(time)
                }

                override fun onFinish() {
                    mp.start()
                    round_start_btn.isEnabled = true
                }
            }
            timer.start()
        }
    }

    private fun getTimeString(time: Long): String {
        val roundSec = (time / 1000) % 60
        val roundMin = (time / 1000 - roundSec) / 60
        return "$roundMin:$roundSec".takeIf { roundSec > 9 } ?: "$roundMin:0$roundSec"
    }
}