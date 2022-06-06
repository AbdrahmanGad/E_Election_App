package com.example.electronicelectionapp.Student.adapter

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
import kotlinx.android.synthetic.main.row_candidate_ca.view.*
import kotlinx.android.synthetic.main.row_candidate_st.view.*

class ST_ElectionsCandidatesRVAdapter (

    var context: Context,
    var candidatesList: List<Candidatell>,
    var NoOfVoter: Int
): RecyclerView.Adapter<ST_ElectionsCandidatesRVAdapter.CandidatesViewHolder>(){

        class CandidatesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView.rootView) {
            var et_name: TextView
            var et_votesPercentage: AppCompatTextView
            init {
                et_name = itemView.candidateNameRowST
                et_votesPercentage = itemView.candidatePercentageST
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidatesViewHolder {
            var itemView = LayoutInflater.from(context).inflate(R.layout.row_candidate_st, parent, false)
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