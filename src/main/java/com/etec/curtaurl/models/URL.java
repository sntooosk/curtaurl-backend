package com.etec.curtaurl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb01_urls")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tb01_uid")
    private String uid;

    @Column(name = "tb01_longurl")
    private String longUrl;

    @Column(name = "tb01_shorturl")
    private String shortUrl;

    @OneToOne(cascade = CascadeType.PERSIST)
    private QrCode qrCode;

    @Column(name = "tb01_createdat")
    private LocalDateTime createdAt;
}
