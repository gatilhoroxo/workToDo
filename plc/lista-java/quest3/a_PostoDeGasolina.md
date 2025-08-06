...
> Você é responsável pelo sistema de TI de um posto de combustível. O sistema é implementado seguindo o paradigma orientado a objetos e é composto por 3 classes, 1 interface, 1 enum e 2 exceções: <b> Automovel, Gasolina, Etanol, BombaDeCombustivel, TipoMotor,
CombustivelNaoCompativel,CombustivelOverflow </b> respectivamente.

### Instruções

- <b> TipoMotor </b> \
    Um enumerador para definir os tipos de combústivel aceitos pelo automóvel, sendo eles:
    - GASOLINA \
    suporta apenas gasolina
    - ETANOL \
    suporta apenas etanol
    - FLEX \
    suporta ambos os tipos de combústives.

- <b> Automovel classe </b> \
    representando os veículos a serem abastecidos
    - Atributos
        - double CombustivelAtual \
        representa a capacidade preenchida atualmente no tanque em litros.
        - double capacidadeMaximaTanque \
        representa a capacidade do tanque em litros, assumindo que o mesmo esteja completo.
        - TipoMotor motor \
        representa o tipo de combústivel suportado pelo motor
    - Métodos
        - gets e sets para combustivelAtual
        - get para capacidadeMaximaTanque

- <b> Gasolina </b> \
    classe que representa uma bomba de combustivel para gasolina
    - Atributos 
        - double precoLitro representa o valor do litro da gasolina
    - Métodos 
        - implemente interface de BombaDeCombustivel

- <b> Etanol </b> 
    classe que representa uma bomba de combústivel par etanol
    - Atributos 
        - double precoLitro representa o valor do litro do etanol
    - Métodos
        - implementa a interface de BombaDeCombustivel

- <b> BombaDeCombustivel </b> interface
    - Métodos
        - `public void abastecer(Automovel automovel, double quantidadeLitros)` \
        abastece o tanque de combustivel, aumentando o combustivelAtual.
        - `public void calcularCusto(double quantidadeLitros)` \
        imprime informações do abastecimento como tipo do combústivel, quantidade abastecidae custo do abastecimento
        - `public void ajustarPreco(double novoPreco)` \
        altera o valor do litro do combustivel
        - `CombustivelNaoCompativel` \
        exception levantada quanto o automóvel abastecido não é compatível com o tipo de combústivel da bomba.
        - `CombustivelOverflow` \
        exception levantada quando a quantidade a ser abastecida é maior que o volume vazio da capacidadeMaximaTanque.

- <b> Main </b> \
    Implementar os seguintes testes
    - Caso de combústivel incompatível
    - Caso de abastecimento em excesso
    - Abastecimento de gasolina bem sucedido, mas com limite do tanque excedido
    - Abastecimento de gasolina após reajuste de preço.
