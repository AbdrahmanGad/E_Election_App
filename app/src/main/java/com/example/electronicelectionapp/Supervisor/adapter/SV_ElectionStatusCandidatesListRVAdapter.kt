package com.example.electronicelectionapp.Supervisor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.Candidatell
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import kotlinx.android.synthetic.main.row_candidate_ca.view.*
import kotlinx.android.synthetic.main.row_candidate_sv.view.*

class SV_ElectionStatusCandidatesListRVAdapter(
var context: Context,
var candidatesList: List<Candidatell>,
var NoOfVoter: Int
): RecyclerView.Adapter<SV_ElectionStatusCandidatesListRVAdapter.CandidatesListViewHolder>(){

    class CandidatesListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView.rootView) {
        var et_name: TextView
        var et_votesPercentage: AppCompatTextView
        init {
            et_name = itemView.candidateNameRowSV
            et_votesPercentage = itemView.CandidatePercentageSV
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidatesListViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_candidate_sv, parent, false)
        return CandidatesListViewHolder(itemView)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CandidatesListViewHolder, position: Int) {
        val elections: Candidatell = candidatesList[position]
        holder.et_name.text = elections.candidate_name
        val percentage = ((elections.votes.toDouble()/NoOfVoter.toDouble())*100).toInt()
        holder.et_votesPercentage.text = "$percentage %"
//        Log.d("MainActivity", "${elections.votes}")
//        Log.d("MainActivity", "$NoOfVoter")
//        Log.d("MainActivity", "${} %")

//        holder.et_votesPercentage.text = "$percentage %"

//        holder.itemView.rootView.setOnClickListener {
//            Toast.makeText(
//                context,
//                "${elections.candidate_name} No. : ${elections.id}",
//                Toast.LENGTH_LONG
//            ).show()
//        }

    }
    override fun getItemCount(): Int {
        return candidatesList.size
    }
}