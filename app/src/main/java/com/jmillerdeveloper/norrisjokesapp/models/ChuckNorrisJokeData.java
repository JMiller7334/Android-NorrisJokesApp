package com.jmillerdeveloper.norrisjokesapp.models;

import org.json.JSONException;
import org.json.JSONObject;

/*CHUCK NORRIS JOKE DATA
* this class holds joke data from the api.
* this classes data is transfer to the recycler.
* */
public class ChuckNorrisJokeData {
    private String id;
    private String value;
    private String iconUrl;
    private String createdAt;
    private String updatedAt;
    private String url;

    //none api related:
    private Boolean saved;

    //SETTERS FOR CLASS:
    public ChuckNorrisJokeData(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.value = json.getString("value");
        this.iconUrl = json.getString("icon_url");
        this.createdAt = json.getString("created_at");
        this.updatedAt = json.getString("updated_at");
        this.url = json.getString("url");

        //TODO: viewModel should determine if this data is true based on the data that will be saved in database.
        this.saved = false;
    }

    //GETTERS FOR CLASS:
    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getIsSaved(){
        return saved;
    }
}
