package com.xingying.shopping.master.common.context;


import com.xingying.shopping.master.entity.ShopUser;

/**
 * 用户上下文
 * @author xueyanjun
 */
public class UserContext {

    private static ThreadLocal<ShopUser> HOLDER = new ThreadLocal<>();

    /**
     * 获取当前系统用户
     * @return 系统用户
     */
    public static ShopUser getCurrentUser(){
        return HOLDER.get();
    }

    /**
     * 设置当前系统用户
     * @return 系统用户
     */
    public static void setCurrentUser(ShopUser user){
        HOLDER.set(user);
    }

    /**
     * 设置当前系统用户
     * @return 系统用户
     */
    public static void clearCurrentUser(){
        HOLDER.remove();
    }

}
