package com.zhitar.shortenerurl.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQuery(name = "Statistic.getAll", query = "SELECT s FROM LinkStatistic s WHERE s.link.id=:link_id ORDER BY s.followDate DESC")
@Entity
public class LinkStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100)
    @Column(name = "statistic_id")
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate followDate;

    private String refferer;

    private String IPAddress;

    private String browser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Link link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFollowDate() {
        return followDate;
    }

    public void setFollowDate(LocalDate followDate) {
        this.followDate = followDate;
    }

    public String getRefferer() {
        return refferer;
    }

    public void setRefferer(String refferer) {
        this.refferer = refferer;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
