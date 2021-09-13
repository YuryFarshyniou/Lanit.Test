package ru.lanit.test.utils.create;

import java.util.Map;

public interface CreateEntityForPostMethod<T> {

    T createEntity(Map<String, String> carInformation);
}
