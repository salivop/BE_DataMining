package com.vaitkevicius.main.common.data;

import org.springframework.data.domain.Persistable;

public abstract class AbstractEntity implements Persistable<String> {
    @Override
    public boolean isNew() {
        return getId() == null;
    }
}

