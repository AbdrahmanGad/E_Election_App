package com.example.electronicelectionapp.Student

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicelectionapp.Candidate.adapter.CA_ElectionsCandidatesRVAdapter
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Student.adapter.ST_ElectionsCandidatesRVAdapter
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.CandidateID
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.Candidatell
import kotlinx.android.synthetic.main.activity_ca_election.*
import kotlinx.android.synthetic.main.activity_ca_future_election.*
import kotlinx.android.synthetic.main.activity_st_election.*
import kotlinx.android.synthetic.main.activity_st_home_page.*
import kotlinx.android.synthetic.main.activity_st_home_page.ST_back
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ST_ElectionStatus : AppCompatActivity() {
    lateinit var myAdapter: ST_ElectionsCandidatesRVAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_election)

        ST_back.setOnClickListener {
            onBackPressed()
        }

        ST_setting.setOnClickListener {
            val intent = Intent(this@ST_ElectionStatus, ST_Settings::class.java)
            startActivity(intent)
        }

        linearLayoutManager = LinearLayoutManager(this)
        ST_RV_candidates_homepage.layoutManager = linearLayoutManager

        val i = intent
        val eCandidatesll: ArrayList<Candidatell> =
            i.getSerializableExtra("list") as ArrayList<Candidatell>

        val eID = i.getIntExtra("electionID", 0)
        val eElectionName = i.getStringExtra("electionName")
        val eElectionDescription = i.getStringExtra("electionDescription")
        val eStartDate = i.getStringExtra("electionStartDate")
        val eEndDate = i.getStringExtra("electionEndDate")

        val eTotalNoOfVoters = i.getIntExtra("totalVoters", 0)
        val eFormURL = i.getStringExtra("electionFormURL")

        election_name_st.text = eElectionName
        electionDescriptionST.text = eElectionDescription
        startDateST.text = eStartDate
        endDateST.text = eEndDate

        myAdapter = ST_ElectionsCandidatesRVAdapter(baseContext, eCandidatesll, eTotalNoOfVoters)
        myAdapter.notifyDataSetChanged()
        ST_RV_candidates_homepage.setHasFixedSize(true)
        ST_RV_candidates_homepage.adapter = myAdapter

        var id = 0
        val candidateNo = "Candidate No. "
        minusOneST.setOnClickListener {
            if (id > 0){
                id -= 1
                candidatesToVoteST.text = candidateNo + id
            }
        }
        addOneST.setOnClickListener {
            id += 1
            candidatesToVoteST.text = candidateNo + id
        }

        BTN_submit_vote_ST.isEnabled = true

        BTN_submit_vote_ST.setOnClickListener {
            val retrofitInstance = RetrofitInstance
            val requestCall = retrofitInstance.api.vote(eID, CandidateID(id))

            requestCall.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(this@ST_ElectionStatus, "Voted Successfully", Toast.LENGTH_SHORT).show()
                        BTN_submit_vote_ST.isEnabled  =  false
                    }else if (response.code() == 404) {
                        Toast.makeText(this@ST_ElectionStatus, "Failed \n Please Make Sure of Candidate No.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.d("MainActivity", "Failed")
                }
            })
        }
        BTN_allCandidatesData_ST.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eFormURL))
            startActivity(intent)
        }

    }
}