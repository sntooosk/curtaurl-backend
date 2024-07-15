package com.etec.curtaurl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.curtaurl.models.URL;

public interface URLRepository extends JpaRepository<URL, String> {
    URL findByShortUrl(String shortUrl);

}
