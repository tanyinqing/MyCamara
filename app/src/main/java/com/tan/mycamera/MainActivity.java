package com.tan.mycamera;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import glidedisanfang.tyq.com.tanaar_171207.Constant;
import glidedisanfang.tyq.com.tanaar_171207.activity.TitleBarActivity;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse_download.Proch_lists_download;
import glidedisanfang.tyq.com.tanaar_171207.listener.DataListener;
import glidedisanfang.tyq.com.tanaar_171207.model.BaseModel;
import glidedisanfang.tyq.com.tanaar_171207.util.ImagesUtil_me;
import glidedisanfang.tyq.com.tanaar_171207.util.MyLog;
import glidedisanfang.tyq.com.tanaar_171207.util.PublicUtil;
import glidedisanfang.tyq.com.tanaar_171207.web.ShipmentWeb;
import glidedisanfang.tyq.com.tanaar_171207.widget.MyDialog;


@ContentView(R.layout.activity_main)
public class MainActivity extends TitleBarActivity {

    @ViewInject(R.id.img_phone)
    private ImageView img_phone;
    private String img_phoneURL="";
    private MobanModel mMobanModel = null;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    // 创建一个以当前时间为名称的文件
    private File tempFile = new File(Environment.getExternalStorageDirectory(), "tan.jpg");
    private static ImageLoader mImageLoader;
    @Override
    protected void RightButtonClicked() {

    }

    @Override
    protected void LeftButtonClicked() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initDatas() {
        setTitle("拍照");

        if (mMobanModel == null) {
            mMobanModel = new MobanModel(this);
        }
        mImageLoader = PublicUtil.getImageLoader();

        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径  完成保存的过程
        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        //PHOTO_REQUEST_TAKEPHOTO   这个是requestcode参数  本Activity得到图片
        startActivityForResult(intent2, PHOTO_REQUEST_TAKEPHOTO);
    }

    public void btn_look(View view) {
        mImageLoader.displayImage(img_phoneURL,img_phone,PublicUtil.getGoodsOption());
    }
    public void btn_camera(View view) {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径  完成保存的过程
        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        //PHOTO_REQUEST_TAKEPHOTO   这个是requestcode参数  本Activity得到图片
        startActivityForResult(intent2, PHOTO_REQUEST_TAKEPHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (requestCode) {
            //从本地得到图片的Uri 并进行剪切
            case PHOTO_REQUEST_TAKEPHOTO:
                if (resultCode == RESULT_OK) {
                    //保存拒签图片的地址

                    final MyDialog dialog = new MyDialog(MainActivity.this);
                    final EditText editText=dialog.getCurrentEditText();
                    dialog .setTitle("提示")
                            .setEditText("请输入照片名字!", InputType.TYPE_TEXT_FLAG_MULTI_LINE, new InputFilter[]{new InputFilter.LengthFilter(100)})
                            .setPositiveButton(R.string.ok1,
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View arg0) {

                                            mMobanModel.saveImage(editText.getText().toString().trim());
                                        }
                                    });
                    dialog.setNegativeButton(R.string.cancel,
                            new View.OnClickListener() {

                                @Override
                                public void onClick(View arg0) {
                                    dialog.dismiss();
                                }
                            });
                    dialog.create(null).show();
                } else {
                    Toast.makeText(this, "图片已经取消", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //这个是主导器
    private class MobanModel<T> extends BaseModel {


        public MobanModel(Context mContext) {
            super(mContext);
        }

        private void saveImage(String phoneName) {

            //文件的保存路径
            String[] file_path = new String[2];
            file_path[0] = "谈银青";
            file_path[1] = "个人相册";

            // 获得外部SD卡,创建本应用程序的保存目录,保存相片  放在跟目录下
            File sdCard = Environment.getExternalStorageDirectory();
            String DATABASE_PATH = sdCard.getAbsolutePath() + "/" + file_path[0] + "/" + file_path[1];   //数据库的存储路径
            // 放在外部SD卡 的私有目录下
            // File sdCard =context.getExternalFilesDir("");  这个是默认的目录，将图片保存在内存卡的根目录下
            File photoDir = new File(DATABASE_PATH);

            if (!photoDir.exists()) {
                photoDir.mkdirs();
            }

            //文件命名的时间成分
            Calendar calendar = Calendar.getInstance();
            Date data = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyMMdd_HHmm");
            String time = format.format(data);
            String DATABASE_NAME = phoneName + time + ".jpg";


            InputStream is = null;
            FileOutputStream fos = null;
            try {
                AssetManager am = context.getAssets();  //管理器
                // 得到.db file
                File saveFile = new File(DATABASE_PATH, DATABASE_NAME);
                // 获取数据库本地资源
                is = new FileInputStream(tempFile);
                // 获取数据库输入流
                fos = new FileOutputStream(saveFile);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                img_phoneURL="file://"+saveFile.getAbsolutePath();
            } catch (Exception e) {
                Log.i("Exception", Log.getStackTraceString(e));
            } finally {
                try {
                    if (fos != null)
                        fos.close();
                    if (is != null)
                        is.close();
                } catch (Exception e) {
                    Log.i("Exception", Log.getStackTraceString(e));
                }
            }


        }
    }
}
