package com.master.chapter11;

import java.io.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:转换流实例
 * @date 2023-09-13 16:17
 */
public class TransformerStreamTest {
    public static void main(String[] args) {
        TransformerStreamTest test=new TransformerStreamTest();
        test.transformerReadWriter();
    }
    //reader相当于是一个解码的操作 writer相当于是一个编码操作
    public void transformerReadWriter() {
        //字节输入流
        InputStreamReader reader = null;
        OutputStreamWriter writer=null;
        try {
            FileInputStream inputStream = new FileInputStream("name.txt");
            FileOutputStream outputStream=new FileOutputStream("name1.txt");
            //字节转换为字符的输入转换流                         使用系统默认的字符集, 解码集些什么，取决于当初村这个文件使用的是什么编码集
            reader = new InputStreamReader(inputStream, "UTF-8");
            //编码解码不一致会乱码
            writer= new OutputStreamWriter(outputStream,"GBK");
            char[] ch = new char[10];
            int len;
            while ((len = reader.read(ch)) != -1) {
                String str = new String(ch, 0, len);
                System.out.print(str);
                writer.write(ch,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer!=null)
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(reader!=null){}
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 转换流:
 *      从程序输入的角度看，可以把一个字节输入流转换为一个字符输入流 中间的桥梁InputStreamReader  解码
 *      然后输出的角度看 可以将字符流转换为原始的字节输出流 中间的桥梁 OutputStreamWriter   编码
 *      他们都属于字符流，处理流       涉及到编码 解码 字符集的问题
 */


/**
 * 关于编码集
 *      ascii:使用一个字节中的前7位表示
 *      iso8859-1:使用一个字节的8位表示
 *      gb2312:中文表示，使用两个字节 首位使用1表示使用两个字节 O表示使用一个字节表示的数据
 *      GBK:包含中文繁体，使用两个字节表示 首位使用1表示使用两个字节 0表示使用一个字节表示对额数据
 *      Unicode：使用两个字节的全部16位可以表示万国码，但是如何区分一个字节还是两个字节，如果参照gbk,使用0/1占位符来表示
 *          则实际上只能表示2^15个数字，无法表情是全世界范围的数字，所以这种方式不科学，还是需要使用16位置都表示字符
 *      UTF-8/16:变长的编码方式，使用1-4个字节表示一个字符，utf-8表示每次8个位传输数据，utf-16表示每次16位传输数据
 *          使用unicode万国码为每个字符编号，然后使用具体的传输编码方式（UTF-8/16）来传输字节流
 *          以UTF-8来举例: 普通的基础英文符号在unicode编码表中以16进制作为表示为16位（因为16禁止的每位表示2禁止的2位，一个就32位，4字节）
 *          在utf-8编码表示中：使用0xxxxxxx 这种一个字节表示
 *                         普通的稍微长一点的超过一位的使用两位表示：110xxxxx 10xxxxxx
 *                         汉字一般在unicode中有两个字节，比如：12345678 11234567 则在UTF-8中使用三位表示
 *                              1110    1234   10    567811  10 234567
 *                              1----------8   1----------8  1-------8
 *                              首位使用多少个1表示有多少个字节，每个字节开通头都是用10位置开始
 *         超出基本语言范围的字符变编码为四个字节，在修正的UTF-8中需要6个字节
 */