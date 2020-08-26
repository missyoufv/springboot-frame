package cn.duw.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 编码：ASCII码 美国制定了一套字符编码，对英语字符与二进制位之间的关系，做了统一规定。这被称为ASCII码，一直沿用至今 后续出现各种码表 也是兼容的
 *       不同国家、不同编码，因此出现unicode编码 ，Unicode（统一码、万国码、单一码）,存在问题，固定长度，浪费空间，如果不是固定长度，怎么区分问题一：
 *
 *          第一个问题是
 *              计算机怎么知道三个字节表示一个符号，而不是分别表示三个符号呢？
 *          第二个问题是
 *              我们已经知道，英文字母只用一个字节表示就够了，如果Unicode统一规定，每个符号用三个或四个字节表示，那么每个英文字母前都必然有二到三个字节是0，
 *              这对于存储来说是极大的浪费，文本文件的大小会因此大出二三倍，这是无法接受的。
 *
 *       UTF-8
 *          nicode编码长度是固定的，无论是数字、英文还是火星文。所以Unicode编码有点浪费空间。UTF8是针对unicode的空间浪费现象，它对字符的长度是动态的。
 *          UTF-8的编码规则很简单，只有二条：
 *              1）对于单字节的符号，字节的第一位设为0，后面7位为这个符号的unicode码。因此对于英语字母，UTF-8编码和ASCII码是相同的。
 *              2）对于n字节的符号（n>1），第一个字节的前n位都设为1，第n+1位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 *
 *          例如汉字严 11100100 10111000 10100101
 */
@SpringBootApplication
public class IOApplication {

    public static void main(String[] args) {
        SpringApplication.run(IOApplication.class);
    }
}
