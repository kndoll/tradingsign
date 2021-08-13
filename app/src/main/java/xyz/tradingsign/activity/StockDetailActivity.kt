package xyz.tradingsign.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.commexpert.ExpertTranProc
import com.truefriend.corelib.commexpert.intrf.ITranDataListener
import xyz.tradingsign.R

class StockDetailActivity : AppCompatActivity(), ITranDataListener {

    var priceTranProc: ExpertTranProc? = null
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)

        priceTranProc = ExpertTranProc(this@StockDetailActivity)
        println(">>> " + priceTranProc)
        priceTranProc!!.InitInstance(this@StockDetailActivity)
        priceTranProc!!.SetShowTrLog(true)

        priceTranProc!!.SetSingleData(0,0, "J");
        priceTranProc!!.SetSingleData(0, 1, "005930")


        button = findViewById(R.id.button_test) as Button
        button!!.setOnClickListener(View.OnClickListener {
            var ret = priceTranProc!!.RequestData("scp")
            println(">> ret ==> " + ret)
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        priceTranProc?.ClearInstance()
    }

    override fun onTranDataReceived(sTranID: String?, nRqId: Int) {
        println(">>>>> onTranDataReceived")
    }

    override fun onTranMessageReceived(p0: Int, p1: String?, p2: String?, p3: String?) {
        println(">>>>> onTranMessageReceived")
    }

    override fun onTranTimeout(p0: Int) {
        println(">>>>> onTranTimeout")
    }
}