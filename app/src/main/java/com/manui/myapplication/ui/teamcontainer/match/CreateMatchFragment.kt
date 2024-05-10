package com.manui.myapplication.ui.teamcontainer.match

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manui.myapplication.databinding.FragmentCreateMatchBinding
import java.text.SimpleDateFormat
import java.util.Calendar


class CreateMatchFragment : Fragment() ,DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentCreateMatchBinding
    private val vm: MatchViewModel by viewModels()
//    private val args: CreatePlayerFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateMatchBinding.inflate(inflater, container, false).apply {
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
            vm.createMatch()
        }

        binding.date.setOnClickListener {
            showDatePickerDialog()
        }
    }

    fun showDatePickerDialog(){
        DatePickerDialog(
            context!!, this@CreateMatchFragment, 2000, 1, 1
        ).show()

    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        vm.changeDate(c.time)
    }
}