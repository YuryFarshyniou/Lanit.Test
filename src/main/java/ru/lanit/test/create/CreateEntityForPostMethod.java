package ru.lanit.test.create;

import java.util.Map;

public interface CreateEntityForPostMethod<T> {

    T createEntity(Map<String, String> carInformation);
}
