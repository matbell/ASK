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

package it.matbell.ask.controllers;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.matbell.ask.model.CalendarEvent;
import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calendar.Event;
import me.everything.providers.android.calendar.Instance;

public class CalendarController {

    public static List<CalendarEvent> getAllEvents(Context context){

        List<CalendarEvent> events = new ArrayList<>();
        CalendarProvider provider = new CalendarProvider(context);

        for(Calendar calendar : provider.getCalendars().getList()){

            for(Event event : provider.getEvents(calendar.id).getList()){

                events.add(new CalendarEvent(
                        calendar.name, event.dTStart, event.dTend, event.title,
                        event.eventLocation, event.allDay));
            }
        }

        return events;
    }

    public static List<CalendarEvent> getCurrentEvents(Context context){

        CalendarProvider provider = new CalendarProvider(context);
        List<CalendarEvent> events = new ArrayList<>();

        for(Instance instance : provider.getInstances(new Date().getTime(),
                new Date().getTime()).getList()){

            events.add( new CalendarEvent(provider.getEvent(instance.eventId)));
        }

        return events;
    }

    public static boolean isUserBusy(Context context){

        CalendarProvider provider = new CalendarProvider(context);

        return provider.getInstances(new Date().getTime(), new Date().getTime())
                .getList().size() > 0;

    }
}
