package com.manui.myapplication.ui.teamcontainer.players

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.manui.myapplication.databinding.FragmentCreatePlayerBinding
import java.util.Calendar


class CreatePlayerFragment : Fragment() ,DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentCreatePlayerBinding
    private val vm: PlayerViewModel by viewModels()
    private val args: CreatePlayerFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePlayerBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm
            date.setOnClickListener {
                showDatePickerDialog()
            }
        }

        subscribeUI()
        return binding.root
    }

    fun subscribeUI() {
        binding.addPlayer.setOnClickListener {
            vm.createPlayer(args.idTeam)
        }
    }

    fun showDatePickerDialog(){
        DatePickerDialog(
            context!!, this@CreatePlayerFragment, 2000, 1, 1
        ).show()

        vm.playerReady.observe(viewLifecycleOwner, Observer {
            requireActivity().onBackPressed()
        })
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        vm.changeDateBirthDay(c.time)
    }
}