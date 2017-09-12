package com.xiasuhuei321.gank_kotlin.datasource

import com.xiasuhuei321.gank_kotlin.datasource.bean.GankData
import io.reactivex.Observable


/**
 * Created by CoderFan on 2017/9/12.
 * desc:
 */
interface DataSource{

    //获取Data
    fun getData(type:String): Observable<List<GankData>>

    //获取服务器数据，每页数据默认为10, 页码默认为1
    fun getRemoteData(type: String,pageIndex:Int = 1,count:Int = 10):Observable<List<GankData>>

    //清除本地指定缓存
    fun clearData(type: String)

    //清除本地所有缓存
    fun clearAllData()
}
