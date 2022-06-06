package com.example.electronicelectionapp.Supervisor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Supervisor.adapter.ElectionRecyclerViewAdapter
import com.example.electronicelectionapp.Supervisor.adapter.SV_AddNewElectionCandidatesListRVAdapter
import com.example.electronicelectionapp.Supervisor.adapter.SV_ElectionStatusCandidatesListRVAdapter
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.Candidatell
import kotlinx.android.synthetic.main.activity_ca_election.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sv_election_status.*
import kotlinx.android.synthetic.main.activity_sv_election_status.back
import kotlinx.android.synthetic.main.activity_sv_election_status.setting
import kotlinx.android.synthetic.main.activity_sv_home_page.*

class SV_ElectionStatus : AppCompatActivity() {
    lateinit var myCandidatesList: SV_ElectionStatusCandidatesListRVAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_election_status)

        back.setOnClickListener {
            onBackPressed()
        }

        setting.setOnClickListener {
            val intent = Intent(this@SV_ElectionStatus, SV_Settings::class.java)
            startActivity(intent)
        }

        val electionName = findViewById<TextView>(R.id.election_name)
        val electionDescription = findViewById<TextView>(R.id.election_Description)
        val electionStartDate = findViewById<TextView>(R.id.startDate)
        val electionEndDate = findViewById<TextView>(R.id.endDate)
        val electionRoomType = findViewById<TextView>(R.id.TV_roomType)

        val i = intent
        val eCandidatesll: ArrayList<Candidatell> =
            i.getSerializableExtra("electionCandidatesList") as ArrayList<Candidatell>

        linearLayoutManager = LinearLayoutManager(this)
        RV_SV_candidate.layoutManager = linearLayoutManager



        val bundel : Bundle? = intent.extras

        val eID = bundel!!.getInt("electionID")
        val eName = bundel!!.getString("electionName")
        val eDescription = bundel.getString("electionDescription")
        val eStartDate = bundel.getString("electionStartDate")
        val eEndDate = bundel.getString("electionEndDate")
        val eRoomTypePublic = bundel.getString("electionPublicRoom")
        val eRoomTypePrivate = bundel.getString("electionPrivateRoom")

        val eTotalNoOfVoters = bundel.getInt("electionTotalVoters")


        ET_totalNo_OfVoters.text = eTotalNoOfVoters.toString()

        electionName.text = eName
        electionDescription.text = eDescription
        electionStartDate.text = eStartDate
        electionEndDate.text = eEndDate

        if (eRoomTypePublic == "Public"){
            electionRoomType.text = "Public"
        }else if (eRoomTypePrivate == "Private"){
            electionRoomType.text = "Private"
        }

        myCandidatesList = SV_ElectionStatusCandidatesListRVAdapter(baseContext, eCandidatesll, eTotalNoOfVoters)
        myCandidatesList.notifyDataSetChanged()
        RV_SV_candidate.setHasFixedSize(true)
        RV_SV_candidate.adapter = myCandidatesList

    }
}