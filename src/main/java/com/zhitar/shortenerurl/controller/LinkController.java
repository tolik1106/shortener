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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/")
    public String greeting() {
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

    @GetMapping("/statistic/{id}")
    public String getStaticstic(@PathVariable Long id, Model model) {
        model.addAttribute("link", linkService.getById(id));
        List<LinkStatistic> allStatistic = statisticService.getAllStatistic(id);
        model.addAttribute("follow", statisticService.countStatisticForLink(id));
        model.addAttribute("statistic", allStatistic);
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
