package models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NamedAPIResourceList {
    @SerializedName("results")
    @Expose
    private List<Character> results;
    @SerializedName("info")
    @Expose
    private Info info;

    public NamedAPIResourceList() {
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
