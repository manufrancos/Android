package com.manui.myapplication.ui.splashfragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.manui.myapplication.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            val info = requireContext().packageManager.getPackageInfo(requireContext().packageName, 0)
            version.text =info.versionName
        }

        subscribeUi()


        return binding.root
    }

    private fun subscribeUi() {
        val timer = object : CountDownTimer(1500, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToTeamFragment())
            }
        }
        timer.start()
    }
}