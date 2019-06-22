package com.saeed.android.scoreline.db.typeconverter

import androidx.room.TypeConverter
import com.saeed.android.scoreline.model.*
import com.squareup.moshi.Moshi


/**
 * Created by Saeed on 2019-06-21.
 */
class Converter {

    var moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromSeason(season: Season): String {
        return moshi.adapter(Season::class.java).toJson(season)
    }

    @TypeConverter
    fun toSeason(season: String): Season? {
        return moshi.adapter(Season::class.java).fromJson(season)
    }

    @TypeConverter
    fun fromSubScore(subScore: SubScore): String {
        return moshi.adapter(SubScore::class.java).toJson(subScore)
    }

    @TypeConverter
    fun toSubScore(subScore: String): SubScore? {
        return moshi.adapter(SubScore::class.java).fromJson(subScore)
    }

    @TypeConverter
    fun fromScore(score: Score): String {
        return moshi.adapter(Score::class.java).toJson(score)
    }

    @TypeConverter
    fun toScore(subScore: String): Score? {
        return moshi.adapter(Score::class.java).fromJson(subScore)
    }

    @TypeConverter
    fun fromMatchTeam(matchTeam: MatchTeam): String {
        return moshi.adapter(MatchTeam::class.java).toJson(matchTeam)
    }

    @TypeConverter
    fun toMatchTeam(matchTeam: String): MatchTeam? {
        return moshi.adapter(MatchTeam::class.java).fromJson(matchTeam)
    }

    @TypeConverter
    fun fromArea(area: Area): String {
        return moshi.adapter(Area::class.java).toJson(area)
    }

    @TypeConverter
    fun toArea(area: String): Area? {
        return moshi.adapter(Area::class.java).fromJson(area)
    }

    @TypeConverter
    fun fromCompetition(competition: Competition): String {
        return moshi.adapter(Competition::class.java).toJson(competition)
    }

    @TypeConverter
    fun toCompetition(competition: String): Competition? {
        return moshi.adapter(Competition::class.java).fromJson(competition)
    }
}