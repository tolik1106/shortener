package com.zhitar.shortenerurl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100)
    @Column(name = "link_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 2048)
    private String url;

    @Column(nullable = false, unique = true)
    private String shortLink;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    private boolean active;

    @OneToMany(mappedBy = "link", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<LinkStatistic> linkStatisticSet;

    public Link() {
    }

    public Link(String url, String shortLink, LocalDate endDate, boolean active) {
        this.url = url;
        this.shortLink = shortLink;
        this.endDate = endDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public String getShortLink() {
        return shortLink;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<LinkStatistic> getLinkStatisticSet() {
        return linkStatisticSet;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setLinkStatisticSet(Set<LinkStatistic> linkStatisticSet) {
        this.linkStatisticSet = linkStatisticSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(id, link.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
