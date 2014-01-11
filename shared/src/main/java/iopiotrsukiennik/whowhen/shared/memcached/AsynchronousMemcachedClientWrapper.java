package iopiotrsukiennik.whowhen.shared.memcached;

import net.spy.memcached.MemcachedClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Piotr Sukiennik
 */
public class AsynchronousMemcachedClientWrapper extends MemcachedClientWrapper {

    public AsynchronousMemcachedClientWrapper( MemcachedClient memcachedClient ) {
        super( memcachedClient );
    }

    public <T> Future<T> asyncGet( final MemcachedGenericKey<T> genericKey, final long intervalCheck, final TimeUnit intervalCheckUnit ) {
        return new Future<T>() {
            private boolean cancelled = false;

            private boolean done = false;

            private boolean mayInterruptIfRunning = false;

            @Override
            public synchronized boolean cancel( boolean mayInterruptIfRunning ) {
                this.mayInterruptIfRunning = mayInterruptIfRunning;
                return cancelled = true;
            }

            @Override
            public synchronized boolean isCancelled() {
                return cancelled;
            }

            @Override
            public synchronized boolean isDone() {
                return done;
            }

            @Override
            public T get() throws InterruptedException, ExecutionException {
                long intervalCheckMillis = intervalCheckUnit.convert( intervalCheck, TimeUnit.MILLISECONDS );
                T target = AsynchronousMemcachedClientWrapper.super.get( genericKey );
                while ( target == null ) {
                    if ( !genericKey.exists() ) {
                        return null;
                    }
                    else if ( !cancelled || ( cancelled && !mayInterruptIfRunning ) ) {
                        try {
                            Thread.sleep( intervalCheckMillis );
                        }
                        catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                        try {
                            target = AsynchronousMemcachedClientWrapper.super.get( genericKey );
                        }
                        catch ( Exception e ) {
                            throw new ExecutionException( e );
                        }
                    }
                    else if ( cancelled && mayInterruptIfRunning ) {
                        throw new InterruptedException();
                    }
                }
                return target;
            }

            @Override
            public T get( long timeout, TimeUnit unit ) throws InterruptedException, ExecutionException, TimeoutException {
                timeout = unit.convert( timeout, TimeUnit.MILLISECONDS );
                long intervalCheckMillis = intervalCheckUnit.convert( intervalCheck, TimeUnit.MILLISECONDS );
                long waitFrom = System.currentTimeMillis();
                long waitTo = waitFrom + timeout;
                T target = AsynchronousMemcachedClientWrapper.super.get( genericKey );
                while ( target == null ) {
                    if ( !genericKey.exists() ) {
                        return null;
                    }
                    else if ( waitFrom <= waitTo ) {
                        throw new TimeoutException();
                    }
                    else if ( !cancelled || ( cancelled && !mayInterruptIfRunning ) ) {
                        try {
                            Thread.sleep( intervalCheckMillis );
                        }
                        catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                        try {
                            target = AsynchronousMemcachedClientWrapper.super.get( genericKey );
                        }
                        catch ( Exception e ) {
                            throw new ExecutionException( e );
                        }
                    }
                    else if ( cancelled && mayInterruptIfRunning ) {
                        throw new InterruptedException();
                    }
                }
                done = true;
                return target;
            }
        };
    }
}
