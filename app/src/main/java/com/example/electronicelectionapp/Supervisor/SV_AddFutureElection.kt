package com.example.electronicelectionapp.Supervisor

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.AddFutureElection
import kotlinx.android.synthetic.main.activity_sv_add_future_election.*
import kotlinx.android.synthetic.main.activity_sv_add_new_election.*
import kotlinx.android.synthetic.main.activity_sv_home_page.*
import kotlinx.android.synthetic.main.activity_sv_home_page.back
import kotlinx.android.synthetic.main.activity_sv_home_page.setting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class SV_AddFutureElection : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_add_future_election)

        var theFutureStartDate = " "
        var theFutureEndDate = " "


        val imageViewBack = findViewById<ImageView>(R.id.back)
        imageViewBack.setOnClickListener {
            onBackPressed()
        }

        val imageViewSettings = findViewById<ImageView>(R.id.setting)
        imageViewSettings.setOnClickListener {
            val intent = Intent(this@SV_AddFutureElection, SV_Settings::class.java)
            startActivity(intent)
        }

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        electionPreStartDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                if (mDay <= 9){
                    if(mMonth <= 9){
                        electionPreStartDate.text = "0$mDay/0${mMonth+1}/$mYear"
                        theFutureStartDate = "$mYear-0${mMonth+1}-0$mDay"
                    }else{
                        electionPreStartDate.text = "0$mDay/${mMonth+1}/$mYear"
                        theFutureStartDate = "$mYear-${mMonth+1}-0$mDay"
                    }
                } else if (mDay >= 10){
                    if(mMonth <= 9){
                        electionPreStartDate.text = "$mDay/0${mMonth+1}/$mYear"
                        theFutureStartDate = "$mYear-0${mMonth+1}-$mDay"
                    }else{
                        electionPreStartDate.text = "$mDay/${mMonth+1}/$mYear"
                        theFutureStartDate = "$mYear-${mMonth+1}-$mDay"
                    }
                }else{
                    electionPreStartDate.text = "$mDay/${mMonth+1}/$mYear"
                    theFutureStartDate = "$mYear-${mMonth+1}-$mDay"
                }
            }, year, month, day)
            dpd.show()
        }
        electionPreEndDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                if (mDay <= 9){
                    if(mMonth <= 9){
                        electionPreEndDate.text = "0$mDay/0${mMonth+1}/$mYear"
                        theFutureEndDate = "$mYear-0${mMonth+1}-0$mDay"
                    }else{
                        electionPreEndDate.text = "0$mDay/${mMonth+1}/$mYear"
                        theFutureEndDate = "$mYear-${mMonth+1}-0$mDay"
                    }
                } else if (mDay >= 10){
                    if(mMonth <= 9){
                        electionPreEndDate.text = "$mDay/0${mMonth+1}/$mYear"
                        theFutureEndDate = "$mYear-0${mMonth+1}-$mDay"
                    }else{
                        electionPreEndDate.text = "$mDay/${mMonth+1}/$mYear"
                        theFutureEndDate = "$mYear-${mMonth+1}-$mDay"
                    }
                }else{
                    electionPreEndDate.text = "$mDay/${mMonth+1}/$mYear"
                    theFutureEndDate = "$mYear-${mMonth+1}-$mDay"
                }
            }, year, month, day)
            dpd.show()
        }

        val retrofitInstance = RetrofitInstance
        suspend fun post(addFutureElection: AddFutureElection) = withContext(Dispatchers.IO){
            val response = retrofitInstance.api.addFutureElection(addFutureElection)

            if (response.code() == 201){
//                Toast.makeText(baseContext, "Saved Successfully", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", response.code().toString())
                Log.d("MainActivity", response.body().toString())

                val intent = Intent(this@SV_AddFutureElection, SV_HomePage::class.java)
                startActivity(intent)
            }
        }

        BTN_Save_Future_election.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                post(AddFutureElection(
                    0,
                    "12",
                    etElectionPreName.text.toString(),
                    etelectionPreDescription.text.toString(),
                    TIET_addFutureElectionForm.text.toString(),
                    theFutureStartDate,
                    theFutureEndDate))
            }
        }

    }
}