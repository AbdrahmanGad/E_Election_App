package com.example.electronicelectionapp.Candidate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.Candidatell
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import kotlinx.android.synthetic.main.row_candidate_ca.view.*

class CA_ElectionsCandidatesRVAdapter (

    var context: Context,
    var candidatesList: List<Candidatell>,
    var NoOfVoter: Int
    ): RecyclerView.Adapter<CA_ElectionsCandidatesRVAdapter.CandidatesViewHolder>(){

    class CandidatesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView.rootView) {
        var et_name: TextView
        var et_votesPercentage: AppCompatTextView
        init {
            et_name = itemView.candidateNameRowCA
            et_votesPercentage = itemView.candidatePercentageCA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidatesViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_candidate_ca, parent, false)
        return CandidatesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CandidatesViewHolder, position: Int) {
        val elections: Candidatell = candidatesList[position]
        holder.et_name.text = elections.candidate_name
        val percentage = ((elections.votes.toDouble()/NoOfVoter.toDouble())*100).toInt()
        holder.et_votesPercentage.text = "$percentage %"

        holder.itemView.rootView.setOnClickListener {
            Toast.makeText(
                context,
                "${elections.candidate_name} No. : ${elections.id}",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun getItemCount(): Int {
        return candidatesList.size
    }


}