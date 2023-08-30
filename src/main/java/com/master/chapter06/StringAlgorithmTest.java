package com.master.chapter06;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 一些String的算法
 * @date 2023-08-30 9:32
 */
public class StringAlgorithmTest {
    public static void main(String[] args) {
        StringAlgorithmTest test=new StringAlgorithmTest();
        String str = test.reverse("abcdefghij", 2, 7);
        System.out.println(str);
        String colorXJH = test.reverse1("ColorXJH", 2, 5);
        System.out.println(colorXJH);
        int count = test.getCount("abababccsdsdaabababa", "ab");
        System.out.println(count);
        List<String> list = test.getMaxSameString("ColorXJH123", "XJHsss123");
        list.stream().forEach((l)->{
            System.out.println(l);
        });

    }

    /**
     * 将一个字符串反转,将字符串指定的部分经行反转 如 abcdefg  => abfedcg
     * 方式1:转换为char[]
     */
    public String reverse(String str,int startIndex,int endIndex){
        char[] chars = str.toCharArray();
        for (int x=startIndex,y=endIndex;x<y;x++,y--){
            char temp=chars[x];
            chars[x]=chars[y];
            chars[y]=temp;
        }
        return new String(chars);
    }
    /**
     * 方式二
     * StringBuilder 类似拼接操作
     */
    public String reverse1(String str,int startIndex,int endIndex){
        StringBuilder sb=new StringBuilder(str.length());
        //第一部分
        String part1=str.substring(0,startIndex);
        sb.append(part1);
        //第二部分
        for (int i = endIndex; i>=startIndex ; i--) {
            sb.append(str.charAt(i));
        }
        //第三部分
        sb.append(str.substring(endIndex+1));
        return sb.toString();

    }


    /**
     * 获取一个字符串，在另一个字符串中出现的次数
     * 比如“ab”在“abcdefaccababrtie”中出现的次数
     */
    public int getCount(String mainStr,String subStr){
        int count=0;
        int index=0;
        if(mainStr.length()>=subStr.length()){
            //方式1：
//            while((index=mainStr.indexOf(subStr))!=-1){
//                count++;
//                mainStr=mainStr.substring(index+subStr.length());
//            }
            //方式2
            while ((index=mainStr.indexOf(subStr,index))!=-1){
                count++;
                index+=subStr.length();
            }
            return count;
        }else{
            return 0;
        }
    }


    /**
     * 获取两个字符串中最大相同字串
     * “ColorXJH”  "rXJH"
     * 短的字串藏毒依次递减与较长串的比较
     * 暂时只考虑只有一个相同字串的情况
     * 多个的情况下需要使用集合将多个字串添加进去
     */
    public List<String> getMaxSameString(String str1, String str2){
        List<String>list=new ArrayList<>();
        String maxStr=(str1.length()>=str2.length())?str1:str2;
        String minStr=(str1.length()<str2.length())?str1:str2;
        //大轮
        int length=minStr.length();
        for (int i=0;i<length;i++){
            //x,y指向字串首尾
            for(int x=0,y=length-i;y<=length;x++,y++){
                String substring = minStr.substring(x, y);
                if(maxStr.contains(substring)){
                    list.add(substring);
                }
            }
            //找到做大的一轮就退出
            if(list.size()>=1){
                break;
            }
        }
        return list;
    }


}


