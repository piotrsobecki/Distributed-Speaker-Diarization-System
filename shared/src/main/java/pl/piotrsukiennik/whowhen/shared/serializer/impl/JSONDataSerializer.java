package pl.piotrsukiennik.whowhen.shared.serializer.impl;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

/**
 * @author Piotr Sukiennik
 */
public class JSONDataSerializer extends AbstractSerializer {
    public String getExtension() {
        return "json";
    }

    @Override
    protected void doSerialize( Object o, File output ) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibilityChecker( mapper.getVisibilityChecker().withFieldVisibility( JsonAutoDetect.Visibility.ANY ) );
        try {

            if ( output.createNewFile() ) {
                mapper.writeValue( output, o );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    @Override
    protected <T> T doDeserialize( File targetFile, Class<T> targetClass ) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibilityChecker( mapper.getVisibilityChecker().withFieldVisibility( JsonAutoDetect.Visibility.ANY ) );
        try {
            return mapper.readValue( targetFile, targetClass );
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean supportsSerialization( Class clazz ) {
        return Object.class.isAssignableFrom( clazz );
    }


   /* public static void serializeToJSON(Serializable object, File targetFile){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibilityChecker(mapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        try{
            targetFile.delete();
            if (targetFile.createNewFile()){
                mapper.writeValue(targetFile, object);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }*/
}
