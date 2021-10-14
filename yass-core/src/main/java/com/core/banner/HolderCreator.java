package com.core.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by ywm
 */

public class HolderCreator  implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
