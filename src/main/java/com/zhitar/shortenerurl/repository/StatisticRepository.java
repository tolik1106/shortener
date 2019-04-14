package com.zhitar.shortenerurl.repository;

import com.zhitar.shortenerurl.domain.LinkStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticRepository extends JpaRepository<LinkStatistic, Long> {

    @Query("SELECT s FROM LinkStatistic s WHERE s.link.id=:link_id ORDER BY s.followDate DESC")
    List<LinkStatistic> getAll(@Param("link_id") Long linkId);

    @Query("SELECT COUNT(s) FROM LinkStatistic s WHERE s.link.id=:linkId")
    Integer followCount(@Param("linkId") Long linkId);
}
