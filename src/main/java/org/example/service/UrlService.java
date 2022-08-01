package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domains.UrlEntity;
import org.example.dto.url.UrlCreateDto;
import org.example.repository.UrlRepository;
import org.example.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 20:14 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public Page<UrlEntity> findAll(Integer page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return urlRepository.findAll(pageable);
    }


    public void generate(UrlCreateDto dto, BindingResult result) {
        if (!Utils.isParsable(dto.getValidTill()) && StringUtils.hasText(dto.getValidTill())) {
            result.addError(new FieldError(
                    "dto",
                    "validTill",
                    "Data for url expiration invalid"));
            return;
        } else if (!StringUtils.hasText(dto.getValidTill())) {
            dto.setExpiration(LocalDateTime.now(Clock.systemDefaultZone()).plusDays(10));
        } else {
            dto.setExpiration(Utils.toLocalDateTime(dto.getValidTill()));
        }

        if (dto.getExpiration().isBefore(LocalDateTime.now(Clock.systemDefaultZone()))) {
            result.addError(new FieldError(
                    "dto",
                    "validTill",
                    "Time is not valid is must be future time"));
            return;
        }

        String shortenedUrl = DigestUtils.md5DigestAsHex(dto.getOriginalURL().getBytes());
        UrlEntity urlEntity = UrlEntity.builder()
                .originalUrl(dto.getOriginalURL())
                .createdAt(LocalDateTime.now())
                .description(dto.getDescription())
                .validTill(dto.getExpiration())
                .shortenedUrl(shortenedUrl)
                .build();
        urlRepository.save(urlEntity);
    }

    public UrlEntity findByShortenedUrl(String shortenedUrl) {
        return urlRepository.findByShortenedUrl(shortenedUrl);
    }
}
