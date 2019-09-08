package com.saeed.android.scoreline.model

import com.squareup.moshi.Moshi


/**
 * Created by Saeed on 2019-06-20.
 */
class Resource<out T>(val status: Status, val message: String?, val data: T?) {

    var messageHolder: MessageHolder? = null

    var moshi: Moshi = Moshi.Builder().build()

    init {
        messageHolder = message?.let {
            try {
                moshi.adapter(MessageHolder::class.java).fromJson(it) as MessageHolder
            } catch (e: Exception) {
                e.printStackTrace()
                MessageHolder(400, "BAD REQUEST", true)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource<*>

        if (status !== resource.status) {
            return false
        }
        if (if (message != null) message != resource.message else resource.message != null) {
            return false
        }
        return if (data != null) data == resource.data else resource.data == null
    }

    override fun toString(): String {
        return "Resource[" +
                "status=" + status + '\'' +
                ",message='" + message + '\'' +
                ",data=" + data +
                ']'
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (messageHolder?.hashCode() ?: 0)
        return result
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data, message = null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(status = Status.ERROR, data = data, message =msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(status = Status.LOADING, data = data, message = null)
        }
    }

}