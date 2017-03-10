package com.tao.mychat.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AMOBBS on 2017/2/6.
 */


public class SpUtils {
    private  static SharedPreferences sp;
    //写
    /**
     *
     * @param ctx 上下文环境
     * @param key 存储节点名称
     * @param value 存储节点的值 boolean
     */
    public static void putBoolean(Context ctx, String key, boolean value){
        //参数  1  存储节点文件名称, 2读写方式
        if(sp==null){
            sp=ctx.getSharedPreferences("comfig",Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }
    /**
     *
     * @param ctx 上下文环境
     * @param key 存储节点名称
     * @param defValue 没有此存储节点的默认值
     * @return  默认值或者此节点读取的结果
     */
    //读
    public static boolean getBoolean(Context ctx,String key,boolean defValue){
        //参数  1  存储节点文件名称, 2读写方式
        if(sp==null){
            sp=ctx.getSharedPreferences("comfig",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }
    /**
     *
     * @param ctx 上下文环境
     * @param key 存储节点名称
     * @param value 存储节点的值 boolean
     */
    public static void putString(Context ctx,String key,String value){
        //参数  1  存储节点文件名称, 2读写方式
        if(sp==null){
            sp=ctx.getSharedPreferences("comfig",Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }
    /**
     *
     * @param ctx 上下文环境
     * @param key 存储节点名称
     * @param defValue 没有此存储节点的默认值
     * @return  默认值或者此节点读取的结果
     */
    //读
    public static String getString(Context ctx,String key,String defValue){
        //参数  1  存储节点文件名称, 2读写方式
        if(sp==null){
            sp=ctx.getSharedPreferences("comfig",Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }



    /**
     *
     * @param ctx 上下文环境
     * @param key 存储节点名称
     * @param value 存储节点的值 boolean
     */
    public static void putInt(Context ctx,String key,int value){
        //参数  1  存储节点文件名称, 2读写方式
        if(sp==null){
            sp=ctx.getSharedPreferences("comfig",Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }
    /**
     *
     * @param ctx 上下文环境
     * @param key 存储节点名称
     * @param defValue 没有此存储节点的默认值
     * @return  默认值或者此节点读取的结果
     */
    //读
    public static int getInt(Context ctx,String key,int defValue){
        //参数  1  存储节点文件名称, 2读写方式
        if(sp==null){
            sp=ctx.getSharedPreferences("comfig",Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);

    }
    /**
     * 移除指定节点
     * @param ctx
     * @param key
     */
    public static void remove(Context ctx, String key) {
        if(sp==null){
            sp=ctx.getSharedPreferences("comfig",Context.MODE_PRIVATE);
        }
        sp.edit().remove(key);
    }
}
