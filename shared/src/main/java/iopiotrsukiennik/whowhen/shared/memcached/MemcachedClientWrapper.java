package iopiotrsukiennik.whowhen.shared.memcached;

import net.spy.memcached.MemcachedClient;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 10.11.12
 * Time: 00:18
 * To change this template use File | Settings | File Templates.
 */
public class MemcachedClientWrapper {
    public static final int MAX_EXPIRATION = 3000;

    private MemcachedClient memcachedClient;
    public MemcachedClientWrapper(MemcachedClient memcachedClient) {
        this.memcachedClient=memcachedClient;
    }

    public <T> T get(String genericKey,Class<T> outputType){
        return (T)memcachedClient.get(genericKey);
    }

    public <T> T get(MemcachedGenericKey<T> genericKey){
        return (T)memcachedClient.get(genericKey.getKey());
    }

    public <T> MemcachedGenericKey<T> put(String key, T object,int expirationMillis){
        MemcachedGenericKey<T>  memcachedGenericKey = new MemcachedGenericKey<T>(key,"");
        memcachedGenericKey.setExpirationTimeMillis(System.currentTimeMillis()+expirationMillis);
        memcachedClient.set(key,expirationMillis,object);
        return memcachedGenericKey;
    }

    public <T> MemcachedGenericKey<T> put(String key, T object){
        return put(key, object,MAX_EXPIRATION);
    }
    public <T> MemcachedGenericKey<T> put(String key, T object,int expiration, TimeUnit unit){
        long expirationMillis = unit.convert(expiration,TimeUnit.MILLISECONDS);
        return put(key,object,(int)expirationMillis);
    }
    public <T> T pop(MemcachedGenericKey<T> genericKey){
        T result = get(genericKey);
        delete(genericKey);
        return result;
    }
    public <T> void delete(MemcachedGenericKey<T> genericKey){
        genericKey.setExpirationTimeMillis(-1);
        memcachedClient.delete(genericKey.getKey());
    }

}
