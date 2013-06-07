package iopiotrsukiennik.whowhen.shared.serializer.impl;

import iopiotrsukiennik.whowhen.shared.serializer.IDataSerializer;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 00:18
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSerializer implements IDataSerializer {
    public final void serialize(Object o, File output) {
        if (this.supportsSerialization(o.getClass())){
           doSerialize(o,output);
        }
    }

    @Override
    public final <T> T deserialize(File targetFile, Class<T> targetClass) {
        if (this.supportsSerialization(targetClass)){
            return doDeserialize(targetFile,targetClass);
        }
        return null;
    }
    protected abstract <T> T doDeserialize(File targetFile, Class<T> targetClass);
    protected abstract void doSerialize(Object o, File output);

}
