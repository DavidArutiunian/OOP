package labs.lab2.html_decode;

import java.util.HashMap;
import java.util.Map;

class HtmlEntityMapImpl implements HtmlEntityMap {
    private Map<String, String> dictionary = new HashMap<>();

    HtmlEntityMapImpl() {
        dictionary.put(HtmlEntities.QUOT.toString(), "\"");
        dictionary.put(HtmlEntities.APOS.toString(), "\'");
        dictionary.put(HtmlEntities.LT.toString(), "<");
        dictionary.put(HtmlEntities.GT.toString(), ">");
        dictionary.put(HtmlEntities.AMP.toString(), "&");
    }

    @Override
    public String getDecodedQuot() {
        return dictionary.get(HtmlEntities.QUOT.toString());
    }

    @Override
    public String getDecodedApos() {
        return dictionary.get(HtmlEntities.APOS.toString());
    }

    @Override
    public String getDecodedLt() {
        return dictionary.get(HtmlEntities.LT.toString());
    }

    @Override
    public String getDecodedGt() {
        return dictionary.get(HtmlEntities.GT.toString());
    }

    @Override
    public String getDecodedAmp() {
        return dictionary.get(HtmlEntities.AMP.toString());
    }
}
