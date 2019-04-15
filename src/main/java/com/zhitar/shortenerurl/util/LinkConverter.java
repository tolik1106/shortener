package com.zhitar.shortenerurl.util;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.to.LinkTo;

import java.time.LocalDate;

public class LinkConverter {

    private LinkConverter() {}

    public static Link convertToLink(LinkTo linkTo, LocalDate endDate, String shortLink) {
        return new Link(linkTo.getLink(), shortLink, endDate, linkTo.isActive());
    }
}
