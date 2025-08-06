...
> Esta atividade tem como foco o levantamento e tratamento de exceções personalizadas em Java, simulando um carrinho de compras.

Você deverá implementar o código para tratar das seguintes situações por meio de exceções:

- ItemAleradyExists \
    lançada quando há tentativa de adicionar um item com o mesmo nome de outro que já esteja presente no carrinho de compras.
- NotEnoughSpace \
    lançadas quando não há mais espaço para adicionar novos itens (limite de 10 itens diferentes por carrinho)
- NotFound \
    lançada ao tentar remover ou buscar um item que não existe no carrinho
- NullParameter \
    lançada quando algum método recebe um parâmetro nulo

- A implementação das classes `Cart` e `Buy` estão incompletas e precisam ser modificas para implementar corretamente o tratamento e levantamento das exceções descritas.
- A classe Main já está pronta e não deve ser modificada. Serva apenas para facilitar os testes do comportamento do sistema e verificar que as exeções estão sendo levantadas corretamente.
- Todas as exceções mencionadas já estão declaradas como classes. 
\
`Buy` \
Classe reponsável pelos métodos de compra, adicionar, remover e buscar itens. Deve conter o tratamento das exceções.
`Cart` \
Classe que gerencia o estado do carrinho e a lógica de manipulação dos itens. Deve conter o
levantamento das exceções.

