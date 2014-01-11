package pl.piotrsukiennik.whowhen.web.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Piotr Sukiennik
 */
public class ChecksumUtils {
    public static MessageDigest MD5MessageDigest = null;

    static {
        try {
            MD5MessageDigest = MessageDigest.getInstance( "md5" );
        }
        catch ( NoSuchAlgorithmException nsae ) {
            throw new RuntimeException( nsae );
        }
    }

    public static String toMd5( byte[] bytes ) {
        return new String( MD5MessageDigest.digest( bytes ) );
    }

    public static String toMd5( String string ) {
        try {
            return toMd5( string.getBytes( "UTF-8" ) );
        }
        catch ( UnsupportedEncodingException uee ) {
            return null;
        }
    }

    public static String toBase64( String string ) {
        try {
            return Base64.encodeBase64String( string.getBytes( "UTF-8" ) );
        }
        catch ( UnsupportedEncodingException uee ) {
            throw new RuntimeException( uee );
        }

    }
}
