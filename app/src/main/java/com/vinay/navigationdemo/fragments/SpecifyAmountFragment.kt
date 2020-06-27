package com.vinay.navigationdemo.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vinay.navigationdemo.R
import com.vinay.navigationdemo.models.Money
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal


/**
 * A simple [Fragment] subclass.
 */
class SpecifyAmountFragment : Fragment() , View.OnClickListener{
    lateinit  var navController: NavController
    lateinit var recipientName: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipientName = it.getString("recipient", "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        send_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
        val message = "Sending money to $recipientName"
        recipient.text = message
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it.id){
                R.id.send_btn -> {
                    if(!TextUtils.isEmpty(input_amount.text.toString())){
                        val amount = Money(BigDecimal(input_amount.text.toString()))
                        val bundle = bundleOf(
                            "recipient" to recipientName,
                            "amount" to amount
                        )
                        navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle)
                    }else{
                        Toast.makeText(context, "Enter Amount", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.cancel_btn -> activity?.onBackPressed()
                else -> {}
            }
        }
    }
}
