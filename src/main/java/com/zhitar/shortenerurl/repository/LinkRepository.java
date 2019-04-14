package com.zhitar.shortenerurl.repository;

import com.zhitar.shortenerurl.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LinkRepository extends JpaRepository<Link, Long> {



    Link findLinkByUrl(String url);

    Link findLinkByShortLink(String shortLink);

    @Modifying
    @Query("DELETE FROM Link l WHERE l.id=:id")
    void delete(@Param("id") Long id);
}
