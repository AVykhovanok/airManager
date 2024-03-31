package com.oles.airmanagement.dto.exception;

import com.oles.airmanagement.converter.mark.Convertible;
import java.time.LocalDateTime;
import java.util.Map;
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
public class ExceptionMapResponse implements Convertible {
    Map<String, String> messageMap;

    HttpStatus status;

    LocalDateTime timeStamp;
}
