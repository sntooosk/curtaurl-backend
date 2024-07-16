package com.etec.curtaurl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb02_qrcodes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tb02_uid", nullable = false, updatable = false)
    private String uid;

    @Column(name = "tb02_qrcodes_imgs", nullable = false, length = 2048)
    private String qrCodeImage;
}
