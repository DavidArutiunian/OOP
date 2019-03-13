package labs.lab4.shapes.point;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class CPoint {
    public final double x;
    public final double y;

    @Override
    public String toString() {
        return "CPoint\n" +
            "x = " + x + '\n' +
            "y = " + y + '\n';
    }

    public String toString(final int indent) {
        val str = StringUtils.repeat(' ', indent);
        return str + "Point\n" +
            str + "x = " + x + '\n' +
            str + "y = " + y + '\n';
    }
}
