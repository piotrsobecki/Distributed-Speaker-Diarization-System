package iopiotrsukiennik.whowhen.shared.util;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 27.10.12
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtil {

    public static <T> void fillWithInstances(T[] arr,Class<T> instanceClazz,Object... args){
        Class[] argClasses = new Class[args.length];
        for (int i=0; i<args.length;i++){
            argClasses[i]=args[i].getClass();
        }
        for (int i=0; i<arr.length;i++){
            try{
                arr[i]=instanceClazz.getConstructor(argClasses).newInstance(args);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> String join(T[] arr, String joinWith){
        String result = "";
         String joinWithTmp = "";
          for (T t: arr){
              result+=joinWithTmp+t;
              joinWithTmp=joinWith;
          }
          return result;
    }


    public static String join(double [] arr, String joinWith){
        String result = "";
        String joinWithTmp = "";
        for (double t: arr){
            result+=joinWithTmp+t;
            joinWithTmp=joinWith;
        }
        return result;
    }
}
