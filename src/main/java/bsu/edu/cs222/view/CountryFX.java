package bsu.edu.cs222.view;

import javafx.scene.paint.Color;

public enum CountryFX {
    AD, AE, AF, AG, AI, AL, AM, AO, AR, AS, AT, AU, AW, AX, AZ,
    BA, BB, BD, BE, BF, BG, BH, BI, BJ, BL, BN, BO, BM, BQ, BR, BS, BT, BV, BW, BY, BZ,
    CA, CC, CD, CF, CG, CH, CI, CK, CL, CM, CN, CO, CR, CU, CV, CW, CX, CY, CZ,
    DE, DJ, DK, DM, DO, DZ,
    EC, EG, EE, EH, ER, ES, ET,
    FI, FJ, FK, FM, FO, FR,
    GA, GB, GE, GD, GF, GG, GH, GI, GL, GM, GN, GO, GP, GQ, GR, GS, GT, GU, GW, GY,
    HK, HM, HN, HR, HT, HU,
    ID, IE, IL, IM, IN, IO, IQ, IR, IS, IT,
    JG, JM, JO, JP, JU,
    KE, KG, KH, KI, KM, KN, KP, KR, XK, KW, KY, KZ,
    LA, LB, LC, LI, LK, LR, LS, LT, LU, LV, LY,
    MA, MC, MD, MG, ME, MF, MH, MK, ML, MO, MM, MN, MP, MQ, MR, MS, MT, MU, MV, MW, MX, MY, MZ,
    NA, NC, NE, NF, NG, NI, NL, NO, NP, NR, NU, NZ,
    OM,
    PA, PE, PF, PG, PH, PK, PL, PM, PN, PR, PS, PT, PW, PY,
    QA,
    RE, RO, RS, RU, RW,
    SA, SB, SC, SD, SE, SG, SH, SI, SJ, SK, SL, SM, SN, SO, SR, SS, ST, SV, SX, SY, SZ,
    TC, TD, TF, TG, TH, TJ, TK, TL, TM, TN, TO, TR, TT, TV, TW, TZ,
    UA, UG, UM_DQ, UM_FQ, UM_HQ, UM_JQ, UM_MQ, UM_WQ, US, UY, UZ,
    VA, VC, VE, VG, VI, VN, VU,
    WF, WS,
    YE, YT,
    ZA, ZM, ZW;

    private Color color;

    CountryFX() {
        color = null;
    }

    public String getName() {
        return name();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color COLOR) {
        color = COLOR;
    }
}
