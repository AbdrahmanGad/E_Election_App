package com.example.electronicelectionapp.Candidate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicelectionapp.Candidate.adapter.CA_ElectionsRecycleViewAdapter
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Student.ST_AddPrivateElection
import com.example.electronicelectionapp.Student.ST_Settings
import com.example.electronicelectionapp.Supervisor.adapter.ElectionRecyclerViewAdapter
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.AddFutureElection
import com.example.electronicelectionapp.theRemote.dataClass.ElectionsList
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import kotlinx.android.synthetic.main.activity_ca_home_page.*
import kotlinx.android.synthetic.main.activity_st_home_page.*
import kotlinx.android.synthetic.main.activity_st_home_page.BTN_addPrivateElection
import kotlinx.android.synthetic.main.activity_sv_home_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CA_HomePage : AppCompatActivity() {

    lateinit var myAdapter : CA_ElectionsRecycleViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ca_home_page)

        CA_back.setOnClickListener {
            onBackPressed()
        }

        val bundel: Bundle? = intent.extras
        val eCandidateUsername = bundel!!.getInt("CandidateUsername")

        CA_HP_setting.setOnClickListener {
            val intent = Intent(this@CA_HomePage, CA_Settings::class.java)
            intent.putExtra("CandidateUsername", eCandidateUsername)
            startActivity(intent)
        }

        BTN_addPrivateElection.setOnClickListener {
            val intent = Intent(this@CA_HomePage, CA_AddPrivateElection::class.java)
            startActivity(intent)
        }

        linearLayoutManager = LinearLayoutManager(this)
        CA_RV_Homepage.layoutManager = linearLayoutManager

        getElectionsList()
    }

    val retrofitInstance: RetrofitInstance = RetrofitInstance

    fun getElectionsList(){
        val response = retrofitInstance.api.getElectionsList()
        response.enqueue(object : Callback<List<ElectionsWithResultItem>?> {
            override fun onResponse(
                call: Call<List<ElectionsWithResultItem>?>,
                response: Response<List<ElectionsWithResultItem>?>
            ) {
                Log.d("MainActivity", "CA_Success_Elections")
                val responseBody = response.body()!!
                myAdapter = CA_ElectionsRecycleViewAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                CA_RV_Homepage.setHasFixedSize(true)
                CA_RV_Homepage.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<ElectionsWithResultItem>?>, t: Throwable) {
                Log.d("MainActivity", "CA_Failed_Elections")
            }
        })
    }
}