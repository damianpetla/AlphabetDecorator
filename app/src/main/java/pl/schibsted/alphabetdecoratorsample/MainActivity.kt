package pl.schibsted.alphabetdecoratorsample

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.dip
import pl.schibsted.alphabetdecorator.AlphabetDecorator
import pl.schibsted.alphabetdecorator.TextHolder
import kotlin.properties.Delegates

public class MainActivity : Activity() {

    val recyclerView: RecyclerView by Delegates.lazy { findViewById(R.id.recyclerView) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(MyAdapter(this))
        recyclerView.addItemDecoration(AlphabetDecorator(dip(16)))
    }

    class MyAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val items = 0..49

        private fun Int.toText() : String{
            return when (this) {
                in 0..9 -> "A"
                in 10..19 -> "B"
                20 -> "C"
                in 21..29 -> "D"
                in 30..39 -> "E"
                in 40..49 -> "F"
                else -> "X"
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            val h = holder as SimpleViewHolder
            h.textView.setText("${position.toText()} Position $position")
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup?, position: Int): RecyclerView.ViewHolder? {
            return SimpleViewHolder(LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false))
        }

        override fun getItemCount(): Int {
            return items.count()
        }

    }

    class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view), TextHolder {
        override val textView = view as TextView
    }

}
