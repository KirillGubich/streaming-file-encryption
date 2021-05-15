package by.bsuir.crypt.stream.cipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShiftRegisterEncryptor {

    private long register;
    private static final int BUFFER_LENGTH = 4096;
    private static final int BITS_IN_BYTE_COUNT = 8;

    public ShiftRegisterEncryptor(long registerInitialValue) {
        register = registerInitialValue;
    }

    // x^23 + x^5 + 1
    private long fetchRegisterBit() {
        long resultBit = register >> 31;
        long bit22 = (register >> 22) & 1;
        long bit4 = (register >> 4) & 1;
        long xor = bit4 ^ bit22;
        register = (register << 1) | xor;
        return resultBit;
    }

    public boolean process(String srcPath, String destPath) {
        if (!checkFiles(srcPath, destPath)) {
            System.out.println("File " + destPath + " already exists");
            return false;
        }
        try (InputStream inputStream = new FileInputStream(srcPath);
             OutputStream outputStream = new FileOutputStream(destPath)) {
            byte[] buffer = new byte[BUFFER_LENGTH];
            int numberOfBytesRead;
            numberOfBytesRead = inputStream.read(buffer, 0, BUFFER_LENGTH);
            while (numberOfBytesRead > 0) {
                for (int i = 0; i < BUFFER_LENGTH; ++i) {
                    for (int j = 0; j < BITS_IN_BYTE_COUNT; ++j) {
                        buffer[i] = (byte) (buffer[i] ^ (fetchRegisterBit() << BITS_IN_BYTE_COUNT - 1 - j));
                    }
                }
                outputStream.write(buffer, 0, numberOfBytesRead);
                numberOfBytesRead = inputStream.read(buffer, 0, BUFFER_LENGTH);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private boolean checkFiles(String srcFile, String destFile) {
        Path srcPath = Paths.get(srcFile);
        Path destPath = Paths.get(destFile);
        return Files.exists(srcPath) && !Files.exists(destPath);
    }
}
