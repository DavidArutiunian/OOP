package labs.lab4.shapes.point;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class Point {
    public final double x;
    public final double y;

    @Override
    public String toString() {
        return "Point\n" +
            "x = " + x + '\n' +
            "y = " + y + '\n';
    }

    public String toString(int indent) {
        val str = StringUtils.repeat(' ', indent);
        return str + "Point\n" +
            str + "x = " + x + '\n' +
            str + "y = " + y + '\n';
    }
}
