package com.core.event;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ywm on 16/6/30.
 */
public class StartBrotherEvent {
    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
