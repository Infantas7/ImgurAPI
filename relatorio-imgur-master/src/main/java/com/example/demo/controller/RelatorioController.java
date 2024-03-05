package com.example.demo.controller;


import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDto;
import com.example.demo.service.RelatorioService;

@RestController
@RequestMapping("posts")
public class RelatorioController {

	// Injeção de dependência do RelatorioService
	public RelatorioController(RelatorioService relatorioService) {
		this.relatorioService = relatorioService;
	}
	private final RelatorioService relatorioService;

	// Mapeia requisições HTTP GET para o caminho "/posts" para este método
	@GetMapping
	private PostDto buscarPosts(@RequestParam String tag) throws URISyntaxException {
		// Chama o método buscarPosts da instância de RelatorioService
		// e retorna o resultado como um objeto PostDto
		return relatorioService.buscarPosts(tag);
	}
}
