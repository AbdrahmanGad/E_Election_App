package com.example.electronicelectionapp.Student.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicelectionapp.Candidate.CA_ElectionStatus
import com.example.electronicelectionapp.Candidate.adapter.CA_ElectionsRecycleViewAdapter
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Student.ST_ElectionStatus
import com.example.electronicelectionapp.theRemote.dataClass.ElectionsList
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.Candidatell
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import kotlinx.android.synthetic.main.row_election_ca.view.*
import kotlinx.android.synthetic.main.row_election_ca.view.electionDescription
import kotlinx.android.synthetic.main.row_election_ca.view.election_name
import kotlinx.android.synthetic.main.row_election_ca.view.endDate
import kotlinx.android.synthetic.main.row_election_ca.view.roomType
import kotlinx.android.synthetic.main.row_election_st.view.*
import java.io.Serializable

class ST_ElectionsRecyclerViewAdapter(
    var context: Context,
    var electionsList: List<ElectionsWithResultItem>
): RecyclerView.Adapter<ST_ElectionsRecyclerViewAdapter.ElectionViewHolder>() {

    class ElectionViewHolder(itemview: View): RecyclerView.ViewHolder(itemview.rootView) {
        var et_name: TextView
        var et_description: TextView
        var et_endDate: TextView
        var et_roomType: TextView
        init {
            et_name = itemview.ST_election_name
            et_description = itemview.ST_electionDescription
            et_endDate = itemview.ST_endDate
            et_roomType = itemview.ST_roomType
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_election_st, parent, false)
        return ElectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        val elections: ElectionsWithResultItem = electionsList[position]
        val list: ArrayList<Candidatell> = elections.candidates as ArrayList<Candidatell>

        holder.et_name.text = elections.election_name
        holder.et_description.text = elections.election_description
        holder.et_endDate.text = elections.end_date

        if (elections.public_room == "True"){
            holder.et_roomType.text = "Public"
        }else if (elections.private_room == "True"){
            holder.et_roomType.text = "Private"
        }

        holder.itemView.rootView.setOnClickListener {
            val intent = Intent(context, ST_ElectionStatus::class.java)
            intent.putExtra("electionID", elections.id)
            intent.putExtra("electionName", elections.election_name)
            intent.putExtra("electionDescription", elections.election_description)
//            intent.putExtra("electionCandidates", )
            intent.putExtra("list", list as Serializable)
            intent.putExtra("totalVoters", elections.total_voters)
//            Log.d("MainActivity", "$list")
//            intent.putExtra("electionVoters", )
            intent.putExtra("electionRoomType", holder.et_roomType.text)
            intent.putExtra("electionStartDate", elections.start_date)
            intent.putExtra("electionEndDate", elections.end_date)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return electionsList.size
    }

}