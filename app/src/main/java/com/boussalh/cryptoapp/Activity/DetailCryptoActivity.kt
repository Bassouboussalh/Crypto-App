package com.boussalh.cryptoapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import com.boussalh.cryptoapp.Model.CryptoModel
import com.boussalh.cryptoapp.R
import com.boussalh.cryptoapp.databinding.ActivityDetailCryptoBinding
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class DetailCryptoActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailCryptoBinding
    private lateinit var item: CryptoModel
    var formatter: DecimalFormat = DecimalFormat("###,###,###.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCryptoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        getBundle()
        orderType()
        setVariable()
    }

    private fun setVariable() {
        binding.buyPositionBtn.setOnClickListener {
            binding.buyPositionBtn.setBackgroundResource(R.drawable.green_bg)
            binding.sellPositionBtn.setBackgroundResource(R.drawable.semi_withe_bg)
            binding.sendOrderBtn.setBackgroundResource(R.drawable.green_bg)
            binding.sendOrderBtn.setText("Buy " + item.ShortSymbol.replace("/USDT",""))
        }
        binding.sellPositionBtn.setOnClickListener {
            binding.buyPositionBtn.setBackgroundResource(R.drawable.semi_withe_bg)
            binding.sellPositionBtn.setBackgroundResource(R.drawable.red_bg)
            binding.sendOrderBtn.setBackgroundResource(R.drawable.red_bg)
            binding.sendOrderBtn.setText("Sell " + item.ShortSymbol.replace("/USDT",""))
        }
        binding.plusAmountBtn.setOnClickListener {
            if (binding.amountEdt.text.isEmpty()) {
                binding.amountEdt.setText("0")
            }
            var n: Double = binding.amountEdt.text.toString().toDouble()
            n = n + 1
            binding.amountEdt.setText(n.toString())
        }

        binding.minusAmountBtn.setOnClickListener {
            if (binding.amountEdt.text.isEmpty()){
                binding.amountEdt.setText("0")
            }
            var n:Double = binding.amountEdt.text.toString().toDouble()
            if (n>0){
                n=n-1
                binding.amountEdt.setText(n.toString())
            }
        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.symbolNameTxt.text = item.ShortSymbol
        binding.priceTxt.text = item.Price.toString()
        binding.changePercentTxt.text = item.ChangePercent.toString() + "$"
        binding.pSellTxt.text = formatter?.format(item.SellPrice1) ?: "0"
        binding.pSellTxt2.text = formatter?.format(item.SellPrice2) ?: "0"
        binding.pSellTxt3.text = formatter?.format(item.SellPrice3) ?: "0"
        binding.pSellTxt4.text = formatter?.format(item.SellPrice4) ?: "0"
        binding.pSellTxt5.text = formatter?.format(item.SellPrice5) ?: "0"
        binding.aSellTxt.text = item.SellAmount1.toString()
        binding.aSellTxt2.text = item.SellAmount2.toString()
        binding.aSellTxt3.text = item.SellAmount3.toString()
        binding.aSellTxt4.text = item.SellAmount4.toString()
        binding.aSellTxt5.text = item.SellAmount5.toString()
        binding.pBuyTxt.text = formatter?.format(item.BuyPrice1) ?: "0"
        binding.pBuyTxt2.text = formatter?.format(item.BuyPrice2) ?: "0"
        binding.pBuyTxt3.text = formatter?.format(item.BuyPrice3) ?: "0"
        binding.pBuyTxt4.text = formatter?.format(item.BuyPrice4) ?: "0"
        binding.pBuyTxt5.text = formatter?.format(item.BuyPrice5) ?: "0"
        binding.aBuyTxt.text = item.BuyAmount1.toString()
        binding.aBuyTxt2.text = item.BuyAmount2.toString()
        binding.aBuyTxt3.text = item.BuyAmount3.toString()
        binding.aBuyTxt4.text = item.BuyAmount4.toString()
        binding.aBuyTxt5.text = item.BuyAmount5.toString()
        binding.openTxt.text = formatter?.format(item.Open) ?: "0"
        binding.closeTxt.text = formatter?.format(item.Close) ?: "0"
        binding.highTxt.text = formatter?.format(item.High) ?: "0"
        binding.lowTxt.text = formatter?.format(item.Low) ?: "0"
        binding.dailyChangeTxt.text = item.DailyChange.toString() + "%"
        binding.dailyVolTxt.text = "$" + item.DailyVol.toString() + "T"

        if (item.ChangePercent > 0) {
            binding.priceTxt.setTextColor(resources.getColor(R.color.green))
            binding.changePercentTxt.setTextColor(resources.getColor(R.color.green))
        } else {
            binding.priceTxt.setTextColor(resources.getColor(R.color.red))
            binding.changePercentTxt.setTextColor(resources.getColor(R.color.red))
        }
        val drawable = resources.getIdentifier(item.Sumbollogo, "drawable", packageName)

        Glide.with(this)
            .load(drawable)
            .into(binding.logoImg)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun orderType() {
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            listOf("Limit Order", "Market Order", "Stop Order")
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.orderTypeSpin.adapter = adapter
    }
}