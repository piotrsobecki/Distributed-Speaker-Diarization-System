package iopiotrsukiennik.whowhen.shared.serializer;

import java.io.File;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 26.10.12
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public interface IDataSerializer {
    void serialize(Object o,File output);
    <T> T deserialize(File targetFile, Class<T> targetClass);
    String getExtension();
    boolean supportsSerialization(Class clazz);
}
