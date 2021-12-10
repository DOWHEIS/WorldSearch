package bsu.edu.cs222.view;

import bsu.edu.cs222.model.ColorCodes;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.asciithemes.TA_Grid;
import de.vandermeer.asciithemes.TA_GridConfig;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class Menu {
    List<String> baseNames = new ArrayList<>(Arrays.asList(" Name ", " Region ", " Income Level ", " Capital City "));
    List<String> indicatorNames = new ArrayList<>(Arrays.asList(" GDP ", " Population ", "Population Growth", "Population Density", "Death Rate Per 1k People", " Unemployment Rate ", "Poverty Rate", " Homicides Per 100k People ", " School Enrollment Rate ", " Average Income ",
            "Life Expectancy", "Land Area (sq.km)"));
    String noVal = " No value reported ";

    ColorCodes colorCodes = new ColorCodes();

    public void createTitleMenu() {
        System.out.println(colorCodes.buildTitleGradient("*-------------------------*"));
        System.out.println(ColorCodes.BOLD + ColorCodes.ITALIC + colorCodes.buildTitleGradient("  Welcome to WorldSearch!"));
        System.out.println(ColorCodes.RESET + colorCodes.buildTitleGradient("*-------------------------*"));
    }

    public String selection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ColorCodes.RESET + ColorCodes.W2 + "Please make your selection:");
        int selection;
        while (true) {
            try {
                System.out.println("   1. Single country search");
                System.out.println("   2. Country comparison");
                System.out.println("   3. Random country search");
                System.out.println("   4. Battle Mode");
                System.out.println("   5. Random Country Comparison");
                System.out.println("   6. Exit");
                selection = scanner.nextInt();
                switch (selection) {
                    case 1:
                        return "Single Country";
                    case 2:
                        return "Country Comparison";
                    case 3:
                        return "Random Country";
                    case 4:
                        return "Battle Mode";
                    case 5:
                        return "Random Country Comparison";
                    case 6:
                        return "quit";
                    default:
                        System.out.println("Invalid selection! Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid selection! Please try again.");
                scanner.next();
            }
        }
    }

    public String rePrompt() {
        System.out.println(ColorCodes.W2);
        String userResponse;
        Scanner scanner = new Scanner(System.in);
        int selection;
        while (true) {
            try {
                System.out.println("Would you like to make another search?");
                System.out.println("   1. Yes");
                System.out.println("   2. Exit to main menu");
                selection = scanner.nextInt();
                switch (selection) {
                    case 1:
                        userResponse = "reSelection";
                        break;
                    case 2:
                        return "Exit";
                    default:
                        System.out.println("Invalid selection! Please try again.");
                        continue;
                }
                return userResponse;

            } catch (InputMismatchException e) {
                System.out.println("Invalid selection! Please try again.");
                scanner.next();
            }
        }
    }

    public void displayBattleModeNames(List<String> topFourNames) {
        System.out.println(ColorCodes.H + topFourNames.get(0) + ColorCodes.W1 + " beat " + ColorCodes.RED + topFourNames.get(1) + ColorCodes.W1 + " in a decisive battle to claim the world!");
    }

    public void displayBattleModeBracket(List<String> topFourIso) {
        String topRow = ColorCodes.W1 + "__%s__%20s__%s__%n";
        String middle = "%6s|__%s__[ " + ColorCodes.H + "%s " + ColorCodes.W1 + "]__%s__|%n";
        String bottomRow = "__%s__|%18s|__%s__%n%n";
        System.out.printf(topRow + middle + bottomRow, topFourIso.get(0), " ", topFourIso.get(2), " ", topFourIso.get(0), topFourIso.get(0), topFourIso.get(1), topFourIso.get(3), " ", topFourIso.get(1));
    }

    public String getUserDidYouMean(List<String> countrySuggestions) {
        System.out.println(ColorCodes.W2);
        Scanner scanner = new Scanner(System.in);
        int selection;
        while (true) {
            try {
                System.out.println("Did you mean?");
                System.out.println("   1. " + countrySuggestions.get(0));
                System.out.println("   2. " + countrySuggestions.get(1));
                System.out.println("   3. " + countrySuggestions.get(2));
                System.out.println("   4. None of these");

                selection = scanner.nextInt();
                switch (selection) {
                    case 1:
                        return countrySuggestions.get(0);
                    case 2:
                        return countrySuggestions.get(1);
                    case 3:
                        return countrySuggestions.get(2);
                    case 4:
                        return "none";
                    default:
                        System.out.println("Invalid selection! Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid selection! Please try again.");
                scanner.next();
            }
        }
    }

    //currently off, needs to be fixed.
    public void createSingleOutput(List<String> baseData, List<String> indicatorData) {
        System.out.println(ColorCodes.RESET);

        DecimalFormat df = setupDecimalFormat();

        AsciiTable table = buildSingleBaseTable(baseData);

        int k = 0;
        int n = 1;
        for (int i = 0; i < indicatorNames.size(); i++) {
            if (Objects.equals(indicatorData.get(i + k), "noVal")) {
                table.addRow(" " + indicatorNames.get(i), " " + noVal + " ");
                table.addRule();
            } else if (i == 0 || i == 9) {
                table.addRow(" " + indicatorNames.get(i) + " ", " $" + df.format(new BigDecimal(indicatorData.get(i + k))) + " (" + indicatorData.get(i + n) + ") ");
                table.addRule();
            } else if (i == 2 || i == 5 || i == 6 || i == 8) {
                table.addRow(" " + indicatorNames.get(i) + " ", " " + df.format(new BigDecimal(indicatorData.get(i + k))) + "%" + " (" + indicatorData.get(i + n) + ") ");
                table.addRule();
            } else {
                table.addRow(" " + indicatorNames.get(i) + " ", " " + df.format(new BigDecimal(indicatorData.get(i + k))) + " (" + indicatorData.get(i + n) + ") ");
                table.addRule();
            }
            k += 1;
            n += 1;
        }
        printTable(table);
    }

    private AsciiTable buildSingleBaseTable(List<String> baseData) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        for (int i = 0; i < baseNames.size(); i++) {
            if (Objects.equals(baseData.get(i), "noVal")) {
                table.addRow(baseNames.get(i), noVal);
            } else {
                table.addRow(baseNames.get(i), " " + baseData.get(i) + " ");
            }
            table.addRule();
        }
        return table;
    }

    public void createComparisonOutput(List<String> baseData, List<String> indicatorData, List<String> baseDataTwo, List<String> indicatorDataTwo) {
        System.out.println(ColorCodes.RESET);

        DecimalFormat df = setupDecimalFormat();

        AsciiTable table = buildComparisonBaseTable(baseData, baseDataTwo);

        int k = 0;
        int n = 1;
        //this accommodates different formatting around "noVal" values
        for (int i = 0; i < indicatorNames.size(); i++) {
            if (Objects.equals(indicatorData.get(i + k), "noVal") && Objects.equals(indicatorDataTwo.get(i + k), "noVal")) {
                if (i == 0 || i == 9) {
                    table.addRow(indicatorNames.get(i), noVal, noVal);
                    table.addRule();
                } else if (i == 2 || i == 5 || i == 6 || i == 8) {
                    table.addRow(indicatorNames.get(i), noVal, noVal);
                    table.addRule();
                } else {
                    table.addRow(indicatorNames.get(i), noVal, noVal);
                    table.addRule();
                }

            } else if (Objects.equals(indicatorData.get(i + k), "noVal")) {
                if (i == 0 || i == 9) {
                    table.addRow(indicatorNames.get(i), noVal, " $" + indicatorDataTwo.get(i + k) + " ");
                    table.addRule();
                } else if (i == 2 || i == 5 || i == 6 || i == 8) {
                    table.addRow(indicatorNames.get(i), noVal, " " + indicatorDataTwo.get(i + k) + "%");
                    table.addRule();
                } else {
                    table.addRow(indicatorNames.get(i), noVal, " " + indicatorDataTwo.get(i + k) + " ");
                    table.addRule();
                }

            } else if (Objects.equals(indicatorDataTwo.get(i + k), "noVal")) {
                if (i == 0 || i == 9) {
                    table.addRow(indicatorNames.get(i), " $" + indicatorData.get(i + k) + " ", noVal);
                    table.addRule();
                } else if (i == 2 || i == 5 || i == 6 || i == 8) {
                    table.addRow(indicatorNames.get(i), " " + indicatorData.get(i + k) + "%", noVal);
                    table.addRule();
                } else {
                    table.addRow(indicatorNames.get(i), " " + indicatorData.get(i + k) + " ", noVal);
                    table.addRule();
                }

            } else if (i == 0 || i == 9) {
                table.addRow(indicatorNames.get(i), " $" + df.format(new BigDecimal(indicatorData.get(i + k))) + " (" + indicatorData.get(i + n) + ") " + " ", " " + " $" + df.format(new BigDecimal(indicatorDataTwo.get(i + k))) + " (" + indicatorDataTwo.get(i + n) + ") " + " ");
                table.addRule();
            } else if (i == 2 || i == 5 || i == 6 || i == 8) {
                table.addRow(indicatorNames.get(i), " " + df.format(new BigDecimal(indicatorData.get(i + k))) + "%" + " (" + indicatorData.get(i + n) + ") " + " ", " " + df.format(new BigDecimal(indicatorDataTwo.get(i + k))) + "%" + " (" + indicatorDataTwo.get(i + n) + ") " + " ");
                table.addRule();
            } else {
                table.addRow(indicatorNames.get(i), " " + df.format(new BigDecimal(indicatorData.get(i + k))) + " (" + indicatorData.get(i + n) + ") " + " ", " " + df.format(new BigDecimal(indicatorDataTwo.get(i + k))) + " (" + indicatorDataTwo.get(i + n) + ") " + " ");
                table.addRule();
            }
            k += 1;
            n += 1;
        }
        printTable(table);
    }

    private AsciiTable buildComparisonBaseTable(List<String> baseData, List<String> baseDataTwo) {
        AsciiTable table = new AsciiTable();

        table.addRule();
        for (int i = 0; i < baseNames.size(); i++) {
            if (Objects.equals(baseData.get(i), "noVal")) {
                table.addRow(baseNames.get(i), noVal, " " + baseDataTwo.get(i) + " ");
                table.addRule();
            } else if (Objects.equals(baseDataTwo.get(i), "noVal")) {
                table.addRow(baseNames.get(i), " " + baseData.get(i) + " ", noVal);
                table.addRule();
            } else {
                table.addRow(baseNames.get(i), " " + baseData.get(i) + " ", " " + baseDataTwo.get(i) + " ");
                table.addRule();
            }
        }

        return table;
    }

    private void printTable(AsciiTable table) {
        TA_Grid myGrid = setupGrid();
        CWC_LongestLine cwc = new CWC_LongestLine();

        System.out.println(ColorCodes.C1);
        table.getContext().setGrid(myGrid);
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(cwc);
        System.out.println(table.render());
    }

    private TA_Grid setupGrid() {
        return TA_Grid.create("grid using UTF-8 light border characters")
                .addCharacterMap(TA_GridConfig.RULESET_NORMAL, ' ', '-', '|', '+', '+', '+', '+', '+', '+', '+', '+', '+');
    }

    private DecimalFormat setupDecimalFormat() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df;
    }
}
