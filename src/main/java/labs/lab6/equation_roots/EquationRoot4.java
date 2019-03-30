package labs.lab6.equation_roots;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
class EquationRoot4 {
    private static final String APP_ID = System.getenv("WOLFRAM_APP_ID");
    private static final OkHttpClient client = new OkHttpClient();

    @Getter
    private final List<Double> roots = new ArrayList<>();

    void solve(double a, double b, double c, double d, double e) throws IOException, WolframResponseException, SolutionNotFoundException, CannotParseValueException, WolframAppIdException {
        if (APP_ID == null) {
            throw new WolframAppIdException("'WOLFRAM_APP_ID' is not provided or invalid!");
        }

        if (a == 0) {
            throw new IllegalArgumentException("First argument cannot be 0!");
        }

        val equation = a + "x^4" +
            (b > 0 ? "+" : "") + b + "x^3" +
            (c > 0 ? "+" : "") + c + "x^2" +
            (d > 0 ? "+" : "") + d + "x" +
            (e > 0 ? "+" : "") + e + "=0";

        val url = Objects.requireNonNull(HttpUrl.parse("http://api.wolframalpha.com/v2/query"))
            .newBuilder()
            .addQueryParameter("appid", APP_ID)
            .addQueryParameter("input", equation)
            .addQueryParameter("output", "json")
            .build();

        val request = new Request.Builder().url(url).get().build();

        try (val response = client.newCall(request).execute()) {
            val data = Objects.requireNonNull(response.body()).string();
            val json = new JSONObject(data);
            val result = json.getJSONObject("queryresult");
            val success = result.getBoolean("success");
            if (!success) {
                throw new WolframResponseException("Cannot get successful response!");
            }
            val pods = result.getJSONArray("pods");
            var found = false;
            for (Object pod : pods) {
                val title = ((JSONObject) pod).getString("title");
                if (Objects.equals(title, "Real solutions") || Objects.equals(title, "Integer solutions")) {
                    val subpods = ((JSONObject) pod).getJSONArray("subpods");
                    if (subpods.length() == 4) {
                        found = true;
                        for (Object root : subpods) {
                            val value = ((JSONObject) root).getString("plaintext").replace("x = ", "");
                            if (!NumberUtils.isCreatable(value)) {
                                throw new CannotParseValueException("Value " + value + " is not parsable to Double!");
                            }
                            val number = NumberUtils.toDouble(value);
                            roots.add(number);
                        }
                    }
                    break;
                }
            }
            if (!found) {
                throw new SolutionNotFoundException("Solutions not found!");
            }
        }
    }
}
