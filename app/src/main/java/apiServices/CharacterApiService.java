package apiServices;

import models.Info;
import models.NamedAPIResourceList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApiService {
    @GET("character/?page=1")
    Call<NamedAPIResourceList> getCharacter();

    @GET("character/")
    Call<NamedAPIResourceList> getCharacterByPage(@Query("?page=") int characterPage);

}
