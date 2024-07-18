package com.example.backendapplicationprogramming.server.entities;

import lombok.Data;

@Data
public class Links {
    private Self self;
    private Country country;

    public Links(Self self) {
        this.self = self;
    }

    @Data
    public static class Self implements java.io.Serializable {
        private String href;

        public Self(String href) {
            this.href = href;
        }
    }

    @Data
    public static class Country implements java.io.Serializable {
        private String href;

        public Country(String href) {
            this.href = href;
        }
    }

}
