package com.developer.wang.utils.string;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

public class StringUtil {
	
	public static String getPercentString(float percent) {
		return String.format(Locale.US, "%d%%", (int) (percent));
	}
	/**
	 * 删除字符串中的空白符
	 *
	 * @param content
	 * @return String
	 */
	public static String removeBlanks(String content) {
		if (content == null) {
			return null;
		}
		StringBuilder buff = new StringBuilder();
		buff.append(content);
		for (int i = buff.length() - 1; i >= 0; i--) {
			if (' ' == buff.charAt(i) || ('\n' == buff.charAt(i)) || ('\t' == buff.charAt(i))
					|| ('\r' == buff.charAt(i))) {
				buff.deleteCharAt(i);
			}
		}
		return buff.toString();
	}
	/**
	 * 获取32位uuid
	 *
	 * @return
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static boolean isEmpty(String input) {
		return TextUtils.isEmpty(input);
	}
	
	/**
	 * 生成唯一号
	 *
	 * @return
	 */
	public static String get36UUID() {
		UUID uuid = UUID.randomUUID();
		String uniqueId = uuid.toString();
		return uniqueId;
	}
	
	public static String makeMd5(String source) {
		return MD5.getStringMD5(source);
	}
	
    public static final String filterUCS4(String str) {
		if (TextUtils.isEmpty(str)) {
			return str;
		}

		if (str.codePointCount(0, str.length()) == str.length()) {
			return str;
		}

		StringBuilder sb = new StringBuilder();

		int index = 0;
		while (index < str.length()) {
			int codePoint = str.codePointAt(index);
			index += Character.charCount(codePoint);
			if (Character.isSupplementaryCodePoint(codePoint)) {
				continue;
			}

			sb.appendCodePoint(codePoint);
		}

		return sb.toString();
	}

    /**
     * counter ASCII character as one, otherwise two
     *
     * @param str
     * @return count
     */
    public static int counterChars(String str) {
        // return
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            int tmp = (int) str.charAt(i);
            if (tmp > 0 && tmp < 127) {
                count += 1;
            } else {
                count += 2;
            }
        }
        return count;
    }

	/**
	 * 为给定的字符串添加HTML红色标记，当使用Html.fromHtml()方式显示到TextView 的时候其将是红色的
	 * @param string 给定的字符串
	 * @return
	 */
	public static String addHtmlRedFlag(String string){
		return "<font color=\"red\">"+string+"</font>";
	}

	public static String addHtmlRedFlag(String string ,String color){
		return "<font color=\""+color+"\">"+string+"</font>";
	}

	/**
	 * 将给定的字符串中所有给定的关键字标红
	 * @param sourceString 给定的字符串
	 * @param keyword 给定的关键字
	 * @return 返回的是带Html标签的字符串，在使用时要通过Html.fromHtml()转换为Spanned对象再传递给TextView对象
	 */
	public static String keywordMadeRed(String sourceString, String keyword){
		String result = "";
		if(sourceString != null && !"".equals(sourceString.trim())){
			if(keyword != null && !"".equals(keyword.trim())){
				result = sourceString.replaceAll(keyword, "<font color=\"red\">"+keyword+"</font>");
			}else{
				result = sourceString;
			}
		}
		return result;
	}

	public static String keywordMadeRed(String sourceString, String keyword ,String color){
		String result = "";
		if(sourceString != null && !"".equals(sourceString.trim())){
			if(keyword != null && !"".equals(keyword.trim())){
				result = sourceString.replaceAll(keyword, "<font color=\""+color+"\">"+keyword+"</font>");
			}else{
				result = sourceString;
			}
		}
		return result;
	}

	public static String nameOfPath(String patch){
		int start = patch.lastIndexOf("/");
		if (start != -1){
			return patch.substring(start+1);
		}
		return null;
	}

    /**
     * 获取指定字符串字节长度
     * @param str
     * @return
     */
    public static int getByteLength(String str){
        if(isEmpty(str))return 0;
        try {
            return str.getBytes("gbk").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 0;
    }

	public static byte[] getByte(String str){
		if(isEmpty(str))return null;
		try {
			return str.getBytes("gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getStringFromByte(byte[] byptes){
    	if(byptes ==  null)return "";
			return new String(byptes);
	}

	/**
	 * 获取字符串字节长度
	 * @param str
	 * @return
	 */
	public static int getWordCount(String str) {
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			int ascii = Character.codePointAt(str, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;
		}
		return length;
	}

	/**
	 * 把字符串截取指定的字节长度
	 * @param str1
	 * @param length1 字节长度
	 * @return
	 */
	public static String getStrByWordCount(String str1, int length1) {
		try {
			int length = 0;
			int subLength = 0;
			for (int i = 0; i < str1.length(); i++) {
				int ascii = Character.codePointAt(str1, i);
				if (ascii >= 0 && ascii <= 255)
					length++;
				else
					length += 2;
				if (length >= length1) {
					if (length % 2 == 1)
						subLength = i;
					else
						subLength = i + 1;
					break;
				}
			}
			if (subLength > 0)
				str1 = str1.substring(0, subLength);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str1;
	}

	/**
	 * str是否包含表情
	 *
	 * @param str
	 * @return true 包含表情
	 */
	public static boolean noContainsEmoji(String str) {
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (isEmojiCharacter(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * str是否全部是表情
	 *
	 * @param str
	 * @return true 全部是表情
	 */
	public static boolean isAllEmoji(String str) {
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (!isEmojiCharacter(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否为表情
	 *
	 * @param codePoint
	 * @return true 为表情
	 */
	public static boolean isEmojiCharacter(char codePoint) {
		return !((codePoint == 0x0) ||
				(codePoint == 0x9) ||
				(codePoint == 0xA) ||
				(codePoint == 0xD) ||
				((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
				((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
				((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
	}

}
