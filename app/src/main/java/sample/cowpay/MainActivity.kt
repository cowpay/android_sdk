package sample.cowpay

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.cowpay.PaymentMethodsActivity
import me.cowpay.util.CowpayConstantKeys
import sample.cowpay.databinding.ActivityMainBinding
import java.security.SecureRandom
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnSaveCreditCardMainActivity.visibility = View.GONE
        setListener()
    }


    fun setListener() {
        binding.btnPayWithCreditCardMainActivity.setOnClickListener {
            payWithCreditCard()
        }

        binding.btnPayWithFawryMainActivity.setOnClickListener {
            payWithFawry()
        }

        binding.btnSaveCreditCardMainActivity.setOnClickListener {
            //not fully implemented
//            saveCreditCard()
        }

        binding.btnOpenPaymentListMainActivity.setOnClickListener {
            openPaymentList()
        }

        binding.rbSandboxMainActivity.setOnCheckedChangeListener { buttonView, isChecked ->
            paymentEnvironment =
                if (isChecked) CowpayConstantKeys.SandBox else CowpayConstantKeys.Production
        }
    }

    //stage auth
    var authorizationToken = "YOUR-Authorization-Token"
    //merchant data
    //dev1212
    //GHIu9nk25D5z
    var merchantCode = "dev1212"
    //$2y$10$IcVQiqt7dm4LEAWaNYfo4uYpv8H9qzjnnCfv9VUDO8QkGkej1KmLq
    //dev1212
    var merchantHashKey = "dev1212"
    var paymentEnvironment = CowpayConstantKeys.SandBox
    var amount = "1"
    fun payWithCreditCard() {
        var intent = Intent(this@MainActivity, PaymentMethodsActivity::class.java)
        //choose payment method
        var PaymentMethod = ArrayList<String>()
        PaymentMethod.add(CowpayConstantKeys.CreditCardMethod)
        intent.putExtra(CowpayConstantKeys.PaymentMethod, PaymentMethod)
        intent.putExtra(CowpayConstantKeys.AuthorizationToken, authorizationToken)
        //set environment production or sandBox
        //CowpayConstantKeys.Production or CowpayConstantKeys.SandBox
        intent.putExtra(CowpayConstantKeys.PaymentEnvironment, paymentEnvironment)
        //set locale language
        var locale = CowpayConstantKeys.ARABIC
        intent.putExtra(CowpayConstantKeys.Language, locale)
        CowpayConstantKeys.LanguageCode = locale
        // use pay with credit card
        intent.putExtra(
            CowpayConstantKeys.CreditCardMethodType,
            CowpayConstantKeys.CreditCardMethodPay
        )
        //merchant data
        intent.putExtra(CowpayConstantKeys.MerchantCode, merchantCode)
        intent.putExtra(
            CowpayConstantKeys.MerchantHashKey,
            merchantHashKey
        )
        //order id
        intent.putExtra(CowpayConstantKeys.MerchantReferenceId, getRandomNumber().toString())
        //order price780
        intent.putExtra(CowpayConstantKeys.Amount, amount)
        //user data
        intent.putExtra(CowpayConstantKeys.CustomerName, "John Smith")
        intent.putExtra(CowpayConstantKeys.CustomerMobile, "01234567890")
        intent.putExtra(CowpayConstantKeys.CustomerEmail, "customer@customer.com")
        intent.putExtra(CowpayConstantKeys.Description, "example description")
        //user id
        intent.putExtra(CowpayConstantKeys.CustomerMerchantProfileId, "15")
        startActivityForResult(intent, CowpayConstantKeys.PaymentMethodsActivityRequestCode)
    }

    fun getRandomNumber(): Int {
        val r = SecureRandom()
        val Low = 10000
        val High = 1000000000
        return (System.currentTimeMillis() % Integer.MAX_VALUE).toInt() + (r.nextInt(High - Low) + Low)
    }

    fun payWithFawry() {
        var intent = Intent(this@MainActivity, PaymentMethodsActivity::class.java)
        //choose payment method
        var PaymentMethod = ArrayList<String>()
        PaymentMethod.add(CowpayConstantKeys.FawryMethod)

        intent.putExtra(CowpayConstantKeys.PaymentMethod, PaymentMethod)
        intent.putExtra(CowpayConstantKeys.AuthorizationToken, authorizationToken)
        //set environment production or sandBox
        //CowpayConstantKeys.Production or CowpayConstantKeys.SandBox
        intent.putExtra(CowpayConstantKeys.PaymentEnvironment, paymentEnvironment)
        //set locale language
        var locale = CowpayConstantKeys.ENGLISH
        intent.putExtra(CowpayConstantKeys.Language, locale)
        CowpayConstantKeys.LanguageCode = locale
        //merchant data
        intent.putExtra(CowpayConstantKeys.MerchantCode, merchantCode)
        intent.putExtra(
            CowpayConstantKeys.MerchantHashKey,
            merchantHashKey
        )
        //order id
        intent.putExtra(CowpayConstantKeys.MerchantReferenceId, getRandomNumber().toString())
        //order price780
        intent.putExtra(CowpayConstantKeys.Amount, amount)
        //user data
        intent.putExtra(CowpayConstantKeys.CustomerName, "John Smith")
        intent.putExtra(CowpayConstantKeys.CustomerMobile, "+201096545211")
        intent.putExtra(CowpayConstantKeys.CustomerEmail, "customer@customer.com")
        intent.putExtra(CowpayConstantKeys.Description, "example description")
        //user id
        intent.putExtra(CowpayConstantKeys.CustomerMerchantProfileId, "15")
        startActivityForResult(intent, CowpayConstantKeys.PaymentMethodsActivityRequestCode)
    }

    fun saveCreditCard() {
        var intent = Intent(this@MainActivity, PaymentMethodsActivity::class.java)
        //choose payment method
        var PaymentMethod = ArrayList<String>()
        PaymentMethod.add(CowpayConstantKeys.CreditCardMethod)
        intent.putExtra(CowpayConstantKeys.PaymentMethod, PaymentMethod)
        intent.putExtra(CowpayConstantKeys.AuthorizationToken, authorizationToken)
        //set environment production or sandBox
        //CowpayConstantKeys.Production or CowpayConstantKeys.SandBox
        intent.putExtra(CowpayConstantKeys.PaymentEnvironment, paymentEnvironment)
        //set locale language
        var locale = CowpayConstantKeys.ENGLISH
        intent.putExtra(CowpayConstantKeys.Language, locale)
        CowpayConstantKeys.LanguageCode = locale
        // use save credit card
        intent.putExtra(
            CowpayConstantKeys.CreditCardMethodType,
            CowpayConstantKeys.CreditCardMethodSave
        )
        //merchant data
        intent.putExtra(CowpayConstantKeys.MerchantCode, merchantCode)
        intent.putExtra(
            CowpayConstantKeys.MerchantHashKey,
            merchantHashKey
        )
        //order id
        intent.putExtra(CowpayConstantKeys.MerchantReferenceId, getRandomNumber().toString())
        //order price780
        intent.putExtra(CowpayConstantKeys.Amount, amount)
        //user data
        intent.putExtra(CowpayConstantKeys.CustomerName, "John Smith")
        intent.putExtra(CowpayConstantKeys.CustomerMobile, "01234567890")
        intent.putExtra(CowpayConstantKeys.CustomerEmail, "customer@customer.com")
        intent.putExtra(CowpayConstantKeys.Description, "example description")
        //user id
        intent.putExtra(CowpayConstantKeys.CustomerMerchantProfileId, "15")
        startActivityForResult(intent, CowpayConstantKeys.PaymentMethodsActivityRequestCode)
    }

    fun openPaymentList() {
        var intent = Intent(this@MainActivity, PaymentMethodsActivity::class.java)
        //choose payment method
        var PaymentMethod = ArrayList<String>()
        PaymentMethod.add(CowpayConstantKeys.CreditCardMethod)
        PaymentMethod.add(CowpayConstantKeys.FawryMethod)
        intent.putExtra(CowpayConstantKeys.PaymentMethod, PaymentMethod)
        intent.putExtra(CowpayConstantKeys.AuthorizationToken, authorizationToken)
        //set environment production or sandBox
        //CowpayConstantKeys.Production or CowpayConstantKeys.SandBox
        intent.putExtra(CowpayConstantKeys.PaymentEnvironment, paymentEnvironment)
        //set locale language
        var locale = CowpayConstantKeys.ARABIC
        intent.putExtra(CowpayConstantKeys.Language, locale)
        CowpayConstantKeys.LanguageCode = locale
        //merchant data
        intent.putExtra(CowpayConstantKeys.MerchantCode, merchantCode)
        intent.putExtra(
            CowpayConstantKeys.MerchantHashKey,
            merchantHashKey
        )
        //order id
        intent.putExtra(CowpayConstantKeys.MerchantReferenceId, getRandomNumber().toString())
        //order price780
        intent.putExtra(CowpayConstantKeys.Amount, amount)
        //user data
        intent.putExtra(CowpayConstantKeys.CustomerName, "John Smith")
        intent.putExtra(CowpayConstantKeys.CustomerMobile, "01234567890")
        intent.putExtra(CowpayConstantKeys.CustomerEmail, "customer@customer.com")
        intent.putExtra(CowpayConstantKeys.Description, "example description")
        //user id
        intent.putExtra(CowpayConstantKeys.CustomerMerchantProfileId, "15")
        startActivityForResult(intent, CowpayConstantKeys.PaymentMethodsActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CowpayConstantKeys.PaymentMethodsActivityRequestCode && data != null && resultCode == Activity.RESULT_OK) {
            var responseCode = data.extras!!.getInt(CowpayConstantKeys.ResponseCode, 0)
            var paymentMethod = data.extras!!.getString(CowpayConstantKeys.PaymentMethod)
            if (responseCode == CowpayConstantKeys.ErrorCode) {
                var responseMSG = data.extras!!.getString(CowpayConstantKeys.ResponseMessage)
                Toast.makeText(this@MainActivity, "$responseMSG $paymentMethod", Toast.LENGTH_LONG)
                    .show()
            } else if (responseCode == CowpayConstantKeys.SuccessCode) {
                var responseMSG = data.extras!!.getString(CowpayConstantKeys.ResponseMessage)
                var paymentGatewayReferenceId =
                    data.extras!!.getString(CowpayConstantKeys.PaymentGatewayReferenceId)
                Toast.makeText(
                    this@MainActivity,
                    responseMSG.plus(" $paymentMethod $paymentGatewayReferenceId"),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
}
