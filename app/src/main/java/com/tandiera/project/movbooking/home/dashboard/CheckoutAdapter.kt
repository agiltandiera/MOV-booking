package com.tandiera.project.movbooking.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.model.Checkout
import com.tandiera.project.movbooking.model.Film
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener:(Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CheckoutAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ComingSoonAdapter.LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    // class viewholder
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.findViewById<TextView>(R.id.tv_kursi)
        private val tvHarga = view.findViewById<TextView>(R.id.tv_harga)

        private val tvImage = view.findViewById<ImageView>(R.id.iv_poster_image)

        fun bindItem(data: Checkout, listener: (Film) -> Unit, context: Context) {

            val localID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if(data.kursi!!.startsWith("Total")) {
                tvTitle.setText(data.kursi)
                tvTitle.setCompoundDrawables(null, null, null, null)
            } else {
                tvTitle.setText("Seat No. " + data.kursi)
//                tvHarga.setText(data.harga)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}