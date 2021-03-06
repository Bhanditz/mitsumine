package me.kirimin.mitsumine._common.database

import java.util.Calendar
import java.util.Locale

import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import me.kirimin.mitsumine._common.database.entity.FeedTableEntity
import me.kirimin.mitsumine._common.domain.model.Feed

object FeedDAO {

    /**
     * データを保存・更新する
     */
    fun save(feed: Feed) {
        delete(feed = feed)
        val cal = Calendar.getInstance(Locale.JAPAN)
        feed.saveTime = cal.time.time
        FeedTableEntity(feed).save()
    }

    /**
     * 全ての要素を取得する
     */
    fun findAll(): List<Feed> {
        return Select().from(FeedTableEntity::class.java).execute<FeedTableEntity>().map(FeedTableEntity::toModel)
    }

    /**
     * 指定したタイプの全ての要素を取得する
     */
    fun findByType(type: String): List<Feed> {
        return Select().from(FeedTableEntity::class.java).where("type = ?", type).orderBy("saveTime DESC").execute<FeedTableEntity>().map(FeedTableEntity::toModel)
    }

    /**
     * 古いデータを削除する
     */
    fun deleteOldData(days: Int) {
        val cal = Calendar.getInstance(Locale.JAPAN)
        val milliseconds = 1000 * 60 * 60 * 24 * days
        Delete().from(FeedTableEntity::class.java).where("saveTime < ? AND type = ?", cal.time.time - milliseconds, Feed.TYPE_READ).execute<FeedTableEntity>()
    }

    private fun delete(feed: Feed) = Delete()
            .from(FeedTableEntity::class.java)
            .where("linkUrl = ?", feed.linkUrl)
            .execute<FeedTableEntity>()
}
