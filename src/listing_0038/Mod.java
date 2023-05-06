package listing_0038;

public enum Mod {
    RR(0x2);

    public static final int MOD_MASK = 0x2;
    private final int value;

    Mod(int value) {
        this.value = value;
    }

    public static Mod getMod(int value) {
        for (Mod mod : Mod.values()) {
            if ((value & MOD_MASK) == mod.value) {
                return mod;
            }
        }
        int unknownMod = value & 0xff;
        throw new RuntimeException("Mod of value = " + unknownMod + " is unknown");
    }

    public int getValue() {
        return value;
    }
}
