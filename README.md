# Computação Distribuida - Trabalho 01
### Objetivo

O objetivo deste trabalho é desenvolver e analisar um modelo de disponibilidade de serviços compostos por múltiplos servidores trabalhando em paralelo. Para isso, são definidos os seguintes objetivos:

1. **Desenvolver uma fórmula** que relacione o número de servidores disponíveis em paralelo, a quantidade mínima de servidores online necessária para o funcionamento do serviço e a probabilidade de um servidor estar online.

2. **Desenvolver um programa em Java** que receba os parâmetros definidos pelo usuário e retorne a probabilidade de disponibilidade calculada pela fórmula.

3. **Desenvolver uma simulação estocástica** capaz de reproduzir o cenário de um serviço composto por `n` servidores, permitindo comparar os resultados obtidos experimentalmente com os valores calculados pela fórmula.

  
## Desenvolvimento da Fórmula

Considere:

- `n`: número total de servidores;
- `k`: número mínimo de servidores online necessário para o funcionamento do serviço;
- `p`: probabilidade de um servidor estar online;
- `1-p`: probabilidade de um servidor estar offline.

A probabilidade de exatamente `i` servidores estarem online é dada pela distribuição binomial:

$$
P(X=i)=\binom{n}{i}p^i(1-p)^{n-i}
$$

Como o serviço permanece disponível quando pelo menos `k` servidores estão online, a probabilidade de disponibilidade do serviço é:

$$
P(X\geq k)=\sum_{i=k}^{n}\binom{n}{i}p^i(1-p)^{n-i}
$$


### Implementação em Java

Conforme proposto pelo enunciado, o projeto possui uma implementação em Java para calcular a probabilidade de disponibilidade de um serviço.

O programa pode ser executado de duas formas:

#### Modo interativo

Execute o programa sem argumentos. Os valores de `n`, `k` e `p` serão solicitados pelo terminal:

```bash
java Formula.DistribuicaBinomial
```

#### Modo por argumentos

Os parâmetros podem ser informados diretamente na execução, seguindo a ordem:

```text
n k p [GRAPH]
```

Onde:

* `n` — número total de servidores;
* `k` — quantidade mínima de servidores ativos necessária para o serviço funcionar;
* `p` — probabilidade de um servidor estar online;
* `GRAPH` — parâmetro opcional que solicita a exibição do gráfico.

Exemplo sem gráfico:

```bash
java Formula.DistribuicaBinomial 10 3 0.8 NONE
```

Exemplo com gráfico:

```bash
java Formula.DistribuicaBinomial 10 3 0.8 GRAPH
```

O parâmetro `GRAPH` não diferencia letras maiúsculas de minúsculas, portanto `graph`, `Graph` e `GRAPH` possuem o mesmo efeito.

## Simulador Estocástico

Para validar experimentalmente a probabilidade de disponibilidade obtida pela distribuição binomial, foi desenvolvido um simulador estocástico em Java.

O simulador reproduz o funcionamento de um serviço composto por `n` servidores independentes. Para cada servidor, é realizado um sorteio aleatório baseado na probabilidade `p`, determinando se o servidor está **online** ou **offline**.

Após a definição do estado de todos os servidores, o simulador verifica se a quantidade de servidores online é suficiente para manter o serviço disponível. O serviço é considerado disponível quando o número de servidores ativos é maior ou igual a `k`.

### Funcionamento

Para cada rodada da simulação:

1. São criados `n` servidores;
2. Cada servidor recebe uma probabilidade `p` de estar online;
3. O estado de cada servidor é determinado aleatoriamente;
4. É contabilizado o número de servidores online;
5. O serviço é considerado disponível caso o número de servidores online seja maior ou igual a `k`;
6. O resultado da rodada é armazenado para posterior cálculo da disponibilidade experimental.

Ao executar várias rodadas, a probabilidade experimental de disponibilidade é obtida pela razão entre o número de rodadas em que o serviço permaneceu disponível e o número total de rodadas:

$$
P_{sim} =
\frac{\text{número de rodadas em que o serviço esteve disponível}}
{\text{número total de rodadas}}
$$

### Execução

O simulador recebe os mesmos parâmetros utilizados no cálculo da fórmula, acrescentando a quantidade de rodadas da simulação:

```text
n k p r
```

Onde:

* `n` — número total de servidores;
* `k` — quantidade mínima de servidores ativos necessária para o serviço funcionar;
* `p` — probabilidade de um servidor estar online;
* `r` — quantidade de rodadas da simulação.


#### Modo interativo

Execute o programa sem argumentos. Os valores de `n`, `k` e `p` serão solicitados pelo terminal:

```bash
java Simulador.SimuladorEstocastico
```
#### Modo por argumentos

Exemplo:

```bash
java Simulador.SimuladorEstocastico 10 3 0.8 10000
```

Nesse exemplo, serão simuladas `10000` configurações de um serviço composto por `10` servidores, sendo necessário que pelo menos `3` estejam online e considerando uma probabilidade de `0.8` para cada servidor estar funcionando.

## Comparação entre fórmula e simulador

Essa comparação é feita na classe main.java

Execute-a e insira valores manualmente via terminal para que seja calculada a formula e testado o simulador

A simulação permite verificar experimentalmente o resultado obtido pela fórmula. Para isso, são utilizados os mesmos valores de `n`, `k` e `p` nos dois métodos.

Por exemplo, considerando:

```text
n = 10
k = 3
p = 0.8
```

o programa calcula inicialmente a probabilidade teórica pela distribuição binomial e, em seguida, executa `r` rodadas do simulador estocástico.

Os resultados podem ser comparados da seguinte maneira:

| Método                |             Resultado |
| --------------------- | --------------------: |
| Distribuição Binomial |            `P(X ≥ k)` |
| Simulação Estocástica | `disponibilidade / r` |

Quanto maior for o número de rodadas, espera-se que o resultado experimental se aproxime do valor obtido pela fórmula, devido à convergência da frequência observada para a probabilidade do evento.

Como a simulação utiliza geração de números aleatórios, o resultado pode variar entre diferentes execuções. Portanto, a simulação não necessariamente produzirá exatamente o mesmo valor calculado pela fórmula.

A diferença entre os dois resultados representa o erro experimental da simulação. Esse erro tende a diminuir conforme aumenta a quantidade de rodadas realizadas.
