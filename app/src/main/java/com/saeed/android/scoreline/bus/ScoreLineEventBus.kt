package com.saeed.android.scoreline.bus

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by Saeed on 2019-07-21.
 */
object ScoreLineEventBus {

    private val listeners = BehaviorSubject.create<Event>()

    fun listen(event: Class<Event>): Observable<Event> = listeners.ofType(event)

    fun publish(event: Event) {
        event.let { listeners.onNext(it) }
    }

}