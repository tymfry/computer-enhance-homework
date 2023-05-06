package listing_0038;

public enum Opcode {
    mov(0x88);

    public static final int OPCODE_MASK = 0xfc;

    private final int value;

    Opcode(int value) {
        this.value = value;
    }

    static Opcode getOpcode(byte value) {
        for (Opcode opcode : Opcode.values()) {
            if ((value & OPCODE_MASK) == opcode.value) {
                return opcode;
            }
        }
        int unknownOpcode = value & 0xff;
        throw new RuntimeException("Opcode of value = " + unknownOpcode + " is unknown");
    }

}
