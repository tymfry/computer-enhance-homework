package listing_0038;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    private static final int D_MASK = 0x2;
    private static final int W_MASK = 0x1;

    public static void main(String[] args) {
        byte[] bytes = readBinaryFile("homework-input/listing_0038_many_register_mov");

        StringBuilder textToWrite = new StringBuilder("bits 16\n\n");

        for (int i = 0; i < bytes.length; i += 2) {
            textToWrite.append(translateBytesTo8086Asm(new byte[]{bytes[i], bytes[i+1]}));
            textToWrite.append("\n");
        }

        writeToTextFile("homework-output/listing_0038_output.asm", textToWrite.toString());

        System.out.println(textToWrite);
    }

    public static String translateBytesTo8086Asm(byte[] twoBytes) {
        String opcode = Opcode.getOpcode(twoBytes[0]).name();
        boolean DFlag = (twoBytes[0] & D_MASK) == D_MASK;
        boolean WFlag = (twoBytes[0] & W_MASK) == W_MASK;

        Mod mod = Mod.getMod(twoBytes[1] >> 6);

        String firstOper = null;
        String secondOper = null;

        if (Mod.RR.getValue() == mod.getValue()) {
            if (DFlag) {
                firstOper = getReg(twoBytes[1] >> 3, WFlag);
                secondOper = getReg(twoBytes[1], WFlag);
            } else {
                secondOper = getReg(twoBytes[1] >> 3, WFlag);
                firstOper = getReg(twoBytes[1], WFlag);
            }
        }

        return String.format("%s %s, %s", opcode, firstOper, secondOper);
    }

    public static String getReg(int regBytes, boolean WFlag) {
        if (WFlag) {
            return Reg16.getReg16(regBytes).name();
        } else {
            return Reg8.getReg8(regBytes).name();
        }
    }

    public static byte[] readBinaryFile(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToTextFile(String fileName, String textToWrite) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] bytes = textToWrite.getBytes();
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
