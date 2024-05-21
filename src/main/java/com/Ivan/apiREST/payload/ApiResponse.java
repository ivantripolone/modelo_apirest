package com.Ivan.apiREST.payload;

import lombok.*;
import java.util.Date;
@Data
@ToString
public class ApiResponse  {
    private Date tiempo = new Date();
    private String mensaje;
    private String url;
    private Object response;

    public ApiResponse(String mensaje, String url) {
        this.mensaje = mensaje;
        this.url = url.replace("uri=","");
    }

    public ApiResponse(String mensaje,String url, Object response) {
        this.mensaje = mensaje;
        this.response = response;
        this.url = url.replace("uri=","");
    }


}
