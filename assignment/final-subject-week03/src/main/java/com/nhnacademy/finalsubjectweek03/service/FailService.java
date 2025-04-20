package com.nhnacademy.finalsubjectweek03.service;

public interface FailService {
    void saveFailedId(String memberId);
    void deleteFailedId(String memberId);
    boolean existsFailedId(String memberId);
    Long getTTLToSeconds(String memberId);
}
