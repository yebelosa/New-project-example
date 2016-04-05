package com.clean.example.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Capacity {

    private boolean fibre;
    private boolean adsl;

    public Capacity(boolean fibre, boolean adsl) {
        this.fibre = fibre;
        this.adsl = adsl;
    }

    public boolean hasAdslCapacity() {
        return false;
    }

    public boolean hasFibreCapacity() {
        return false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
