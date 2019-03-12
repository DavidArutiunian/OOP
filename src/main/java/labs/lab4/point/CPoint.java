package labs.lab4.point;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class CPoint {
    public final double x;
    public final double y;

    public String ToString() {
        return "CPoint\n" +
            "x = " + x + '\n' +
            "y = " + y + '\n';
    }

    public String ToString(final int indent) {
        val str = StringUtils.repeat(' ', indent);
        return str + "Point\n" +
            str + "x = " + x + '\n' +
            str + "y = " + y + '\n';
    }
}
