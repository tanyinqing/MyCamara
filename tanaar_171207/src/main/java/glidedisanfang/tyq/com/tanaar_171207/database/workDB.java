package glidedisanfang.tyq.com.tanaar_171207.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import glidedisanfang.tyq.com.tanaar_171207.entity.Work;
import glidedisanfang.tyq.com.tanaar_171207.entity.moban_databass_entity;

public class workDB {
	//点赞数据表
	@SuppressWarnings("unused")
	private Context context;
	private DBHelper helper;

	public workDB(Context context) {
		// 数据库的初始化
		helper = new DBHelper(context, "WorkTracking.db", null, DBHelper.DATABASE_VERSION);
		this.context = context;

	}

	//插入一个对象
	public void insertAlarm(Work work) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("data", work.getData());
		values.put("function", work.getFunction());
		values.put("time", work.getTime());
		values.put("image_url", work.getImage_url());
		db.insert("work", null, values);
		db.close();
	}

	/*判断是否存在 存在返回true 不存在返回false*/
	public boolean isHave(String date,String function) {
		boolean isHave = false;
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query("work", new String[] {"id"}, "data=? and function=?",new String[] {date,function}, null, null, null);
		if(cursor.moveToFirst() == false){
			isHave = false;
		}else{
			isHave = true;
		}
		cursor.close();
		db.close();
		return isHave;
	}

	//最后一个添加的数据的id  保存到其他表中作为外键
    public int getLastId(){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("work", new String[] { "id"}, null, null, null, null, null);
        cursor.moveToLast();
        int lastId = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();
        db.close();
        return lastId;

    }

	//得到该表中的所有数据
	public ArrayList<Work> getAlarmList() {
		SQLiteDatabase db = helper.getWritableDatabase();
		ArrayList<Work> alarmArray = new ArrayList<Work>();
		Cursor cursor = db.query("work", new String[] { "*"}, null, null, null, null, null);
		//将cursor对应的值放入链表集合中
		cursorMethod(alarmArray, cursor);
		cursor.close();
		db.close();
		return alarmArray;
	}
	//将cursor对应的值放入链表集合中
	private void cursorMethod(ArrayList<Work> alarmArray, Cursor cursor) {
		while (cursor.moveToNext()) {
			Work alarm = new Work();
			alarm.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
			alarm.setData(cursor.getString(cursor.getColumnIndex("U_Id")));
			alarm.setFunction(cursor.getString(cursor.getColumnIndex("Date")));
			alarm.setImage_url(cursor.getString(cursor.getColumnIndex("Content")));
			alarm.setTime(cursor.getString(cursor.getColumnIndex("IT_Number")));
			alarmArray.add(alarm);
		}
	}


	//根据goodId进行删除操作
	public void delete(String id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("RiZhi", "id=?", new String[]{id});
		db.close();
	}

	/**
	 * 删除这个数据表的数据
	 */
	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("RiZhi", null, null);
		db.close();
	}
	
}