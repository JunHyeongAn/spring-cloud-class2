package com.company.apigatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config>{
	@Data
	public static class Config {
		private String baseMessage;
		private boolean preLog;
		private boolean postLog;
	}
	public GlobalFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();
			
			log.info("Global Filter baseMessage : {}", config.getBaseMessage());
			
			if(config.isPreLog())
				log.info("Global Filter start : request id -> {}", request.getId());
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				if(config.postLog)
					log.info("Global Filter end : response code -> {}", response.getStatusCode());
			}));
		};
	}
}
