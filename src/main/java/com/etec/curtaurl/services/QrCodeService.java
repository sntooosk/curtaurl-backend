package com.etec.curtaurl.services;

import com.etec.curtaurl.models.QrCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QrCodeService {

    public QrCode generateQrCode(String contents) throws IOException, WriterException {
        if (contents == null || contents.isEmpty()) {
            throw new IllegalArgumentException("Contents for QR Code cannot be null or empty");
        }

        int imageSize = 200;
        BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, imageSize, imageSize);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", bos);
        String image = Base64.getEncoder().encodeToString(bos.toByteArray());
        QrCode qrCode = new QrCode();
        qrCode.setQrCodeImage(image);
        return qrCode;
    }
}
