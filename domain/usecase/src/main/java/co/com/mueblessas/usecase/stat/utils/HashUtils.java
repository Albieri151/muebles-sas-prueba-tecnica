package co.com.mueblessas.usecase.stat.utils;

import co.com.mueblessas.model.stat.Stat;
import reactor.core.publisher.Mono;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class HashUtils {

    public static Mono<Boolean> isHashValid(Stat stat) {
        return Mono.fromCallable(() -> {
            if (stat == null) return false;
            String data = String.format("%d,%d,%d,%d,%d,%d,%d",
                    stat.getTotalContactoClientes(),
                    stat.getMotivoReclamo(),
                    stat.getMotivoGarantia(),
                    stat.getMotivoDuda(),
                    stat.getMotivoCompra(),
                    stat.getMotivoFelicitaciones(),
                    stat.getMotivoCambio());

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            String calculatedHash = hexString.toString();
            return calculatedHash.equals(stat.getHash());
        }).onErrorReturn(false);
    }
}