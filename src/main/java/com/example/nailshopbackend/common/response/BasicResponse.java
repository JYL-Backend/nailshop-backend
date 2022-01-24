package com.example.nailshopbackend.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicResponse extends CommonResponse {
    private Object data;
    private int code;

        public void changeCode(int code){
            this.code = code;
        }

            public void setData(Object obj) {
            this.data = obj;
        }

            public BasicResponse() {
            this.code = 200;
        }
}
