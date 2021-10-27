package com.kemalurekli.percentagecalculator.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.kemalurekli.percentagecalculator.databinding.FragmentHomeBinding
import com.kemalurekli.percentagecalculator.viewmodel.HomeFragmentViewModel

private const val INITIAL_PERCENT : Int = 30
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        binding.sbPercentageBar.progress = INITIAL_PERCENT
        binding.tvPercentageNum.text = "% $INITIAL_PERCENT"
        binding.sbPercentageBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvPercentageNum.text = "% $p1"
                calculatePercentage()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        binding.etNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                calculatePercentage()
            }
        })
    }
    private fun calculatePercentage() {
        if(binding.etNumber.text.isEmpty()){
            binding.tvTotalResultNum.text = ""
            binding.tvPercentageResult.text = ""
            return
        }
        //Get data from user
        val inputNumber : Double = binding.etNumber.text.toString().toDouble()
        val inputSeekBar : Int = binding.sbPercentageBar.progress
        // Calculation
        val calculatedPercent : Double = (inputSeekBar*inputNumber)/100
        val calculatedTotal : Double = inputNumber + calculatedPercent
        // Update UI
        binding.tvPercentageResult.text = "%.2f".format(calculatedPercent)
        binding.tvTotalResultNum.text = "%.2f".format(calculatedTotal)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}