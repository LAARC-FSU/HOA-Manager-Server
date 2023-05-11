package com.laarc.hoamanagerserver.exception.function;

import com.laarc.hoamanagerserver.exception.http.HttpErrorException;

import java.util.function.Supplier;

@FunctionalInterface
public interface HttpExceptionSupplier<T extends HttpErrorException> extends Supplier<T> {

}
