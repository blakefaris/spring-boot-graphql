package com.blakefaris.graphql.resolvers;

import com.blakefaris.graphql.CharacterRepository;
import com.blakefaris.graphql.types.Character;
import com.blakefaris.graphql.types.Droid;
import com.blakefaris.graphql.types.Episode;
import com.blakefaris.graphql.types.Human;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final CharacterRepository characterRepository;

    public Query(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Character hero(Episode episode) {
        return episode != null ? characterRepository.getHeroes().get(episode) : characterRepository.getCharacters().get("1000");
    }

    public Human human(String id, DataFetchingEnvironment env) {
        return (Human) characterRepository.getCharacters().values().stream()
            .filter(character -> character instanceof Human && character.getId().equals(id))
            .findFirst()
            .orElseGet(null);
    }

    public Droid droid(String id) {
        return (Droid) characterRepository.getCharacters().values().stream()
            .filter(character -> character instanceof Droid && character.getId().equals(id))
            .findFirst()
            .orElseGet(null);
    }

    public Character character(String id) {
        return characterRepository.getCharacters().get(id);
    }
}
