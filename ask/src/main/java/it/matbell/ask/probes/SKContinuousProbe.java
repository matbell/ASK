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

/**
 * This class represents a continuous probe, which means that the exec() method is called every
 * <interval> seconds.
 *
 * Parameters:
 *  - "interval" : (requires) number of seconds between two consecutive executions of the probe.
 *
 */
public abstract class SKContinuousProbe extends SKBaseProbe {

    private int interval;

    public void setInterval(int interval){this.interval = interval;}
    public int getInterval(){return interval;}

    @Override
    public abstract void init();

    @Override
    public abstract void onFirstRun();

    @Override
    abstract void onStop();

    public abstract void exec();
}
