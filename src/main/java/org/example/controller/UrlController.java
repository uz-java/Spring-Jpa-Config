package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domains.UrlEntity;
import org.example.dto.url.UrlCreateDto;
import org.example.service.UrlService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 20:13 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
@Controller
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String homePage(@RequestParam(name = "page") Optional<Integer> pageOptional, Model model){
        Integer pageNumber= pageOptional.orElse(0);
        Page<UrlEntity> page=urlService.findAll(pageNumber);
        model.addAttribute("page",page);
        model.addAttribute("pageNumbers", IntStream.range(0,page.getTotalPages()).toArray());
        return "index";
    }
    @RequestMapping(value = "/gen/", method = RequestMethod.GET)
    public String urlGeneratePage(Model model){
        model.addAttribute("dto",new UrlCreateDto());
        return "gen/url-generate-page";
    }
    @RequestMapping(value = "/gen/", method = RequestMethod.POST)
    public String urlGenerate(@Valid @ModelAttribute("dto") UrlCreateDto dto, BindingResult result){
        urlService.generate(dto,result);
        if(result.hasErrors()){
            return "gen/url-generate-page";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/go/{shortenedUrl}", method = RequestMethod.GET)
    public void UrlGeneratePage(@PathVariable String shortenedUrl, HttpServletResponse response) throws IOException {
        UrlEntity urlEntity=urlService.findByShortenedUrl(shortenedUrl);
        response.sendRedirect(urlEntity.getOriginalUrl());
    }
}
