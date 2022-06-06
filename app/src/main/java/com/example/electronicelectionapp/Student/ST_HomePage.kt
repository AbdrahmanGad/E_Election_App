package com.example.electronicelectionapp.Student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicelectionapp.Candidate.adapter.CA_ElectionsRecycleViewAdapter
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Student.adapter.ST_ElectionsRecyclerViewAdapter
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.ElectionsList
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import kotlinx.android.synthetic.main.activity_ca_home_page.*
import kotlinx.android.synthetic.main.activity_st_home_page.*
import kotlinx.android.synthetic.main.activity_st_home_page.BTN_addPrivateElection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ST_HomePage : AppCompatActivity() {

    lateinit var myAdapter : ST_ElectionsRecyclerViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_home_page)

        ST_back.setOnClickListener {
            onBackPressed()
        }

        St_setting.setOnClickListener {
            val intent = Intent(this@ST_HomePage, ST_Settings::class.java)
            startActivity(intent)
        }

        BTN_addPrivateElection.setOnClickListener {
            val intent = Intent(this@ST_HomePage, ST_AddPrivateElection::class.java)
            startActivity(intent)
        }

        linearLayoutManager = LinearLayoutManager(this)
        ST_RV_Homepage.layoutManager = linearLayoutManager

        getElectionsList()
    }

    val retrofitInstance: RetrofitInstance = RetrofitInstance

    private fun getElectionsList() {
        val response = retrofitInstance.api.getElectionsList()
        response.enqueue(object : Callback<List<ElectionsWithResultItem>?> {
            override fun onResponse(
                call: Call<List<ElectionsWithResultItem>?>,
                response: Response<List<ElectionsWithResultItem>?>
            ) {
                Log.d("MainActivity", "ST_Success_Elections")
                val responseBody = response.body()!!
                myAdapter = ST_ElectionsRecyclerViewAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                ST_RV_Homepage.setHasFixedSize(true)
                ST_RV_Homepage.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<ElectionsWithResultItem>?>, t: Throwable) {
                Log.d("MainActivity", "ST_Failed_Elections")
            }
        })
    }
}