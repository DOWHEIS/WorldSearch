package bsu.edu.cs222.model;

public class ColorCodes {

    public String buildTitleGradient(String string) {
        String[] colorPalette = {ColorCodes.Space1, ColorCodes.Space2, ColorCodes.Space3, ColorCodes.Space4, ColorCodes.W1, ColorCodes.E1, ColorCodes.L1, ColorCodes.C1, ColorCodes.O1, ColorCodes.M, ColorCodes.E2,
                ColorCodes.Space5, ColorCodes.T, ColorCodes.O2, ColorCodes.Space6, ColorCodes.W2, ColorCodes.O3, ColorCodes.R1, ColorCodes.L2, ColorCodes.D, ColorCodes.S,
                ColorCodes.E3, ColorCodes.A, ColorCodes.R2, ColorCodes.C2, ColorCodes.H, ColorCodes.Exclamation};
        char[] chars = string.toCharArray();
        StringBuilder title = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            title.append(colorPalette[i]);
            title.append(chars[i]);
        }
        return title.toString();
    }

    //text format
    public static final String BOLD = "\033[1m";
    public static final String ITALIC = "\033[3m";

    //Color Palette:
    public static final String RED = "\033[38;2;249;73;73m";

    public static final String Space1 = "\033[38;2;41;194;250m";
    public static final String Space2 = "\033[38;2;41;194;250m";
    public static final String Space3 = "\033[38;2;41;194;250m";
    public static final String Space4 = "\033[38;2;41;194;250m";
    public static final String W1 = "\033[38;2;41;194;250m";
    public static final String E1 = "\033[38;2;39;195;238m";
    public static final String L1 = "\033[38;2;37;197;227m";
    public static final String C1 = "\033[38;2;35;198;215m";
    public static final String O1 = "\033[38;2;33;199;204m";
    public static final String M = "\033[38;2;31;201;192m";
    public static final String E2 = "\033[38;2;29;202;181m";

    public static final String Space5 = "\033[38;2;29;202;181m";

    public static final String T = "\033[38;2;27;204;169m";
    public static final String O2 = "\033[38;2;25;205;157m";

    public static final String Space6 = "\033[38;2;23;206;146m";

    public static final String W2 = "\033[38;2;23;206;146m";
    public static final String O3 = "\033[38;2;22;208;134m";
    public static final String R1 = "\033[38;2;20;209;123m";
    public static final String L2 = "\033[38;2;18;210;111m";
    public static final String D = "\033[38;2;16;212;99m";
    public static final String S = "\033[38;2;14;213;88m";
    public static final String E3 = "\033[38;2;12;215;76m";
    public static final String A = "\033[38;2;10;216;65m";
    public static final String R2 = "\033[38;2;8;217;53m";
    public static final String C2 = "\033[38;2;6;219;42m";
    public static final String H = "\033[38;2;4;220;30m";
    public static final String Exclamation = "\033[38;2;4;220;30m";

    public static final String RESET = "\033[0m";
}


