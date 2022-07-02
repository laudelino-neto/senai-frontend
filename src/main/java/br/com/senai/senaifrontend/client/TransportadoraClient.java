package br.com.senai.senaifrontend.client;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.senai.senaifrontend.dto.Transportadora;

@Component
public class TransportadoraClient {
	
	@Value("${endpoint}")
	private String urlEndpoint;
	
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
		List<Transportadora> transportadoras = httpClient
				.getForObject(urlEndpoint + resource 
						+ "/nome-fantasia/" + nomeFantasia, 
						List.class);
		return transportadoras;
	}

}
