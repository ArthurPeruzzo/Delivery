package com.arthur.delivery.config;

import com.arthur.delivery.entidades.Cliente;
import com.arthur.delivery.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration //fala que essa classe é especifica para configuração
@Profile("test") //especifica para o perfil de teste
public class TesteConfig implements CommandLineRunner { //irá executar o método run assim que o programa for executado

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        Cliente cliente1 = new Cliente(null, "Arthur", "arthur@gmail.com", "99323502", 12345674);
        Cliente cliente2 = new Cliente(null, "Ernani", "ernani@gmail.com", "96324322", 142342414);
        Cliente cliente3 = new Cliente(null, "Silvia", "silvia@gmail.com", "99321502", 142345574);

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
    }
}

