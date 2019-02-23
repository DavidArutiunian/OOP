package labs.lab2.vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

class ProcessVector {
    private final List<Float> items;

    ProcessVector(final List<Float> items) {
        this.items = items;
    }

    List<Float> call(final float average) {
        List<Float> array = new ArrayList<>();
        items.forEach(num -> array.add(num + average));
        return array;
    }

    List<Float> call() {
        return call(0);
    }

    float getAverage() {
        float average = 0;
        if (!items.isEmpty()) {
            AtomicReference<Float> sum = new AtomicReference<>(0.f);
            items.forEach(num -> sum.updateAndGet(v -> v + num));
            average = sum.get() / items.size();
        }
        return average;
    }
}
