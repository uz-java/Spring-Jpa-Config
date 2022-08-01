package org.example.dto.url;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlCreateDto {

    @NotBlank(message = "{NotBlank.OriginalUrl}")
    @Pattern(regexp = "^(https://|http://).*", message = "{Pattern.OriginalUrl}")
    private String originalURL;

    @NotBlank(message = "{NotBlank.Description}")
    private String description;

    private String validTill;

    private LocalDateTime expiration;
}
