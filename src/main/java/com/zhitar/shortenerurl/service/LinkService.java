package com.zhitar.shortenerurl.service;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.repository.LinkRepository;
import com.zhitar.shortenerurl.to.LinkTo;
import com.zhitar.shortenerurl.util.Helper;
import com.zhitar.shortenerurl.util.LinkConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LinkService {

    private static final long DEFAULT_EXPIRE_DAYS = 30;
    private static final long MS_IN_ONE_DAY = 24 * 60 * 60 * 1000;
    public static final String PREFIX = "http://localhost:8080/";

    @Autowired
    private LinkRepository repository;

    @Transactional
    public Link createLink(LinkTo linkTo) {
        Link linkByURL = repository.findLinkByUrl(linkTo.getLink());
        if (linkByURL != null) {
            return linkByURL;
        }
        Date expiredDate;
        if (( expiredDate = linkTo.getDateExpired()) == null) {
            expiredDate = new Date();
            Integer daysExpired;
            if ((daysExpired = linkTo.getDaysExpired()) == null) {
                System.out.println("Without days");
                System.out.println(expiredDate.getTime());
                System.out.println(DEFAULT_EXPIRE_DAYS * MS_IN_ONE_DAY);
                expiredDate.setTime(expiredDate.getTime() + DEFAULT_EXPIRE_DAYS * MS_IN_ONE_DAY);
            } else {
                System.out.println("DaysExpired");
                expiredDate.setTime(expiredDate.getTime() + daysExpired * MS_IN_ONE_DAY);
            }
        }
        String shortLink = Helper.generateRandomString();
        Link newLink = LinkConverter.convertToLink(linkTo, expiredDate, shortLink);
        Link saved = repository.save(newLink);
        return saved;
    }

    public List<Link> getAll() {
        return repository.findAll();
    }

    public Link getByShortLink(String shortLink) {
        shortLink = PREFIX + shortLink;
        return repository.findLinkByShortLink(shortLink);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void update(Link link) {
        repository.save(link);
    }

    public Link getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
