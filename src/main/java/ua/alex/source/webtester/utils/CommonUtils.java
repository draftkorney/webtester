package ua.alex.source.webtester.utils;

import ua.alex.source.webtester.entities.AccountRole;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {
    private CommonUtils() {

    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] encPassByte = messageDigest.digest(password.getBytes("UTF-8"));
            return new String(encPassByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return password;

    }

    public static List<Integer> convertRoles(List<AccountRole> roles) {
        return roles.stream().map(ar -> ar.getRole().getIdRole()).collect(Collectors.toList());
    }
}
