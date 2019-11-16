package gt.gob.sat.uip.service_asignacionesrevisores;

import gt.gob.sat.arquitectura.microservices.util.FileStorageProperties;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableConfigurationProperties({FileStorageProperties.class})
@ComponentScan(basePackages={"gt.gob.sat.arquitectura.microservices","gt.gob.sat.uip.service_asignacionesrevisores"})
public class Application {

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }
	
    /**
     * Permite aplicar balanceo por medio de Ribbon al utilizar el restTemplate. 
     */
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    /**
     * Configuración de la instancia de Eureka.
     */
    @Autowired EurekaInstanceConfigBean eurekaInstanceConfig;
    
    /**
     * ETAG permite al cliente manejar cache y ahorrar ancho de banda cuando el recurso solicitado no ha cambiado.
     * En este caso en lugar de recibir el codigo 200 OK recibe el 304 Not Modified.
     * @return
     */
    @Bean
    public Filter etagFilter() {
        return new ShallowEtagHeaderFilter();
    }
    
	/**
	 * Identificacion de archivos con mensajes.
	 * @return
	 */
    @Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
    
    /**
     * Forza a que la codificacion sea en UTF-8.
     * @return
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
