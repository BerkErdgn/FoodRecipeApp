package com.berke.foodrecipeapp.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.berke.foodrecipeapp.R


class EmblemFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emblem, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Ekranın 4 saniye sonra devam etmesi için
        val timer = object: CountDownTimer(4000,1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val actionEmblemToSplashScreen= EmblemFragmentDirections.actionEmblemFragmentToSplashScreenFragment()
                Navigation.findNavController(view).navigate(actionEmblemToSplashScreen)
            }

        }
        timer.start()

    }


}