package xyz.tradingsign.activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.commexpert.CommExpertMng
import xyz.tradingsign.R

class StockListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_list)

        var tickerList: ArrayList<String> = ArrayList()

        val kospiList = CommExpertMng.getInstance().GetKospiCodeList()
        val kosdaqList = CommExpertMng.getInstance().GetKosdaqCodeList()
        kospiList.forEach() {
            println(it.name + " : " + it.code)
            tickerList.add(it.name)
        }
        kosdaqList.forEach() {
            tickerList.add(it.name)
        }

        val autoComplete: AutoCompleteTextView = findViewById(R.id.ticker_auto_complete)

        // adapter set
        autoComplete.setAdapter(ArrayAdapter<String>(this@StockListActivity,
            android.R.layout.simple_dropdown_item_1line, tickerList))

        // event
        autoComplete.setOnItemClickListener { parent, view, position, id ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}