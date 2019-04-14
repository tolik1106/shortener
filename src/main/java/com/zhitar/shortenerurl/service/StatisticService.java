package com.zhitar.shortenerurl.service;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.domain.LinkStatistic;
import com.zhitar.shortenerurl.repository.StatisticRepository;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private StatisticRepository repository;

    public List<LinkStatistic> getAllStatistic(Long linkId) {
        return repository.getAll(linkId);
    }

    public LinkStatistic create(Link link, HttpServletRequest request) {
        LinkStatistic statistic = new LinkStatistic();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        statistic.setFollowDate(LocalDate.now());
        statistic.setRefferer(request.getHeader(HttpHeaders.REFERER));
        statistic.setBrowser(userAgent.getBrowser().getName());
        statistic.setIPAddress(request.getRemoteAddr());
        statistic.setLink(link);
        repository.save(statistic);
        return statistic;
    }

    public Integer countStatisticForLink(Long linkId) {
        return repository.followCount(linkId);
    }
}
