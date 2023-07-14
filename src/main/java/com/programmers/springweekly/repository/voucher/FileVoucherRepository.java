package com.programmers.springweekly.repository.voucher;

import com.programmers.springweekly.domain.voucher.Voucher;
import com.programmers.springweekly.domain.voucher.VoucherFactory;
import com.programmers.springweekly.domain.voucher.VoucherType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class FileVoucherRepository {

    private final Map<UUID, Voucher> voucherMap = new ConcurrentHashMap<>();

    @Value("${file.voucher.path}")
    private String filePath;

    public Voucher save(Voucher voucher) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {

            String voucherId = String.valueOf(voucher.getVoucherId());
            String voucherAmount = String.valueOf(voucher.getVoucherAmount());

            bufferedWriter.write(voucherId);
            bufferedWriter.write(",");
            bufferedWriter.write(voucherAmount);
            bufferedWriter.write(",");
            bufferedWriter.write(String.valueOf(voucher.getVoucherType()));
            bufferedWriter.newLine();

            bufferedWriter.flush();
            return voucher;
        } catch (IOException e) {
            log.error("바우처를 파일에 저장하던 중 알 수 없는 에러가 발생", e);
            throw new RuntimeException("바우처를 파일에 저장하던 중 알 수 없는 에러가 발생하였습니다.");
        }
    }

    public Map<UUID, Voucher> findAll() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath))) {
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                String[] readLine = line.split(",");

                Voucher voucher = VoucherFactory.createVoucher(UUID.fromString(readLine[0]), VoucherType.valueOf(readLine[2]), Long.parseLong(readLine[1]));

                voucherMap.put(voucher.getVoucherId(), voucher);
            }

        } catch (IndexOutOfBoundsException e) {
            log.warn("바우처 파일에 저장된 바우처의 정보 열의 수가 맞지 않아 예외 발생 ", e);
            throw new IndexOutOfBoundsException("현재 파일에 저장된 열의 수가 맞지 않습니다. 바우처 파일을 확인해보세요.");
        } catch (IOException e) {
            log.error("바우처 파일에서 모든 정보를 읽어오던 중 알 수 없는 에러가 발생 ", e);
            throw new RuntimeException("바우처 파일에서 모든 정보를 읽어오던 중 알 수 없는 에러가 발생하였습니다.");
        }

        return Collections.unmodifiableMap(voucherMap);
    }

}
