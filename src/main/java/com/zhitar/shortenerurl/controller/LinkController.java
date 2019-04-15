package com.zhitar.shortenerurl.controller;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.domain.LinkStatistic;
import com.zhitar.shortenerurl.service.LinkService;
import com.zhitar.shortenerurl.service.StatisticService;
import com.zhitar.shortenerurl.to.LinkTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Objects;

@Controller
public class LinkController {

    public static final int URL_PREFIX_LENGTH = 22;
    @Autowired
    private LinkService linkService;

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/links")
    public String getAll() {
        return "links";
    }

    @GetMapping("/{id}")
    public ResponseEntity follow(@PathVariable String id, HttpServletRequest request) {
        Link link = linkService.getByShortLink(id);
        Objects.requireNonNull(link);
        LinkStatistic stat = statisticService.create(link, request);
        if (!link.isActive() || link.getEndDate().isBefore(LocalDate.now())) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", link.getUrl());
            return new ResponseEntity(headers, HttpStatus.FOUND);
        }
    }

    @PostMapping("/")
    public String create(LinkTo linkTo,
                         Model model) {
        Link link = linkService.createLink(linkTo);
        model.addAttribute("shortUrl", link.getShortLink());
        return "greeting";
    }

    @GetMapping("/statistic")
    public String getStaticstic(@RequestParam String shortUrl, Model model) {
        Link link = linkService.getByShortLink(shortUrl.substring(URL_PREFIX_LENGTH));
        if (link == null) {
            return "statistic";
        }
        model.addAttribute("link", link);
        model.addAttribute("follow", statisticService.countStatisticForLink(link.getId()));
        model.addAttribute("followByDate", statisticService.dateStatistic(link.getId()));
        model.addAttribute("followByBrowser", statisticService.browserStatistic(link.getId()));
        model.addAttribute("refStat", statisticService.referrerStatistic(link.getId()));
        return "statistic";
    }

}
