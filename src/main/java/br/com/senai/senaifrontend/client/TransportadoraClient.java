package br.com.senai.senaifrontend.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.senaifrontend.dto.Transportadora;

@Component
public class TransportadoraClient {
	
	@Value("${endpoint}")
	private String urlEndpoint;
	
	@Autowired
	private ObjectMapper mapper;
	
	private final String resource = "/transportadoras";
	
	@Autowired
	private RestTemplateBuilder builder;
	
	public Transportadora inserir(
			Transportadora novaTransportadora) {
		
		RestTemplate httpClient = builder.build();
		
		URI uri = httpClient.postForLocation(
				urlEndpoint + resource, novaTransportadora);
		
		Transportadora transportadoraSalva = httpClient
				.getForObject(urlEndpoint + uri.getPath(), 
						Transportadora.class);
		
		return transportadoraSalva;
		
	}
	
	public void alterar(Transportadora transportadoraSalva) {
		RestTemplate httpClient = builder.build();
		httpClient.put(urlEndpoint + resource, 
				transportadoraSalva);
	}
	
	public void excluir(Transportadora transportadoraSalva) {
		RestTemplate httpClient = builder.build();
		httpClient.delete(urlEndpoint + resource 
				+ "/id/" + transportadoraSalva.getId());
	}
	
	@SuppressWarnings("unchecked")
	public List<Transportadora> listarPor(String nomeFantasia){
		
		RestTemplate httpClient = builder.build();
		
		List<LinkedHashMap<String, Object>> response = httpClient.getForObject(
				urlEndpoint + resource + "/nome-fantasia/" + nomeFantasia, List.class);
		
		List<Transportadora> transportadoras = new ArrayList<Transportadora>();
		
		for (LinkedHashMap<String, Object> item : response) {
			Transportadora transportadora = mapper.convertValue(item, Transportadora.class);
			transportadoras.add(transportadora);
		}
		
		return transportadoras;
	}

}
