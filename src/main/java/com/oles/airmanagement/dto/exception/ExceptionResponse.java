package com.oles.airmanagement.dto.exception;

import com.oles.airmanagement.converter.mark.Convertible;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionResponse implements Convertible {
    String message;

    HttpStatus status;

    LocalDateTime timeStamp;
}
