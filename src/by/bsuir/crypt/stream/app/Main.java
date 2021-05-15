package by.bsuir.crypt.stream.app;

import by.bsuir.crypt.stream.cipher.ShiftRegisterEncryptor;
import by.bsuir.crypt.stream.util.UserInputUtil;

public class Main {

    private static final String RESOURCES_PATH = "src/resources/";
    private static final String SRC_FILE_NAME_INPUT_MESSAGE = "Source file name: ";
    private static final String DEST_FILE_INPUT_MESSAGE = "Dest file name: ";

    public static void main(String[] args) {
        final long key = UserInputUtil.inputKey();
        final ShiftRegisterEncryptor encryptor = new ShiftRegisterEncryptor(key);
        String srcPath = RESOURCES_PATH + UserInputUtil.inputFileName(SRC_FILE_NAME_INPUT_MESSAGE);
        String destPath = RESOURCES_PATH + UserInputUtil.inputFileName(DEST_FILE_INPUT_MESSAGE);
        boolean processedSuccessfully = encryptor.process(srcPath, destPath);
        if (processedSuccessfully) {
            System.out.println("File successfully processed");
        } else {
            System.out.println("Something went wrong. File was not processed");
        }
    }
}
