package com.etec.curtaurl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.curtaurl.models.QrCode;

public interface QrCodeRepository extends JpaRepository<QrCode, String> {
}
