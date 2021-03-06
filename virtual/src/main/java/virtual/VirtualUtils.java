package virtual;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 李可乐 on 2017/4/18.
 */

public class VirtualUtils {


  private static ThreadLocalRandom getRandomInstance() {
    return ThreadLocalRandom.current();
  }

  public static int getInt(int bound) {
    return getRandomInstance().nextInt(bound);
  }

  public static int getInt(int begin, int end) {
    return getRandomInstance().nextInt(begin, end);
  }

  public static long getLong(int bound) {
    return getRandomInstance().nextLong(bound);
  }

  public static boolean getBoolean() {
    return getRandomInstance().nextBoolean();
  }

  public static float getFloat() {
    return getRandomInstance().nextFloat();
  }

  public static double getDouble() {
    return getRandomInstance().nextDouble();
  }

  public static double getDouble(double origin, double bound) {
    return getRandomInstance().nextDouble(origin, bound);
  }

  /**
   * 得到数字组成的随机字符串
   */
  public static String getNumberString(int length) {
    return getLongLength(length).toString();
  }

  public static String getPhoneNumberString(int total, String prefix) {
    if (prefix.length() >= total) {
      //前缀超过总位数 直接返回
      return prefix;
    }
    return prefix + getNumberString(total - prefix.length());
  }

  public static Integer getIntegerLength(int length) {

    if (length > 10 || length < 1) {
      throw new IllegalArgumentException("length参数错误：" + length + " 整数长度有效范围是1<=length<=10");
    }

    int scale = 10;
    int begin = 1;
    int end = 1;
    while (length > 1) {
      begin *= scale;
      end *= scale;
      length--;
    }
    end = end > Integer.MAX_VALUE / 10 ? Integer.MAX_VALUE : end * scale;

    return getRandomInstance().nextInt(begin, end);
  }

  public static Long getLongLength(int length) {
    if (length > 19 || length < 1) {
      throw new IllegalArgumentException("length参数错误：" + length + " 长整数长度有效范围是1<=length<=19");
    }

    long scale = 10;
    long begin = 1;
    long end = 1;
    while (length > 1) {
      begin *= scale;
      end *= scale;
      length--;
    }

    end = end > Long.MAX_VALUE / 10 ? Long.MAX_VALUE : end * scale;

    return getRandomInstance().nextLong(begin, end);
  }


  public static String getAlphabetString(int length) {
    char[] chars = new char[length];
    for (int i = 0; i < length; i++) {
      chars[i] = getAlphabet();
    }
    return new String(chars).intern();
  }

  public static char getAlphabet() {

    ThreadLocalRandom randomInstance = getRandomInstance();
    int character;
    if (randomInstance.nextBoolean()) {
      character = randomInstance.nextInt(65, 91);
    } else {
      character = randomInstance.nextInt(97, 123);
    }
    return (char) character;
  }

  public static String getAlphabetNumber(int length){
    char[] chars = new char[length];
    for (int i = 0; i < length; i++) {
      chars[i] = getAlphabetNumber();
    }
    return new String(chars).intern();
  }

  public static char getAlphabetNumber() {

    ThreadLocalRandom randomInstance = getRandomInstance();
    int character;
    int type = randomInstance.nextInt(4);
    if (type == 0) {
      character = randomInstance.nextInt(65, 91);
    } else if (type == 1) {
      character = randomInstance.nextInt(97, 123);
    } else {
      character = randomInstance.nextInt(48, 58);
    }
    return (char) character;
  }

  /**
   * 得到符号/字母/数字组成的随机字符串
   */
  public static String getSymbolString(int length) {
    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      stringBuilder.append((char) getRandomInstance().nextInt(33, 126));//33~126的ASCII码表数值
    }
    return stringBuilder.toString();
  }

  /**
   * 得到随机中文字符串
   */
  public static String getChinese(int length) {
    char[] chars = new char[length];
    for (int i = 0; i < chars.length; i++) {
      chars[i] = getChineseChar();
    }
    return new String(chars);
  }

  private static char getChineseChar() {
    return (char) (0x4e00 + (int) (getRandomInstance().nextDouble() * (0x9fa5 - 0x4e00
        + 1)));
  }

  /**
   * 得到随机中文简体字符串
   */
  public static String getChineseSimple(int length) {
    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      stringBuilder.append(getChineseCharSimple());
    }
    return stringBuilder.toString();
  }

  private static String getChineseCharSimple() {
    String str = null;
    // 定义高低位
    int highPos;
    int lowPos;
    highPos = (176 + Math.abs(getRandomInstance().nextInt(39))); //获取高位值
    lowPos = (161 + Math.abs(getRandomInstance().nextInt(93))); //获取低位值
    byte[] b = new byte[2];
    b[0] = (Integer.valueOf(highPos).byteValue());
    b[1] = (Integer.valueOf(lowPos).byteValue());
    try {
      str = new String(b, "GBk"); //转成中文
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    }
    return str;
  }


}
