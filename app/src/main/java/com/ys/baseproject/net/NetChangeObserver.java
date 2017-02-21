package com.ys.baseproject.net;

import com.ys.baseproject.utils.NetUtils;

/**

 * 网络改变观察者，观察网络改变后回调的方法

 * Created by deng on 16/9/13.

 */

public interface NetChangeObserver {



    /**

     * 网络连接回调 type为网络类型

     */

    void onNetConnected(NetUtils.NetType type);



    /**

     * 没有网络

     */

    void onNetDisConnect();

}
