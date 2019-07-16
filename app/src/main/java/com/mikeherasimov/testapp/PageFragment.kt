package com.mikeherasimov.testapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_page.*
import java.util.*


class PageFragment : Fragment() {

    private val pageNumber: Int by lazy { arguments!!.getInt(ARG_PAGE_NUMBER) }
    private val notificationIds = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPageNumber.text = pageNumber.toString()
        tvCreateNotification.setOnClickListener {
            val id = showNotification(
                context!!,
                getString(R.string.app_name),
                getString(R.string.notification_text, pageNumber)
            )
            notificationIds.add(id)
        }
    }

    fun dismissNotifications() {
        for (id in notificationIds) {
            NotificationManagerCompat.from(context!!).cancel(id)
        }
    }

    private fun showNotification(context: Context, title: String, text: String): Int {
        val notification = setupNotification(context, setupPendingIntent(context), title, text)
        createNotificationChannel(context)
        val notificationId = Random().nextInt()
        NotificationManagerCompat.from(context).notify(notificationId, notification)
        return notificationId
    }

    private fun setupPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(ARG_PAGE_NUMBER, pageNumber)
        return PendingIntent.getActivity(
            context, Random().nextInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun setupNotification(context: Context, pendingIntent: PendingIntent, title: String, text: String): Notification {
        return NotificationCompat.Builder(context, getString(R.string.notification_channel_id))
            .setSmallIcon(R.drawable.ic_notification)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_notification))
            .setColor(ContextCompat.getColor(context, R.color.colorAccent))
            .setContentTitle(title)
            .setContentText(text)
            .setGroup(getString(R.string.notification_group_key))
            .setChannelId(getString(R.string.notification_channel_id))
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle())
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                getString(R.string.notification_channel_id),
                getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.createNotificationChannelGroup(
                NotificationChannelGroup("group_id_1", getString(R.string.notification_group_name))
            )
        }
    }

    companion object {

        const val ARG_PAGE_NUMBER = "pageNumber"

        fun newInstance(pageNumber: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE_NUMBER, pageNumber)
            fragment.arguments = args
            return fragment
        }

    }

}
