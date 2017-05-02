package com.ys.baseproject.properties;

import android.os.ParcelFileDescriptor;

import com.bumptech.glide.load.model.ImageVideoModelLoader;
import com.bumptech.glide.load.model.ModelLoader;

import java.io.InputStream;

/**
 * Created by yunshan on 17/3/29.
 */

public class d extends ImageVideoModelLoader<d.DataMode>{


    public d(ModelLoader<DataMode, InputStream> streamLoader, ModelLoader<DataMode, ParcelFileDescriptor> fileDescriptorLoader) {
        super(streamLoader, fileDescriptorLoader);
    }

    interface DataMode{

        public String build(int width,int height);
    }
}
