package org.example.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "urls")
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortenedUrl;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime validTill;

    @Builder.Default
    private Long createdBy = -1L;
}
