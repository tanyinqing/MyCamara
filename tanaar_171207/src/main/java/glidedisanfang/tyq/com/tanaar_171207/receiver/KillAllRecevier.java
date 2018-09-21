package glidedisanfang.tyq.com.tanaar_171207.receiver;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import glidedisanfang.tyq.com.tanaar_171207.entity.TaskInfo;
import glidedisanfang.tyq.com.tanaar_171207.util.MyLog;
import glidedisanfang.tyq.com.tanaar_171207.util.TaskInfoProvider;

/**
 * 杀死后台的进程的广播
 */
public class KillAllRecevier extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		for (TaskInfo taskInfo: TaskInfoProvider.getTaskInfos(context)){
			if (taskInfo.isUserTask()) {
				MyLog.ShowLog("关闭进程前" + taskInfo.toString());
				ActivityManager activityManager= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
				activityManager.killBackgroundProcesses(taskInfo.getPackname());
			}
		}
	}

}
