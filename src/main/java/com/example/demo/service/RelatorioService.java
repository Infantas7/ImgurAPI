package com.example.demo.service;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.PostDto;

@Service
public class RelatorioService {

	// Definindo a quantidade desejada de posts
	private static final Integer QUANTIDADE_POSTS = 500;

	// Injeção de dependência do RestTemplate
	private final RestTemplate restTemplate;

	public RelatorioService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Método principal para buscar posts com uma tag específica.
	 *
	 * @param tag A tag a ser usada na busca de posts.
	 * @return Um objeto PostDto contendo os posts encontrados.
	 * @throws URISyntaxException Caso ocorra um erro na construção da URI.
	 */
	public PostDto buscarPosts(String tag) throws URISyntaxException {
		int quantidadePostsAtuais = 0;
		int paginaAtual = 0;

		List<PostDto.Data> posts = new ArrayList<>();

		// Loop enquanto a quantidade de posts atual for menor ou igual à quantidade desejada
		while (quantidadePostsAtuais <= QUANTIDADE_POSTS) {
			// Busca posts na página atual usando o método buscarPosts(int, String)
			PostDto postsCarregados = buscarPosts(paginaAtual, tag);

			int quantidadesPostsCarregados = postsCarregados.getData().size();

			// Atualiza a quantidade total de posts carregados
			quantidadePostsAtuais += quantidadesPostsCarregados;

			// Verifica se a quantidade total de posts carregados ultrapassa a quantidade desejada
			if (quantidadePostsAtuais >= QUANTIDADE_POSTS) {
				// Calcula a quantidade de posts a serem adicionados para atingir exatamente 500
				int quantidadeParaFechar500 = quantidadesPostsCarregados - (quantidadePostsAtuais - QUANTIDADE_POSTS);

				// Adiciona os posts necessários para atingir exatamente 500
				posts.addAll(postsCarregados.getData().subList(0, quantidadeParaFechar500));
			} else {
				// Adiciona todos os posts carregados à lista
				posts.addAll(postsCarregados.getData());
			}

			// Incrementa o número da página para a próxima iteração
			paginaAtual++;
		}

		// Retorna um novo objeto PostDto contendo os posts encontrados
		return new PostDto(posts);
	}

	/**
	 * Método para buscar posts em uma página específica com uma tag.
	 *
	 * @param page O número da página a ser buscada.
	 * @param tag  A tag a ser usada na busca de posts.
	 * @return Um objeto PostDto contendo os posts encontrados na página especificada.
	 * @throws URISyntaxException Caso ocorra um erro na construção da URI.
	 */
	public PostDto buscarPosts(int page, String tag) throws URISyntaxException {
		// Constrói a URL para a busca de posts no Imgur
		String urlImgur = "https://api.imgur.com/3/gallery/search/top/all/" + page;

		// Constrói a URI com a URL e a tag
		URI uri = new URI(urlImgur + "?q=" + tag);

		// Constrói a entidade de requisição com cabeçalhos e tipo de mídia
		RequestEntity<Void> requestEntity = RequestEntity.get(uri)
				.header(HttpHeaders.AUTHORIZATION, "Client-ID c3ba4d46fb49f9e")
				.accept(MediaType.APPLICATION_JSON)
				.build();

		// Realiza a requisição utilizando o RestTemplate e retorna o corpo da resposta como um objeto PostDto
		return restTemplate.exchange(requestEntity, PostDto.class).getBody();
	}

}
