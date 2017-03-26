package com.skyseraph.xknife.lib.utils.nomal;

/**
 * Created by SkySeraph on 2015/3/17.
 * 实现或继承此接口的类，其共有属性和方法将不参与混淆
 * Use:
 -keep public interface test.ko.utils.nomal.NotProguardInterface{public *;}
 -keep class * implements test.ko.utils.nomal.NotProguardInterface{
 <methods>;
 <fields>;
 }
 */

public interface NotProguardInterface {
}
