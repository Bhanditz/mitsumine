package me.kirimin.mitsumine.mybookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import me.kirimin.mitsumine.R
import me.kirimin.mitsumine._common.domain.model.MyBookmark

class MyBookmarkSearchAdapter(context: Context,
                              private val onMyBookmarkClick: (v: View, myBookmark: MyBookmark) -> Unit,
                              private val onMyBookmarkLongClick: (v: View, myBookmark: MyBookmark) -> Unit) : ArrayAdapter<MyBookmark>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_my_bookmarks, null)
            holder = ViewHolder(view.findViewById(R.id.card_view),
                    view.findViewById(R.id.MyBookmarksTitleTextView) as TextView,
                    view.findViewById(R.id.MyBookmarksContentTextView) as TextView,
                    view.findViewById(R.id.MyBookmarksUsersTextView) as TextView,
                    view.findViewById(R.id.MyBookmarksUrlTextView) as TextView)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        val bookmark = getItem(position)
        holder.cardView.tag = bookmark
        holder.cardView.setOnClickListener { v -> onMyBookmarkClick(v, v.tag as MyBookmark) }
        holder.cardView.setOnLongClickListener { v ->
            onMyBookmarkLongClick(v, v.tag as MyBookmark)
            false
        }
        holder.title.text = bookmark.title
        holder.contents.text = bookmark.snippet
        holder.userCount.text = bookmark.bookmarkCount.toString() + context.getString(R.string.users_lower_case)
        holder.url.text = bookmark.linkUrl
        return view
    }

    class ViewHolder(
            val cardView: View,
            val title: TextView,
            val contents: TextView,
            val userCount: TextView,
            val url: TextView) {
    }
}
