package com.wuys.wuyson.quikeindex;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Wuyson on 2016/12/2.
 */

public class PinYinUtil {
    /**
     *
     * @param chinese
     * @return
     */
    public static String getPinyin(String chinese){
        if(TextUtils.isEmpty(chinese)) return null;

        //用来转化拼音的大小写，或者声调
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//设置转化的拼音为大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不带声调

        //1.需要将字符串转化字符数组，然后对每个字符转化，最后拼接起来
        char[] charArray = chinese.toCharArray();
        String pinyin = "";

        for(int i=0;i<charArray.length;i++){
            //2.过滤空格
            if(Character.isWhitespace(charArray[i])) continue;
            //3.判断是否是汉字，一个汉字两个字节，一个字的范围是-128~127
            if(charArray[i]>127){
                try {
                    //由于多音字，dan和shan （单）
                    String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(charArray[i],format);
                    if(pinyinArr != null){
                        pinyin +=pinyinArr[0];//如果多音，取第一个
                    }else {

                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                    //说明转化失败，不是汉字，则忽略
                }
            }else {
                //不是汉字，可以直接拼接
                pinyin +=charArray[i];
            }
        }
        return pinyin;
    }

}
