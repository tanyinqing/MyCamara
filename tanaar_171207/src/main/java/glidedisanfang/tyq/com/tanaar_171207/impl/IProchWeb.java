package glidedisanfang.tyq.com.tanaar_171207.impl;
import android.os.Handler;

import java.util.List;

import glidedisanfang.tyq.com.tanaar_171207.entity.User;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse.Good;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse.Porch_info;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse.Proch_all;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse.Purchase_lists;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse.Purchase_stop;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse_download.Proch_lists_download;
import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse_download.Statistics_pur;
import glidedisanfang.tyq.com.tanaar_171207.listener.DataListener;

public interface IProchWeb {
	/**
	 * 采购申请
	 * @param
	 */
	public void proch_lists(String proch_status,String start,String page_num,String proch_order_sn, final DataListener<Proch_lists_download> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void porch_info(String prochsn,final DataListener<Porch_info> dataListener);
	/**
	 *
	 * @param
	 */
	public void proch_passed(final String prochsn, final Handler handler);
	/**
	 *
	 * @param
	 */
	public void proch_reject(final String prochsn,final String note,final  Handler handler);
	/**
	 *
	 * @param dataListener
	 */
	public void proch_all(final DataListener<List<Proch_all>> dataListener);
	/**
	 *
	 * @param dataListener
	 */
	public void proch_start(String sku,final DataListener<String> dataListener);/**
	 *
	 * @param dataListener
	 */
	public void purchase_lists(final DataListener<List<Purchase_lists>> dataListener);
	/*
	 * @param dataListener
	 */
	public void purchase_info(String prochsn,final DataListener<List<Good>> dataListener);
		/*
	 * @param dataListener
	 */
	public void purchase_stop(final String prochsn,final String sku,final  Handler handler);	/*
	 * @param dataListener
	 */
	public void proch_search(String name,final DataListener<List<Good>> dataListener);	/*
	 * @param dataListener
	 */
	public void statistics_pur(String beginTime,String endTime,String sku,String goods_id,String goods_type,final DataListener<Statistics_pur> dataListener);



}
