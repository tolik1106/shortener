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
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class LinkController {

    private final AtomicLong counter = new AtomicLong();
    private static final String TEMPLATE = "Hello, %s";

    @Autowired
    private LinkService linkService;

    @Autowired
    private StatisticService statisticService;


    @GetMapping("/")
    public String greeting(@RequestParam(defaultValue = "World") String name, Model model) {
        model.addAttribute("message", "");
        return "greeting";
    }

    @GetMapping("/links")
    public String getAll(Model model) {
        model.addAttribute("links", linkService.getAll());
        return "links";
    }

    @GetMapping("/{id}")
    public ResponseEntity follow(@PathVariable String id, HttpServletRequest request) {
        Link link = linkService.getByShortLink(id);
        Objects.requireNonNull(link);
        LinkStatistic stat = statisticService.create(link, request);
        if (!link.isActive() || link.getEndDate().before(new Date())) {
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
        Link link1 = linkService.createLink(linkTo);
        model.addAttribute("greeting", link1.getShortLink());
        return "greeting";
    }

    @GetMapping("/statistic")
    public String getStaticstic(@RequestParam Link link, Model model) {
        model.addAttribute("link", link);
        model.addAttribute("statistic", statisticService.getAllStatistic(link.getId()));
        return "statistic";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        linkService.delete(id);
        return "redirect:/links";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Link link = linkService.getById(id);
        model.addAttribute("link", link);
        return "link";
    }

    @PostMapping("/links")
    public String update(@ModelAttribute Link link) {
        linkService.update(link);
        return "redirect:/links";
    }
}
