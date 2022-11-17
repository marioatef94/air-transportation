package com.transportation.converter;

public interface Converter<T,D>{

    T convertToDto(D d);

    D convertToEntity(T t);

}
