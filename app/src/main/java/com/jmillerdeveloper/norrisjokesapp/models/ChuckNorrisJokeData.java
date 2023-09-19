package com.jmillerdeveloper.norrisjokesapp.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/*CHUCK NORRIS JOKE DATA
* this class holds joke data from the api.
* this classes data is transfer to the recycler.
* */
public class ChuckNorrisJokeData {
    private final String id;
    private final String value;
    private final String iconUrl;
    private final String createdAt;
    private final String updatedAt;
    private final String url;

    //none api related:
    private final Boolean saved;

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

    //OVERRIDES:

    /*INFO:
    * checks if the object passed in has the same id this
    * instance of the class and returns true/false based on that.
    * */
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChuckNorrisJokeData that = (ChuckNorrisJokeData) obj;
        return Objects.equals(id, that.id);
    }

    /*INFO:
    * generates a hash code based on the unique identifier of the class(Id)
    * */
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
