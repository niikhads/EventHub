package com.example.eventhub.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse<T> {

     int code;
     String message;
     ResponseData<T> response;

     @Data
     public static class ResponseData<T> {
          private T data;
          public ResponseData(T data) {
               this.data = data;
          }
     }
}
