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

package it.matbell.ask.model;

import android.content.Context;

import java.util.Arrays;

import it.matbell.ask.commons.Utils;
import it.matbell.ask.controllers.ContactsController;

public class Sms {

    private String address, body;
    private long timestamp;
    private boolean containsUrl;
    private boolean fromAddressBook;
    private boolean sent;
    private long id;

    public Sms(Context context, me.everything.providers.android.telephony.Sms sms, boolean sent){

        this.id = sms.id;
        this.address = sms.address;
        this.timestamp = sms.sentDate;
        this.sent = sent;
        this.body = sms.body;
        this.containsUrl = sms.body.contains("http://") || sms.body.contains("www.");
        this.fromAddressBook = ContactsController.getNameFromNumber(context, sms.address) != null;
    }

    public long getId(){return id;}

    public Object[] getDataToPrint(){

        return Arrays.asList(timestamp, address, sent, containsUrl, fromAddressBook).toArray();
    }

    @Override
    public String toString(){
        return Utils.formatLogOutput(timestamp, address, body, sent, containsUrl, fromAddressBook);
    }
}
