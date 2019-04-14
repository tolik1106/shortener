package com.zhitar.shortenerurl.service;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.repository.LinkRepository;
import com.zhitar.shortenerurl.to.LinkTo;
import com.zhitar.shortenerurl.util.Helper;
import com.zhitar.shortenerurl.util.LinkConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LinkService {

    private static final long DEFAULT_EXPIRE_DAYS = 30;
    public static final String PREFIX = "http://localhost:8080/";

    @Autowired
    private LinkRepository repository;

    @Transactional
    public Link createLink(LinkTo linkTo) {
        Link linkByURL = repository.findLinkByUrl(linkTo.getLink());
        if (linkByURL != null) {
            return linkByURL;
        }
        LocalDate expiredDate;
        if (( expiredDate = linkTo.getDateExpired()) == null) {
            expiredDate = LocalDate.now();
            Integer daysExpired = linkTo.getDaysExpired();
            if ((daysExpired) == null) {
                expiredDate = expiredDate.plusDays(DEFAULT_EXPIRE_DAYS);
            } else {
                expiredDate = expiredDate.plusDays(daysExpired);
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

    @Transactional
    public void enable(long id, boolean active) {
        Link link = getById(id);
        link.setActive(active);
        repository.save(link);
    }
}
