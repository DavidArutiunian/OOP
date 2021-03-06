package labs.lab4.shapes.factory.parameters;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SolidShapeParameters extends ShapeParameters {
    @Nullable
    private Integer fillColor;
}
