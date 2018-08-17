package com.cyanelix.railwatch.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.cyanelix.railwatch.R
import com.cyanelix.railwatch.domain.Schedule

class ScheduleArrayAdapter(context: Context?, resource: Int, schedules: MutableList<Schedule>?) : ArrayAdapter<Schedule>(context, resource, schedules) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflateLayout(parent)

        val schedule = getItem(position)!!

        setTextInTextView(view, R.id.from_station, schedule.fromStation)
        setTextInTextView(view, R.id.to_station, schedule.toStation)
        setTextInTextView(view, R.id.days, getShortDatesList(schedule))
        setTextInTextView(view, R.id.schedule_start_time, schedule.startTime)
        setTextInTextView(view, R.id.schedule_end_time, schedule.endTime)

        if (schedule.state != "ENABLED") view.setBackgroundColor(Color.LTGRAY)

        return view
    }

    private fun inflateLayout(parent: ViewGroup?) =
            LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false)

    private fun setTextInTextView(view: View, resource: Int, value: String) {
        (view.findViewById(resource) as TextView).text = value
    }

    private fun getShortDatesList(schedule: Schedule): String {
        return schedule.days.joinToString(", ") { it.substring(0, 3) }
    }
}