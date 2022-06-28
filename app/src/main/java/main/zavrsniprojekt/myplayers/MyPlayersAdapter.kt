package main.zavrsniprojekt.myplayers

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.players_database.view.*
import main.zavrsniprojekt.R
import main.zavrsniprojekt.database.Player

class MyPlayersAdapter(
        private var listAdapter: MutableList<Player?>,
        private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyPlayersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlayersAdapter.ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.players_database, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listAdapter.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.firstName.text = listAdapter[position]?.name
        holder.surname.text = listAdapter[position]?.surname
        holder.team.text = listAdapter[position]?.currentTeam
        val pos = listAdapter[position]?.position
        if(pos == "C")
            holder.layout?.setBackgroundColor(Color.rgb(163, 238, 240))
        else if(pos == "F")
            holder.layout?.setBackgroundColor( Color.rgb(225, 230, 225))
        else if(pos == "G")
            holder.layout?.setBackgroundColor(Color.rgb(184, 247, 178))
        else if(pos == "C-F")
            holder.layout?.setBackgroundColor(Color.rgb(237, 175, 173))
        else if(pos == "G-F")
            holder.layout?.setBackgroundColor(Color.rgb(244, 245, 206))
        else
            holder.layout?.setBackgroundColor(Color.rgb(240, 192, 225))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
        val firstName: TextView = itemView.ime
        val surname: TextView = itemView.prezime
        val team: TextView = itemView.tim
        val layout: ConstraintLayout? = itemView.item_id_constraint_layout1

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position=adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
