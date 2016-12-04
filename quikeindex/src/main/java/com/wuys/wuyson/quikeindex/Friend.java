package com.wuys.wuyson.quikeindex;

/**
 * Created by Wuyson on 2016/12/2.
 */

public class Friend implements Comparable<Friend>{
    private String name;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    private String pinyin;

    public Friend(String name) {
        this.name = name;
        //一开始就转好拼音
        setPinyin(PinYinUtil.getPinyin(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Friend friend) {
        return getPinyin().compareTo(friend.getPinyin());
    }
}
