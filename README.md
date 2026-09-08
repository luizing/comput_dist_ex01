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
java DistribuicaBinomial
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
java DistribuicaBinomial 10 3 0.8 NONE
```

Exemplo com gráfico:

```bash
java DistribuicaBinomial 10 3 0.8 GRAPH
```

O parâmetro `GRAPH` não diferencia letras maiúsculas de minúsculas, portanto `graph`, `Graph` e `GRAPH` possuem o mesmo efeito.


#### Com a exibição do gráfico

## Simulador Estocástico


