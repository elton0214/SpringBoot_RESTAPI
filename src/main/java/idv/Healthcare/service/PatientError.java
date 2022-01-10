package idv.Healthcare.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

// not working now
@Data
@RequiredArgsConstructor
public class PatientError {
    private final String message;
}
