package com.example.electronicelectionapp.Candidate

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicelectionapp.Candidate.adapter.CA_ElectionsRecycleViewAdapter
import com.example.electronicelectionapp.Candidate.adapter.CA_futureElectionsRecycleViewAdapter
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Supervisor.adapter.ElectionRecyclerViewAdapter
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.AddFutureElection
import com.example.electronicelectionapp.theRemote.dataClass.ElectionsList
import kotlinx.android.synthetic.main.activity_ca_future_elections.*
import kotlinx.android.synthetic.main.activity_sv_home_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CA_FutureElections : AppCompatActivity() {

    lateinit var myAdapter : CA_futureElectionsRecycleViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var futureElectionsList: ArrayList<AddFutureElection>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ca_future_elections)

        linearLayoutManager = LinearLayoutManager(this)
        CA_RV_FutureElections.layoutManager = linearLayoutManager

        CA_back.setOnClickListener {
            onBackPressed()
        }

        CA_setting.setOnClickListener {
            val intent = Intent(this@CA_FutureElections, CA_Settings::class.java)
            startActivity(intent)
        }

//        futureElectionsList = ArrayList<AddFutureElection>()
//        myAdapter = CA_futureElectionsRecycleViewAdapter(baseContext, futureElectionsList)
//        CA_RV_FutureElections.layoutManager = LinearLayoutManager(this)
//        CA_RV_FutureElections.adapter = myAdapter

        getFutureElectionsList()

    }
    val retrofitInstance: RetrofitInstance = RetrofitInstance

    fun getFutureElectionsList() {
        val response = retrofitInstance.api.getFutureElectionsList()

        response.enqueue(object : Callback<List<AddFutureElection>?> {
            override fun onResponse(
                call: Call<List<AddFutureElection>?>,
                response: Response<List<AddFutureElection>?>
            ) {
                Log.d("MainActivity", "CA_Future_Elections_successfully")
                val responseBody = response.body()!!
                myAdapter = CA_futureElectionsRecycleViewAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                CA_RV_FutureElections.setHasFixedSize(true)
                CA_RV_FutureElections.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<AddFutureElection>?>, t: Throwable) {
                Log.d("MainActivity", "CA_Future_Elections_Failed")
            }
        })
    }

}