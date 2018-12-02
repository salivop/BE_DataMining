package com.vaitkevicius.main.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * *
 * Created By Povilas 15/11/2018
 * *
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NullUtils {
    /**
     * Calls 'func' on 'in' if in is not null
     * @param in the parameter, on which the 'func' will be called
     * @param func function, which accepts 'in' as a parameter
     * @param <R> the return type
     * @param <T> the 'in' type
     * @return the result of 'func' being called on 'in' or null if 'in' is null
     */
    public static <R, T> R call(T in, Function<T, R> func) {
        return in == null ? null : func.apply(in);
    }

    /**
     * Calls 'func' on 'in' if in is not null, otherwise returns orElse
     * @param in the parameter, on which the 'func' will be called
     * @param func function, which accepts 'in' as a parameter
     * @param orElse value to return if 'in' is false
     * @param <R> the return type
     * @param <T> the 'in' type
     * @return the result of 'func' being called on 'in' or 'orElse' if 'in' is null
     */
    public static <R, T> R call(T in, Function<T, R> func, R orElse) {
        return in == null ? orElse : func.apply(in);
    }

    /**
     *
     * @param args parameters in priority order
     * @param <T> type of parameters
     * @return first non-null parameter
     */
    public static <T> T coalesce(T ...args) {
        if(args == null) {
            return null;
        }
        return Arrays.stream(args).filter(Objects::nonNull).findFirst().orElse(null);
    }

}
