package pl.piotrsukiennik.whowhen.shared.serializer;

import java.io.File;

/**
 * @author Piotr Sukiennik
 */
public interface IDataSerializer {
    void serialize( Object o, File output );

    <T> T deserialize( File targetFile, Class<T> targetClass );

    String getExtension();

    boolean supportsSerialization( Class clazz );
}
