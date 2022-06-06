package com.example.electronicelectionapp.Supervisor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.Candidates
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicelectionapp.R
import kotlinx.android.synthetic.main.row_candidate_name.view.*

class SV_AddNewElectionCandidatesListRVAdapter(
    var context: Context,
    var arraylist: ArrayList<Candidates> = ArrayList<Candidates>()

): RecyclerView.Adapter<SV_AddNewElectionCandidatesListRVAdapter.CandidateViewHolder>(){
//    var arraylist: ArrayList<String> = ArrayList<String>()
//    fun MainAdapter(contacts: ArrayList<String>) {
//        arraylist = contacts
//    }

    class CandidateViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){
        var mCandidateName : TextView

        init {
            mCandidateName = itemView.row_Candidate_name_TV
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_candidate_name, parent, false)
        return CandidateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        val xxx: Candidates = arraylist[position]
        holder.mCandidateName.text = xxx.candidate_name
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

}
