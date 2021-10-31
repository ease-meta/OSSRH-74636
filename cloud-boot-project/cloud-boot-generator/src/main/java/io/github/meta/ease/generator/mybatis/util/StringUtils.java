/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.meta.ease.generator.mybatis.util;

import java.util.regex.Pattern;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/28 20:51
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * ���ַ�
     */
    public static final String EMPTY = StringPool.EMPTY;
    /**
     * �ַ��� is
     */
    public static final String IS = "is";
    /**
     * �»����ַ�
     */
    public static final char UNDERLINE = '_';
    /**
     * MP �ڶ���� SQL ռλ�����ʽ��ƥ������ {0},{1},{2} ... ����ʽ
     */
    public final static Pattern MP_SQL_PLACE_HOLDER = Pattern.compile("[{](?<idx>\\d+)}");
    /**
     * ��֤�ַ����Ƿ������ݿ��ֶ�
     */
    private static final Pattern P_IS_COLUMN = Pattern.compile("^\\w\\S*[\\w\\d]*$");

    /**
     * �Ƿ�Ϊ��д����
     */
    private static final Pattern CAPITAL_MODE = Pattern.compile("^[0-9A-Z/_]+$");

    /**
     * �ַ���ȥ���հ�����
     *
     * <ul> <li>\n �س�</li> <li>\t ˮƽ�Ʊ��</li> <li>\s �ո�</li> <li>\r ����</li> </ul>
     */
    private static final Pattern REPLACE_BLANK = Pattern.compile("\\s*|\t|\r|\n");

    /**
     * �Ƿ�Ϊ��д����
     *
     * @param word ���ж��ַ���
     * @return ignore
     */
    public static boolean isCapitalMode(String word) {
        return null != word && CAPITAL_MODE.matcher(word).matches();
    }

    /**
     * �Ƿ�Ϊ�շ��»��߻������
     *
     * @param word ���ж��ַ���
     * @return ignore
     */
    public static boolean isMixedMode(String word) {
        return matches(".*[A-Z]+.*", word) && matches(".*[/_]+.*", word);
    }

    /**
     * ɾ���ַ�ǰ׺֮��,����ĸСд,֮���ַ���Сд�Ĳ���
     * <p>StringUtils.removePrefixAfterPrefixToLower( "isUser", 2 )     = user</p>
     * <p>StringUtils.removePrefixAfterPrefixToLower( "isUserInfo", 2 ) = userInfo</p>
     *
     * @param rawString ��Ҫ������ַ���
     * @param index     ɾ�����ٸ��ַ�(��������)
     * @return ignore
     */
    public static String removePrefixAfterPrefixToLower(String rawString, int index) {
        return prefixToLower(rawString.substring(index), 1);
    }

    /**
     * ǰn������ĸСд,֮���ַ���Сд�Ĳ���
     *
     * @param rawString ��Ҫ������ַ���
     * @param index     ���ٸ��ַ�(��������)
     * @return ignore
     */
    public static String prefixToLower(String rawString, int index) {
        StringBuilder field = new StringBuilder();
        field.append(rawString.substring(0, index).toLowerCase());
        field.append(rawString.substring(index));
        return field.toString();
    }

    /**
     * �ַ����շ�ת�»��߸�ʽ
     *
     * @param param ��Ҫת�����ַ���
     * @return ת���õ��ַ���
     */
    public static String camelToUnderline(String param) {
        if (isBlank(param)) {
            return EMPTY;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append(UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * �ַ����»���ת�շ��ʽ
     *
     * @param param ��Ҫת�����ַ���
     * @return ת���õ��ַ���
     */
    public static String underlineToCamel(String param) {
        if (isBlank(param)) {
            return EMPTY;
        }
        String temp = param.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * ����ĸת��Сд
     *
     * @param param ��Ҫת�����ַ���
     * @return ת���õ��ַ���
     */
    public static String firstToLowerCase(String param) {
        if (isBlank(param)) {
            return EMPTY;
        }
        return param.substring(0, 1).toLowerCase() + param.substring(1);
    }

    /**
     * ������ʽƥ��
     *
     * @param regex ������ʽ�ַ���
     * @param input Ҫƥ����ַ���
     * @return ��� input ���� regex ������ʽ��ʽ, ����true, ���򷵻� false;
     */
    public static boolean matches(String regex, String input) {
        if (null == regex || null == input) {
            return false;
        }
        return Pattern.matches(regex, input);
    }

    /**
     * �շ�ת���ַ�
     * <p>StringUtils.camelToHyphen( "managerAdminUserService" ) = manager-admin-user-service</p>
     *
     * @param input ignore
     * @return ��'-'�ָ�
     * @see <a href="https://github.com/krasa/StringManipulation">document</a>
     */
    public static String camelToHyphen(String input) {
        return wordsToHyphenCase(wordsAndHyphenAndCamelToConstantCase(input));
    }

    private static String wordsAndHyphenAndCamelToConstantCase(String input) {
        StringBuilder buf = new StringBuilder();
        char previousChar = ' ';
        char[] chars = input.toCharArray();
        for (char c : chars) {
            boolean isUpperCaseAndPreviousIsLowerCase = (Character.isLowerCase(previousChar))
                    && (Character.isUpperCase(c));

            boolean previousIsWhitespace = Character.isWhitespace(previousChar);
            boolean lastOneIsNotUnderscore = (buf.length() > 0)
                    && (buf.charAt(buf.length() - 1) != '_');
            boolean isNotUnderscore = c != '_';
            if (lastOneIsNotUnderscore
                    && (isUpperCaseAndPreviousIsLowerCase || previousIsWhitespace)) {
                buf.append(StringPool.UNDERSCORE);
            } else if ((Character.isDigit(previousChar) && Character.isLetter(c))) {
                buf.append('_');
            }
            if ((shouldReplace(c)) && (lastOneIsNotUnderscore)) {
                buf.append('_');
            } else if (!Character.isWhitespace(c) && (isNotUnderscore || lastOneIsNotUnderscore)) {
                buf.append(Character.toUpperCase(c));
            }
            previousChar = c;
        }
        if (Character.isWhitespace(previousChar)) {
            buf.append(StringPool.UNDERSCORE);
        }
        return buf.toString();
    }

    private static boolean shouldReplace(char c) {
        return (c == '.') || (c == '_') || (c == '-');
    }

    private static String wordsToHyphenCase(String s) {
        StringBuilder buf = new StringBuilder();
        char lastChar = 'a';
        for (char c : s.toCharArray()) {
            if ((Character.isWhitespace(lastChar)) && (!Character.isWhitespace(c)) && ('-' != c)
                    && (buf.length() > 0) && (buf.charAt(buf.length() - 1) != '-')) {
                buf.append(StringPool.DASH);
            }
            if ('_' == c) {
                buf.append('-');
            } else if ('.' == c) {
                buf.append('-');
            } else if (!Character.isWhitespace(c)) {
                buf.append(Character.toLowerCase(c));
            }
            lastChar = c;
        }
        if (Character.isWhitespace(lastChar)) {
            buf.append(StringPool.DASH);
        }
        return buf.toString();
    }

}
