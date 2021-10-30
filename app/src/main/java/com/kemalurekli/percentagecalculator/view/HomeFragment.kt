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
        binding.etPercentageNum.hint = "$INITIAL_PERCENT"
        binding.sbPercentageBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.etPercentageNum.hint = "$p1"
                calculatePercentage()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
                binding.etPercentageNum.text.clear()
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        binding.etNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (binding.etNumber.text.toString() =="."){
                    binding.etNumber.text.clear()
                    return
                }
                calculatePercentage()
            }
        })
        binding.etPercentageNum.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (binding.etPercentageNum.text.toString() =="."){
                    binding.etPercentageNum.text.clear()
                    return
                }
                if (!binding.etPercentageNum.text.isEmpty()){
                    binding.sbPercentageBar.progress = p0.toString().toDouble().toInt()
                    calculatePercentageWithUserInput()
                }
                calculatePercentageWithUserInput()
            }
        })
    }
    private fun calculatePercentageWithUserInput() {
        if(binding.etNumber.text.isEmpty() || binding.etPercentageNum.text.isEmpty()){
            binding.tvTotalResultNum.text = "Enter a value"
            binding.tvPercentageResult.text = "0"
            binding.etPercentageNum.hint = "0"
            return
        }
        //Get data from user
        val inputNumber : Double = binding.etNumber.text.toString().toDouble()
        val inputPercent : Double = binding.etPercentageNum.text.toString().toDouble()
        // Calculation
        val calculatedPercent : Double = (inputPercent*inputNumber)/100
        val calculatedTotal : Double = inputNumber + calculatedPercent
        // Update UI
        binding.tvPercentageResult.text = "%.2f".format(calculatedPercent)
        binding.tvTotalResultNum.text = "%.2f".format(calculatedTotal)
        binding.tvExplanation.text = "%$inputPercent of $inputNumber is ${binding.tvPercentageResult.text} and total value is ${binding.tvTotalResultNum.text}"    }
    private fun calculatePercentage() {
        if(binding.etNumber.text.isEmpty()){
            binding.tvTotalResultNum.text = "Enter a value"
            binding.tvPercentageResult.text = "0"
            return
        }
        //Get data from user
        val inputNumber : Double = binding.etNumber.text.toString().toDouble()
        val inputSeekBar : Int = binding.sbPercentageBar.progress
        // Calculation
        val calculatedPercentageFromVM = viewModel.calculatePercentage(inputNumber,inputSeekBar)
        val calculatedResultFromVM = viewModel.calculateResult(inputNumber,inputSeekBar)
        // Update UI
        binding.tvPercentageResult.text = "%.2f".format(calculatedPercentageFromVM)
        binding.tvTotalResultNum.text = "%.2f".format(calculatedResultFromVM)
        binding.tvExplanation.text = "%$inputSeekBar of $inputNumber is ${binding.tvPercentageResult.text} and total value is ${binding.tvTotalResultNum.text}"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}