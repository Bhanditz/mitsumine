package me.kirimin.mitsumine.bookmarklist

import me.kirimin.mitsumine._common.domain.model.Bookmark
import me.kirimin.mitsumine.bookmarklist.BookmarkListView

class BookmarkListPresenter {

    var view: BookmarkListView? = null

    fun onCreate(bookmarkListView: BookmarkListView, bookmarks: List<Bookmark>){
        this.view = bookmarkListView
        bookmarkListView.initViews(bookmarks)
    }

    fun onItemClick(bookmark: Bookmark) {
        view?.startUserSearchActivity(bookmark.user)
    }
}