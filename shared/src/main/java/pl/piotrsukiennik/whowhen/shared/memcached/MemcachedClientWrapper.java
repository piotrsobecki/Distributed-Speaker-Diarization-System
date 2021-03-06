package pl.piotrsukiennik.whowhen.shared.memcached;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.OperationTimeoutException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Piotr Sukiennik
 */
public class MemcachedClientWrapper {
    public static final int MAX_EXPIRATION = 3000;

    private static Log LOG = LogFactory.getLog( MemcachedClientWrapper.class );
    private MemcachedClient memcachedClient;

    public MemcachedClientWrapper( MemcachedClient memcachedClient ) {
        this.memcachedClient = memcachedClient;
    }

    public <T> T get( String genericKey, Class<T> outputType ) {
        try{
            return (T) memcachedClient.get( genericKey );
        }
        catch (  IllegalStateException ex ){
            if (LOG.isErrorEnabled()){
                LOG.error( "Error while obtaining object",ex);
            }
            return null;
        } catch ( OperationTimeoutException ex ){
            if (LOG.isErrorEnabled()){
                LOG.error( "Error while obtaining object",ex);
            }
            return null;
        }
    }

    public <T> T get( MemcachedGenericKey<T> genericKey ) {
        try{
            return (T) memcachedClient.get( genericKey.getKey() );
        }
        catch (  IllegalStateException ex ){
            if (LOG.isErrorEnabled()){
                LOG.error( "Error while obtaining object",ex);
            }
            return null;
        } catch ( OperationTimeoutException ex ){
            if (LOG.isErrorEnabled()){
                LOG.error( "Error while obtaining object",ex);
            }
            return null;
        }
    }

    public <T> MemcachedGenericKey<T> put( String key, T object, int expirationMillis ) {
        MemcachedGenericKey<T> memcachedGenericKey = new MemcachedGenericKey<T>( key, "" );
        memcachedGenericKey.setExpirationTimeMillis( System.currentTimeMillis() + expirationMillis );
        memcachedClient.set( key, expirationMillis, object );
        return memcachedGenericKey;
    }

    public <T> MemcachedGenericKey<T> put( String key, T object ) {
        return put( key, object, MAX_EXPIRATION );
    }

    public <T> MemcachedGenericKey<T> put( String key, T object, int expiration, TimeUnit unit ) {
        long expirationMillis = unit.convert( expiration, TimeUnit.MILLISECONDS );
        return put( key, object, (int) expirationMillis );
    }

    public <T> T pop( MemcachedGenericKey<T> genericKey ) {
        T result = get( genericKey );
        delete( genericKey );
        return result;
    }

    public <T> void delete( MemcachedGenericKey<T> genericKey ) {
        genericKey.setExpirationTimeMillis( -1 );
        memcachedClient.delete( genericKey.getKey() );
    }

}
