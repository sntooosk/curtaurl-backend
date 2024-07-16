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
    @Column(name = "tb01_uid", nullable = false, updatable = false)
    private String uid;

    @Column(name = "tb01_longurl", nullable = false, length = 2048)
    private String longUrl;

    @Column(name = "tb01_shorturl", nullable = false, unique = true, length = 20)
    private String shortUrl;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "tb01_qrcode_uid", referencedColumnName = "tb02_uid")
    private QrCode qrCode;

    @Column(name = "tb01_createdat", nullable = false)
    private LocalDateTime createdAt;
}
