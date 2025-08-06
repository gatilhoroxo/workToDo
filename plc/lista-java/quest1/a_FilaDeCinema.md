...
> Você deve criar uma fila que faz com que as pessoas mais velhas fiquem sempre na frente de alguém mais jovem, e que existem dois tipos de ingresso, um para os adultos e um para as crianças. Use o conceito de polimorfismo para diferenciar o tipo de ingresso que a pessoa terá e, usando os conceitos de generics, faça com que essa fila só aceite objetos que herdam do tipo Pessoa que você deve criar.

### Intruções

- Crie um ```enum Ticket``` que tenha dois tipos de ingresso disponível: ADULTO e CRIANCA

- Crie um a classe abstrata chamada Pessoa que implementa a interface Comparable essa classe deve ter um único método abstrato chamado ```getTicketType()```, além desse método, a classe deve conter os outros atributos e métodos.
  - Atributos
    - ```Integer idade```
    - ```String nome```
    - Obs: use o construtor de Pessoa para inicializar ambos os atributos.
  - Métodos:
    - ```Integer compareTo(Person person)```
    - O método que deve ser implementado quando você criar uma classe que implementa a interface Comparable (use a idade para implementar esse método)
    - ```String getName()```
    - ```Integer getAge()```
    - ```void toString()```
      - O método retorna: ```name+“:”+age+“[“+getTicketType()+”]”;```


- Crie duas classes que estemdem a classe Pessoa:
  - ```Adulto```
  - ```Criança```
  - Obs: cada classe deve retornar o tipo de ingresso correspondente com o seu tipo quando implementar a classe Pessoa.
- Crie a classe Queue que deve aceitar apenas objetos que implementam a classe Pessoa. (use os conceitos de generics) e adicione os seguintes atributos e métodos na fila.
  - Atributos
    - ```ArrayList Pessoas```
      Obs: use o construtor da classe para dizer qual a capacidade inicial da fila e se nenhum valor for informado, use 10 como um valor padrão.
  - Métodos:
    - ```<T>push(T pessoas)```
      lembre-se de fazer com que as pessoas mais velhas sempre sejam inseridas antes das mais novas.
    - ```<T>pop()```
    - ```boolean isEmpty()```
    - Obs: a intenção não é testar a melhor implementação quando fizer o push na fila, inclusive, recomendo que faça da seguinte forma: sempre insira no array e dê um sort pela ordem reversa, a classe Collections do java pode simplificar o processo!

