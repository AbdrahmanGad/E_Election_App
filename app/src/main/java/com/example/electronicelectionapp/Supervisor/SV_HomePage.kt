package com.example.electronicelectionapp.Supervisor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Supervisor.adapter.ElectionRecyclerViewAdapter
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.ElectionsList
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import kotlinx.android.synthetic.main.activity_sv_home_page.*
import kotlinx.android.synthetic.main.activity_sv_home_page.back
import kotlinx.android.synthetic.main.activity_sv_settings.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class SV_HomePage : AppCompatActivity() {


    lateinit var myAdapter : ElectionRecyclerViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
//    lateinit var electionsList: List<ElectionsList>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_home_page)

        BTN_ADD_election.setOnClickListener {
            val intent = Intent(this@SV_HomePage, SV_AddNewElection::class.java)
            startActivity(intent)
        }

        back.setOnClickListener {
            onBackPressed()
        }

        setting.setOnClickListener {
            val intent = Intent(this@SV_HomePage, SV_Settings::class.java)
            startActivity(intent)
        }

        linearLayoutManager = LinearLayoutManager(this)
        RV_sv_elections_homepage.layoutManager = linearLayoutManager

//        electionsList
        getElectionsList()
    }


    val retrofitInstance: RetrofitInstance = RetrofitInstance

    fun getElectionsList(){
        val response = retrofitInstance.api.getElectionsList()

        response.enqueue(object : retrofit2.Callback<List<ElectionsWithResultItem>?> {
            override fun onResponse(
                call: Call<List<ElectionsWithResultItem>?>,
                response: Response<List<ElectionsWithResultItem>?>
            ) {
                Log.d("MainActivity", "success")
                val responseBody = response.body()!!

                myAdapter = ElectionRecyclerViewAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                RV_sv_elections_homepage.setHasFixedSize(true)
                RV_sv_elections_homepage.adapter = myAdapter
//                myAdapter.setOnItemClickListener(object : ElectionRecyclerViewAdapter.onItemClickListener{
//                    override fun onItemClick(position: Int) {
//
//                        val intent = Intent(this@SV_HomePage, SV_ElectionStatus::class.java)
//                        intent.putExtra("electionName", electionsList[position].election_name)
//                        intent.putExtra("electionDescription", electionsList[position].election_description)
//                        intent.putExtra("electionStartDate", electionsList[position].start_date)
//                        intent.putExtra("electionEndDate", electionsList[position].end_date)
//                        intent.putExtra("electionPublicRoom", electionsList[position].public_room)
//                        intent.putExtra("electionPrivateRoom", electionsList[position].private_room)
//                        startActivity(intent)
//                    }
//                })

            }

            override fun onFailure(call: Call<List<ElectionsWithResultItem>?>, t: Throwable) {
                Log.d("MainActivity", "Failed")

            }
        })
    }


}