package com.movieflix.mapper;
import com.movieflix.controllers.request.StreamingRequest;
import com.movieflix.controllers.response.StreamingResponse;
import com.movieflix.entities.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    //Função para pegar um StreamingRequest passado do usuario para um Streaming Entity
    public static Streaming toStreaming(StreamingRequest request){
        return Streaming
                .builder()
                .name(request.name())
                .build();
    }

    //Função para pegar um Category entity e transformar em um CategoryResponse
    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
