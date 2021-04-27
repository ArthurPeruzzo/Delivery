package com.arthur.delivery.config;

import com.arthur.delivery.entidades.*;
import com.arthur.delivery.entidades.enums.TipoPagamento;
import com.arthur.delivery.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Endereco endereco1 = new Endereco(null, "Coronel passos maia", "Centro", "Pato Branco", "PR");
        Endereco endereco2 = new Endereco(null, "Av.Brasil", "Centro", "Pato Branco", "PR");
        Endereco endereco3 = new Endereco(null, "Rua Jardim Souza", "Zona Norte", "Pato Branco", "PR");

        Cliente cliente1 = new Cliente(null, "Arthur", "arthur@gmail.com", "99323502", 12345674, endereco1);
        Cliente cliente2 = new Cliente(null, "Ernani", "ernani@gmail.com", "96324322", 142342414, endereco2);
        Cliente cliente3 = new Cliente(null, "Silvia", "silvia@gmail.com", "99321502", 142345574, endereco3);

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));

        Entregador entregador1 = new Entregador(null, "Joao", "joao@gmail.com", "323213", 321331);
        Entregador entregador2 = new Entregador(null, "Marcelo", "marcelo@gmail.com", "9879213", 45353331);
        Entregador entregador3 = new Entregador(null, "Felipe", "felipe@gmail.com", "95843213", 3876831);

        entregadorRepository.saveAll(Arrays.asList(entregador1, entregador2, entregador3));

        Entrega entrega1 = new Entrega(null, LocalDateTime.of(2021, 03, 20, 21, 30), LocalDateTime.of(2021, 03, 20, 22, 00), 4.3, entregador1);
        Entrega entrega2 = new Entrega(null, LocalDateTime.of(2021, 03, 21, 21, 14), LocalDateTime.of(2021, 03, 21, 22, 10), 4.2, entregador2);
        Entrega entrega3 = new Entrega(null, LocalDateTime.of(2021, 03, 22, 21, 00), LocalDateTime.of(2021, 03, 22, 22, 52), 3.0, entregador1);

        entregaRepository.saveAll(Arrays.asList(entrega1, entrega2, entrega3));

        Pedido pedido1 = new Pedido(null, LocalDateTime.of(2021, 03, 20, 20, 30), 10.00, TipoPagamento.DINHEIRO, entrega1,cliente1, null);
        Pedido pedido2 = new Pedido(null, LocalDateTime.of(2021, 03, 21, 20, 25 ), 10.00, TipoPagamento.CARTAO, entrega2, cliente2, null);
        Pedido pedido3 = new Pedido(null, LocalDateTime.of(2021, 03, 22, 20, 15), 10.00, TipoPagamento.DINHEIRO, entrega3, cliente3, null);

        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));

        Item item1 = new Item(null, "X-salada", 25.00, pedido1);
        Item item2 = new Item(null, "X-bacon", 30.00, pedido2);
        Item item3 = new Item(null, "pizza-quatro-queijos", 40.00, pedido3);

        itemRepository.saveAll(Arrays.asList(item1, item2, item3));

        //associacao item e pedido

        PedidoItem pedidoItem1 = new PedidoItem(item1, pedido1, 4, item1.getPreco());
        PedidoItem pedidoItem2 = new PedidoItem(item2, pedido1, 3, item2.getPreco());
        PedidoItem pedidoItem3 = new PedidoItem(item3, pedido2, 2, item3.getPreco());
        PedidoItem pedidoItem4 = new PedidoItem(item2, pedido3, 1, item2.getPreco());
        PedidoItem pedidoItem5 = new PedidoItem(item1, pedido2, 3, item1.getPreco());

        pedidoItemRepository.saveAll(Arrays.asList(pedidoItem1, pedidoItem2, pedidoItem3, pedidoItem4, pedidoItem5));

        Endereco enderecoRestaurante1 = new Endereco(null, "São Jorge", "Centro", "Sao domingos", "SC");
        Endereco enderecoRestaurante2 = new Endereco(null, "Av.Paulista", "zona norte", "São Paulo", "SP");
        Endereco enderecoRestaurante3 = new Endereco(null, "Rua JK", "Centro", "Pato Branco", "PR");

        Restaurante restaurante1 = new Restaurante(null ,"Rock Delivery", LocalTime.of(18, 30),LocalTime.of(23, 00),4.2, 33322211, "98991230", enderecoRestaurante1);
        Restaurante restaurante2 = new Restaurante(null ,"Viking Delivery", LocalTime.of(18, 30),LocalTime.of(23, 30),5.0, 65422211, "99943230", enderecoRestaurante2);
        Restaurante restaurante3 = new Restaurante(null ,"Los Hermanos Delivery", LocalTime.of(18, 15),LocalTime.of(22, 30),4.3, 6595211, "994721230", enderecoRestaurante3);

        restauranteRepository.saveAll(Arrays.asList(restaurante1, restaurante2, restaurante3));

        //associacao entre restaurante e pedido

        pedido1.setRestaurante(restaurante1);
        pedido2.setRestaurante(restaurante2);
        pedido3.setRestaurante(restaurante1);

        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));

        Comentario comentario1 = new Comentario(null, "Lanche muito gostoso e entrega rápida!", 4.5, cliente1, restaurante1);
        Comentario comentario2 = new Comentario(null, "A entrega atrasou muito", 3.0, cliente2, restaurante2);
        Comentario comentario3 = new Comentario(null, "A pizza de quatro queijos é muito boa!", 4.8, cliente3, restaurante3);
        Comentario comentario4 = new Comentario(null, "Lanche bom!", 4.8, cliente1, restaurante3);

        comentarioRepository.saveAll(Arrays.asList(comentario1, comentario2, comentario3, comentario4));

    }

}

