package glidedisanfang.tyq.com.tanaar_171207.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import glidedisanfang.tyq.com.tanaar_171207.R;
import glidedisanfang.tyq.com.tanaar_171207.ServiceApplication;
import glidedisanfang.tyq.com.tanaar_171207.database.DevelopDB;
import glidedisanfang.tyq.com.tanaar_171207.model.BaseModel;
import glidedisanfang.tyq.com.tanaar_171207.service.AutoCleanService;
import glidedisanfang.tyq.com.tanaar_171207.util.LogTxt;
import glidedisanfang.tyq.com.tanaar_171207.util.PublicUtil;

public class DevelopActivity extends Activity {
    private DevelopDB developDB;
    private DevelopModel developModel;
    private TextView tv_information;
    private ListView list_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_develop);
        if (developModel == null) {
            developModel=new DevelopModel(this);
        }
        developDB=new DevelopDB(this);
        tv_information= (TextView) findViewById(R.id.tv_information);
        list_data= (ListView) findViewById(R.id.list_data);
    }

    public void onClick(View view){
        int i = view.getId();
        if (i == R.id.btn_word_delete) {
            developDB.delete_work();
            developModel.delete_file();
            PublicUtil.ShowToast("work表清除成功");
        }else if (i == R.id.kill_process) {
            startService(new Intent(this,AutoCleanService.class));
            PublicUtil.ShowToast("清除进程的服务启动成功");
        }else if (i == R.id.delete_db) {
            if ( developDB.delete_db(this)) {
                PublicUtil.ShowToast("数据库删除成功");
            }
        }else if (i == R.id.cache_data) {
            tv_information.setText(ServiceApplication.getInstance().getPreferenceUtil().getAllDate());
                PublicUtil.ShowToast("缓存数据显示完毕");
        }else if (i == R.id.list_datas) {
            list_data.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item_data, developDB.getAlarmList()));
                PublicUtil.ShowToast("数据库数据显示完毕");
        }
    }

    private class DevelopModel<T> extends BaseModel {

        public DevelopModel(Context mContext) {
            super(mContext);
        }

        public  void delete_file() {
            File sdCard= Environment.getExternalStorageDirectory();
            //这种是目录的表达方式 文件的表达方式是不一样的
            File file=new File(sdCard.getAbsolutePath()+"/"+"工作跟踪");
            if (file.exists()) {
                LogTxt.delete(file);
            }
        }

    }
}
