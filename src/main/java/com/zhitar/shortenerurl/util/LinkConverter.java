package com.zhitar.shortenerurl.util;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.to.LinkTo;

import java.util.Date;

public class LinkConverter {

    private LinkConverter() {}

    public static Link convertToLink(LinkTo linkTo, Date endDate, String shortLink) {
        return new Link(linkTo.getLink(), shortLink, endDate, linkTo.isActive());
    }

    public static LinkTo convertToLinkTo(Link link) {
        return null;
    }
}
