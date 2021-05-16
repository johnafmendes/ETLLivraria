/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livraria.diversos;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class Diversos {

    /**
     *
     * @param md5
     * @return
     */
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
