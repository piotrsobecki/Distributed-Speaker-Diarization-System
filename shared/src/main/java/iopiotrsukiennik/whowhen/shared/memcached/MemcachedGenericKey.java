package iopiotrsukiennik.whowhen.shared.memcached;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 10.11.12
 * Time: 00:15
 * To change this template use File | Settings | File Templates.
 */
public class MemcachedGenericKey<T> implements Serializable {

    private String key;
    private long expirationTimeMillis;
    private String keyHolderServerID;


    MemcachedGenericKey(String key, String keyHolderServerID) {
        this.key = key;
        this.keyHolderServerID = keyHolderServerID;
    }

    public String getKey() {
        return key;
    }

    void setExpirationTimeMillis(long expirationTimeMillis){
        this.expirationTimeMillis=expirationTimeMillis;
    }
    public boolean exists(){
        return expirationTimeMillis<=System.currentTimeMillis();
    }

    public long getExpirationTimeMillis() {
        return expirationTimeMillis;
    }

    public String getKeyHolderServerID() {
        return keyHolderServerID;
    }
}
