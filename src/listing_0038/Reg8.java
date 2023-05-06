package listing_0038;

public enum Reg8 {
    al(0x0),
    cl(0x1),
    dl(0x2),
    bl(0x3),
    ah(0x4),
    ch(0x5),
    dh(0x6),
    bh(0x7);

    public static final int REG8_MASK = 0x7;
    private final int value;

    Reg8(int value) {
        this.value = value;
    }

    public static Reg8 getReg8(int value) {
        for (Reg8 reg8 : Reg8.values()) {
            if ((value & REG8_MASK) == reg8.value) {
                return reg8;
            }
        }
        int unknownReq8 = value & 0xff;
        throw new RuntimeException("Reg8 of value = " + unknownReq8 + " is unknown");
    }
}
