package com.example.SpringAIDemo.structuredOutputConverter.ollama.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"actor", "movies"})
public record ActorsFilms(String actor, List<String> movies) {}