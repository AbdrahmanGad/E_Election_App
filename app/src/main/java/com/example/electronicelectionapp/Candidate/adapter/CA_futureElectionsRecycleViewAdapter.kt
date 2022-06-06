package com.example.electronicelectionapp.Candidate.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicelectionapp.Candidate.CA_FutureElectionDetails
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.theRemote.dataClass.AddFutureElection
import com.example.electronicelectionapp.theRemote.dataClass.ElectionsList
import kotlinx.android.synthetic.main.row_future_elections_ca.view.*

class CA_futureElectionsRecycleViewAdapter(
    var context: Context,
    var futureElections: List<AddFutureElection>,
): RecyclerView.Adapter<CA_futureElectionsRecycleViewAdapter.futureElectionsViewHolder>() {

    class futureElectionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView.rootView) {
        var et_name: TextView
        init {
            et_name = itemView.TV_FutureElectionInTheRV
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): futureElectionsViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_future_elections_ca, parent, false)
        return futureElectionsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: futureElectionsViewHolder, position: Int) {
        val elections: AddFutureElection =  futureElections[position]
        holder.et_name.text = elections.election_pre_name
        holder.itemView.rootView.setOnClickListener {
            val intent = Intent(context, CA_FutureElectionDetails::class.java)
            intent.putExtra("electionID", elections.electionID)
            intent.putExtra("electionPreName", elections.election_pre_name)
            intent.putExtra("electionPreDescription", elections.election_pre_description)
            intent.putExtra("electionFormURL", elections.cv_form_url)
            intent.putExtra("electionPreStartDate", elections.start_date)
            intent.putExtra("electionPreEndDate", elections.end_date)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return futureElections.size
    }


}