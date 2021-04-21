package com.arthur.delivery.config;

import com.arthur.delivery.entidades.Cliente;
import com.arthur.delivery.entidades.Endereco;
import com.arthur.delivery.entidades.Entregador;
import com.arthur.delivery.repositories.ClienteRepository;
import com.arthur.delivery.repositories.EnderecoRepository;
import com.arthur.delivery.repositories.EntregadorRepository;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Override
    public void run(String... args) throws Exception {
        Endereco endereco1 = new Endereco(null, "Coronel passos maia", "Centro", "Sao domingos", "SC");
        Endereco endereco2 = new Endereco(null, "Av.Brasil", "Centro", "São Paulo", "SP");
        Endereco endereco3 = new Endereco(null, "Rua Jardim Souza", "Centro", "Pato Branco", "PR");

        Cliente cliente1 = new Cliente(null, "Arthur", "arthur@gmail.com", "99323502", 12345674, endereco1);
        Cliente cliente2 = new Cliente(null, "Ernani", "ernani@gmail.com", "96324322", 142342414, endereco2);
        Cliente cliente3 = new Cliente(null, "Silvia", "silvia@gmail.com", "99321502", 142345574, endereco3);

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));

        Entregador entregador1 = new Entregador(null, "Joao", "joao@gmail.com", "323213", 321331);
        Entregador entregador2 = new Entregador(null, "Marcelo", "marcelo@gmail.com", "9879213", 45353331);
        Entregador entregador3 = new Entregador(null, "Felipe", "felipe@gmail.com", "95843213", 3876831);

        entregadorRepository.saveAll(Arrays.asList(entregador1, entregador2, entregador3));
    }

}

