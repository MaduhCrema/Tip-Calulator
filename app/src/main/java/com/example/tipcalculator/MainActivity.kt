package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

//Configuração para usar a vinculação de visualizações
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //define o que acontece quando clica no botão
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()

        /** Precisa converter o terxt para string, para conerter em double**/
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text =
                "" //para limpar a string caso o usuario toque em calculate de novo
            return //sai do metodo
        }
        /** Quando uma das opções estiverem selecionadas vai atribuir um valor**/
        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_porcent_tip_option -> 0.20
            R.id.eighteen_porcent_tip_option -> 0.18
            else -> 0.15
        }
        var tip = tipPercent * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        // formata o valor para uma moeda
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amout, formattedTip)
    }
}
