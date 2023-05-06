package listing_0038;

public enum Reg16 {
    ax(0x0),
    cx(0x1),
    dx(0x2),
    bx(0x3),
    sp(0x4),
    bp(0x5),
    si(0x6),
    di(0x7);

    public static final int REG16_MASK = 0x7;
    private final int value;

    Reg16(int value) {
        this.value = value;
    }

    public static Reg16 getReg16(int value) {
        for (Reg16 reg16 : Reg16.values()) {
            if ((value & REG16_MASK) == reg16.value) {
                return reg16;
            }
        }
        int unknownReq16 = value & 0xff;
        throw new RuntimeException("Reg16 of value = " + unknownReq16 + " is unknown");
    }
}
