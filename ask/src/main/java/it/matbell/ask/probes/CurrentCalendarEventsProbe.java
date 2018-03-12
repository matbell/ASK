/*
 * Copyright (c) 2017. Mattia Campana, m.campana@iit.cnr.it, campana.mattia@gmail.com
 *
 * This file is part of Android Sensing Kit (ASK).
 *
 * Android Sensing Kit (ASK) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Android Sensing Kit (ASK) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Android Sensing Kit (ASK).  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package it.matbell.ask.probes;

import java.util.List;

import it.matbell.ask.controllers.CalendarController;
import it.matbell.ask.model.CalendarEvent;
import it.matbell.ask.model.LoggableElements;

/**
 * @author Mattia Campana (m.campana@iit.cnr.it)
 *
 * This probe monitors the current events saved in the user's calendars.
 *
 * Requires:
 *
 *  - "android.permission.READ_CALENDAR"
 *
 */
@SuppressWarnings("unused")
class CurrentCalendarEventsProbe extends ContinuousProbe {

    @Override
    public void init() {}

    @Override
    public void onFirstRun() {}

    @Override
    void onStop() {}

    @Override
    public void exec() {

        List<CalendarEvent> events = CalendarController.getCurrentEvents(getContext());

        logOnFile(true, new LoggableElements(events));
    }
}
