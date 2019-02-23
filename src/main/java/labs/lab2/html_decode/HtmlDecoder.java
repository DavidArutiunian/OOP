package labs.lab2.html_decode;

class HtmlDecoder {
    private final String line;

    HtmlDecoder(final String line) {
        this.line = line;
    }

    String decode(final HtmlEntityMap htmlEntityMap) {
        return line.replaceAll(HtmlEntities.QUOT.toString(), htmlEntityMap.getDecodedQuot())
            .replaceAll(HtmlEntities.APOS.toString(), htmlEntityMap.getDecodedApos())
            .replaceAll(HtmlEntities.LT.toString(), htmlEntityMap.getDecodedLt())
            .replaceAll(HtmlEntities.GT.toString(), htmlEntityMap.getDecodedGt())
            .replaceAll(HtmlEntities.AMP.toString(), htmlEntityMap.getDecodedAmp());
    }
}
