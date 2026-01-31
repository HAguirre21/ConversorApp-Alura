import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Responses {
    private String result;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    @SerializedName("time_last_update_utc")
    private String timeLastUpdateUtc;

    @SerializedName("time_next_update_utc")
    private String timeNextUpdateUtc;

    public boolean isSuccess() {
        return "success".equals(result);
    }

    public Map<String, Double> getConversionRates() { return conversionRates; }
    public String getTimeLastUpdateUtc() { return timeLastUpdateUtc; }
}