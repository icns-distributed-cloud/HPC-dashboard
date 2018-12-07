package com.icnslab.Message;

/**
 * Created by alicek106 on 2017-07-20.
 */
public class AckResponse {
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public AckResponse(String result){
        this.result = result;
    }
    String result;
}
