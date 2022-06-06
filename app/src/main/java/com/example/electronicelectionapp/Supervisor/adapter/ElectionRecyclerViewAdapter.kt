package com.example.electronicelectionapp.Supervisor.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicelectionapp.Candidate.CA_FutureElectionDetails
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Supervisor.SV_ElectionStatus
import com.example.electronicelectionapp.Supervisor.model.Election
import com.example.electronicelectionapp.theRemote.dataClass.ElectionsList
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.Candidatell
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import kotlinx.android.synthetic.main.activity_sv_add_new_election.view.*
import kotlinx.android.synthetic.main.activity_sv_add_new_election.view.endDate
import kotlinx.android.synthetic.main.row_election_sv.view.*
import java.io.Serializable

class ElectionRecyclerViewAdapter(
    var context: Context,
    var electionsList: List<ElectionsWithResultItem>
    ) : RecyclerView.Adapter<ElectionRecyclerViewAdapter.ElectionViewHolder>() {
    class ElectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
        var et_name: TextView
        var et_description: TextView
        var et_endDate: TextView
        var et_roomType: TextView
        init {
            et_name = itemView.row_election_name
            et_description = itemView.row_election_description
            et_endDate = itemView.rowend_date
            et_roomType = itemView.row_room_type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_election_sv, parent, false)
        return ElectionViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        val election: ElectionsWithResultItem = electionsList[position]
        val list: ArrayList<Candidatell> = election.candidates as ArrayList<Candidatell>

        holder.et_name.text = election.election_name
        holder.et_description.text = election.election_description
        holder.et_endDate.text = election.end_date

        if (election.public_room == "True"){
            holder.et_roomType.text = "Public"
        }else if (election.private_room == "True"){
            holder.et_roomType.text = "Private"
        }

        holder.itemView.rootView.setOnClickListener {
            val intent = Intent(context, SV_ElectionStatus::class.java)

            intent.putExtra("electionID", election.id)
            intent.putExtra("electionName", election.election_name)
            intent.putExtra("electionDescription", election.election_description)
            intent.putExtra("electionStartDate", election.start_date)
            intent.putExtra("electionEndDate", election.end_date)
            intent.putExtra("electionTotalVoters", election.total_voters)

            intent.putExtra("electionCandidatesList", list as Serializable)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return electionsList.size
    }


}