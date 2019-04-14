package com.zhitar.shortenerurl.to;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class LinkTo {

    private Long id;

    private String link;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private Date dateExpired;

    private Integer daysExpired;

    private boolean active;

    public LinkTo() {
    }

    public LinkTo(String link, Date dateExpired, Integer daysExpired, boolean active) {
        this.link = link;
        this.dateExpired = dateExpired;
        this.daysExpired = daysExpired;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    public Integer getDaysExpired() {
        return daysExpired;
    }

    public void setDaysExpired(Integer daysExpired) {
        this.daysExpired = daysExpired;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkTo linkTo = (LinkTo) o;
        return Objects.equals(id, linkTo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
