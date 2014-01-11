package pl.piotrsukiennik.whowhen.shared.serializer.util;

import pl.piotrsukiennik.whowhen.shared.serializer.IDataSerializer;

import java.io.File;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Piotr Sukiennik
 */
public class SerializeOnChangeObserver implements Observer {

    private String namePrefix = "serialized";

    private IDataSerializer[] dataSerializers;

    private File serializersOutputDir;


    public SerializeOnChangeObserver( File serializersOutputDir, IDataSerializer dataSerializer, IDataSerializer... dataSerializers ) {
        IDataSerializer[] serializers = null;
        if ( dataSerializers == null || dataSerializers.length == 0 ) {
            serializers = new IDataSerializer[1];
        }
        else {
            serializers = new IDataSerializer[dataSerializers.length + 1];
            System.arraycopy( dataSerializers, 0, serializers, 1, dataSerializers.length );
        }
        serializers[0] = dataSerializer;
        this.dataSerializers = dataSerializers;
        this.serializersOutputDir = serializersOutputDir;
    }

    public SerializeOnChangeObserver( File serializersOutputDir, IDataSerializer[] dataSerializers ) {
        this.dataSerializers = dataSerializers;
        this.serializersOutputDir = serializersOutputDir;
    }

    public void update( Observable o, Object arg ) {
        serializeObservable( o );
    }

    public void serializeObservable( Observable observable ) {
        if ( observable instanceof Serializable ) {
            for ( IDataSerializer dataSerializer : dataSerializers ) {
                if ( dataSerializer.supportsSerialization( observable.getClass() ) ) {
                    dataSerializer.serialize( (Serializable) observable, new File( serializersOutputDir, namePrefix + "." + dataSerializer.getExtension() ) );
                }
            }
        }
        else {
            throw new ObservableNotSerializableException( "Observable of class " + observable.getClass() + " is not serializable." );
        }
    }

    protected class ObservableNotSerializableException extends RuntimeException {
        public ObservableNotSerializableException( String message ) {
            super( message );
        }
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix( String namePrefix ) {
        this.namePrefix = namePrefix;
    }
}
