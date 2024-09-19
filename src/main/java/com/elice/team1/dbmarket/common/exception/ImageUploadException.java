package com.elice.team1.dbmarket.common.exception;
import java.io.IOException;


public class ImageUploadException extends IOException {
    //TODO 이미지 예외처리
    public ImageUploadException(){
    }
    public ImageUploadException(String message) {
        super.getMessage();
        toRegister();
    }
    public ImageUploadException(Long id) {
        super.getMessage();
        toUpdate();
    }
    public String toRegister() {
        return "item/registeritemform";
    }
    public String toUpdate() {
        return "item/edit";
    }
}
