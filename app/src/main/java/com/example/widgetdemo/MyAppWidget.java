package com.example.widgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidget extends AppWidgetProvider {
    public static final String CLICK_ACTION = "com.example.widgetdemo.MyAppWidget.click";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("TAG","onReceive ");
        if (CLICK_ACTION.equals(intent.getAction())){
            //跳转
            Intent it = new Intent(context,MainActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(it);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int id : appWidgetIds){
            updateAppWidget(context, appWidgetManager, id);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int id) {
        Intent intent = new Intent(context,MyAppWidget.class);//当前receiver
        intent.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.my_widget);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        appWidgetManager.updateAppWidget(id,views);

    }
}
