package com.example.electronicelectionapp.Candidate

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicelectionapp.Candidate.adapter.CA_ElectionsCandidatesRVAdapter
import com.example.electronicelectionapp.Candidate.adapter.CA_ElectionsRecycleViewAdapter
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.CandidateID
import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.Candidates
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.Candidatell
import kotlinx.android.synthetic.main.activity_ca_election.*
import kotlinx.android.synthetic.main.activity_ca_election.CA_back
import kotlinx.android.synthetic.main.activity_ca_election.CA_setting
import kotlinx.android.synthetic.main.activity_ca_election.election_name
import kotlinx.android.synthetic.main.activity_ca_election.endDate
import kotlinx.android.synthetic.main.activity_ca_election.startDate
import kotlinx.android.synthetic.main.activity_ca_future_election.*
import kotlinx.android.synthetic.main.activity_ca_home_page.*
import kotlinx.android.synthetic.main.activity_sv_election_status.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CA_ElectionStatus : AppCompatActivity() {

    lateinit var myAdapter: CA_ElectionsCandidatesRVAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ca_election)

        CA_setting.setOnClickListener {
            val intent = Intent(this@CA_ElectionStatus, CA_Settings::class.java)
            startActivity(intent)
        }

        CA_back.setOnClickListener {
            onBackPressed()
        }

        linearLayoutManager = LinearLayoutManager(this)
        CA_RV_ElectionStatus.layoutManager = linearLayoutManager

        val i = intent
        val eCandidatesll: ArrayList<Candidatell> =
            i.getSerializableExtra("list") as ArrayList<Candidatell>

        val eID = i.getIntExtra("electionID", 0)
        val eElectionName = i.getStringExtra("electionName")
        val eElectionDescription = i.getStringExtra("electionDescription")
        val eStartDate = i.getStringExtra("electionStartDate")
        val eEndDate = i.getStringExtra("electionEndDate")

        val eTotalNoOfVoters = i.getIntExtra("electionTotalVoters", 0)
        val eFormURL = i.getStringExtra("electionFormURL")

        election_name.text = eElectionName
        electionDescriptionCA.text = eElectionDescription
        startDate.text = eStartDate
        endDate.text = eEndDate

        myAdapter = CA_ElectionsCandidatesRVAdapter(baseContext, eCandidatesll,eTotalNoOfVoters )
        myAdapter.notifyDataSetChanged()
        CA_RV_ElectionStatus.setHasFixedSize(true)
        CA_RV_ElectionStatus.adapter = myAdapter

        var id = 0
        val candidateNo = "Candidate No. "
        minusOne.setOnClickListener {
            if (id > 0){
                id -= 1
                candidatesToVote.text = candidateNo + id
            }
        }
        addOne.setOnClickListener {
            id += 1
            candidatesToVote.text = candidateNo + id
        }


        BTN_allCandidatesData_CA.setOnClickListener {
            //https://www.surveymonkey.com/r/MTZF9NV
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eFormURL))
            startActivity(intent)
        }

        BTN_submit_vote_CA.setOnClickListener {
            val retrofitInstance = RetrofitInstance
            val requestCall = retrofitInstance.api.vote(eID, CandidateID(id))

            requestCall.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(this@CA_ElectionStatus, "Voted Successfully", Toast.LENGTH_SHORT).show()
                    }
                    if (response.code() == 404) {
                        Toast.makeText(this@CA_ElectionStatus, "Failed \n Please Make Sure of Candidate No.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.d("MainActivity", "Failed")
                }
            })
        }
    }
}