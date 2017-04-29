package me.kirimin.mitsumine.feed.keyword

import android.os.Bundle

import me.kirimin.mitsumine.feed.AbstractFeedUseCase
import me.kirimin.mitsumine.feed.AbstractFeedFragment

public class KeywordSearchFragment : AbstractFeedFragment() {

    companion object {
        public fun newFragment(keyword: String): KeywordSearchFragment {
            val fragment = KeywordSearchFragment()
            val bundle = Bundle()
            bundle.putString("keyword", keyword)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getRepository(): AbstractFeedUseCase = KeyWordSearchUseCase(context, arguments.getString("keyword"))

    override fun isUseReadLater(): Boolean = true

    override fun isUseRead(): Boolean = true
}