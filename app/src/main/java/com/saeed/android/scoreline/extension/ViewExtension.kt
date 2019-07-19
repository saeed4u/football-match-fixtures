package com.saeed.android.scoreline.extension

import android.view.View
import com.saeed.android.scoreline.model.Resource
import com.saeed.android.scoreline.model.Status
import org.jetbrains.anko.toast


/**
 * Created by Saeed on 2019-07-19.
 */
fun View.bindResource(resource: Resource<Any>, success: () -> Unit) {
    when (resource.status) {
        Status.LOADING -> Unit
        Status.SUCCESS -> success()
        Status.ERROR -> this.context.toast(resource.messageHolder?.message!!)
    }
}