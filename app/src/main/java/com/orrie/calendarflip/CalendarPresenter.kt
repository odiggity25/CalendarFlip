package com.orrie.calendarflip

/**
 * Created by orrie on 2017-07-28.
 */
class CalendarPresenter(view: CalendarView) {

    private var day = 0
    init {
        view.animates.subscribe {
            day ++
            view.animateToDay(day % 3)
        }
    }
}