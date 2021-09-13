package ru.lanit.test.service;

import org.springframework.stereotype.Service;

@Service
public interface IService<T> {
    void save(T entity);
}
