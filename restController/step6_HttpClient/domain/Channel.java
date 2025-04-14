package com.nhnacademy.restcontrollerpractice.step6.domain;

public enum Channel {
    /**
     * Enum에서 자주 사용되는 방식
     */
    A("https://nhnacademy.dooray.com/services/3204376758577275363/4045901689874472590/W0RgKMoPTUO3RejIIJVQcg"),
    B("https://nhnacademy.dooray.com/services/3204376758577275363/4045905507212963259/WL5QAUhXSXC09zQjupasRA");

    final String url;

    Channel(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
