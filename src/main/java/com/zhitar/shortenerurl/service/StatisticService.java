package com.zhitar.shortenerurl.service;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.domain.LinkStatistic;
import com.zhitar.shortenerurl.repository.StatisticRepository;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatisticService {

    @Autowired
    private StatisticRepository repository;

    @Transactional
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

    public List<Object[]> dateStatistic(Long urlId) {
        return repository.getFollowByDate(urlId);
    }

    public List<Object[]> browserStatistic(Long urlId) {
        return repository.getFollowByBrowser(urlId);
    }

    public List<Object[]> referrerStatistic(Long urlId) {
        return repository.getFollowByReferrer(urlId);
    }
}
