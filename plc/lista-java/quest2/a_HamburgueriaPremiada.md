...
> Na Hamburgueria Big Food, o CEO Mr Big, aproveitou o mês da Comida Ogra em Recife e decidiu lançar uma promoção no seu restaurante: o mês do BigFats! \
> Ao longo de setembro, a cada compra de um refri, batata ou hamburguer, o cliente irá ganhar pontos. Ao final do mês, os 10 clientes com mais pontos ganham um voucher Ogro que pode ser trocado pelo combo Ogro do restaurente, que será revelado apenas no final do mês. \
> E um detalhe! Clientes VIPs da empresa com cadastro realizado até agosto terão um bônus nos pontos acumulados, dando mais chances de ganhar a competição. Outro detalhe: a cada 10 Reais em compras o cliente ganha 1 ponto extra. Esse ponto extra é inteiro, ou seja: \
 ```R$ 35,00 em compras -> 3 pontos extras``` \
 ```6 compras de R$5,00 -> 0 pontos extras ``` (pois individualmente nenhuma atinge o limite inferior de R$10,00) \
> A ideia foi um sucesso e foi parar em todos os jornais de Recife! Porém há um problema, Mr Big não sabe programar, então não há possibilidade dele implementar o sistema descrito... até ele conhecer você! Em troca de 2 sachês de Ketchup e um aperto de mão, você aceitou o desafio: montar o sistema do Big Fats!

### Instruções

- Usando interfaces e classes abstratas, modele os diferentes tipos de clientes (Normal e VIP) e crie um sistema com menu que represente os seguintes atributos:
    - Preço do hamburguer
    - Preço da batata
    - Preco do refri
    - Bônus VIP <b> Bônus VIP é um número entre (0,1) exclusivo </b>

- E os seguintes métodos
    - Adicionar cliente
        - Adicionando um cliente, envolve o tipo e nome do mesmo. Lembre-se que para todo e qualquer cliente a pontuação inicial é sempre zero!
    - Adicionar compra 
        - Adicionando uma compra, deve-se registrar o tipo do cliente, nome do mesmo e quantidade de produtos, os produtos são os atributos mencionados acima.


Lembre-se de computar a pontuação parcial durante aquele mês para o cliente, como no exemplo abaixo:

    Tipo: VIP 
    Nome: João 
    Hamburguer: 3
    Batata: 2
    Refri: 0
`pontosSemBonus = (precoItem / 10)*qtdItem` (divisão sem resto!) \
`pontosComBonus = Ceil(bonusVip * pontosSemBonus)*qtdItem` (Ceil é a função teto) 

E adicionar aos pontos obtidos em compras prévias. 
-  Finalizar promoção
    - Ao finalizar o mês, liste os usuários com mais pontos e imprima se são clientes VIP’s ou normais.
    