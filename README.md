# Relatório de Especificação de Casos de Teste
**Componente Curricular:** Engenharia de Computação / Testes de Software Automatizados  
**Alvo do Teste:** Calculadora Online Básica (Funcionalidade de Exponenciação $x^n$)

Para validação completa da funcionalidade de potenciação, foram mapeados 8 cenários de teste aplicando as técnicas de Particionamento por Classes de Equivalência e Análise de Valor Limite.

---

### Detalhamento dos Cenários e Motivações

#### Caso de Teste 1: Exponenciação Padrão com Inteiros Positivos
* **Entradas:** Base ($x$) = 2, Expoente ($n$) = 3  
* **Resultado Esperado:** 8  
* **Motivação:** Age como o cenário clássico de sucesso ("Caminho Feliz"). Verifica se a comunicação básica entre o WebDriver, os seletores de ID da página e o motor lógico de cálculo está operacional para inteiros positivos comuns.

#### Caso de Teste 2: Base Positiva com Expoente Zero
* **Entradas:** Base ($x$) = 5, Expoente ($n$) = 0  
* **Resultado Esperado:** 1  
* **Motivação:** Avalia uma propriedade algébrica fundamental. Por definição matemática, qualquer número diferente de zero elevado a zero deve retornar exatamente 1. Testa se o sistema trata essa regra de exceção.

#### Caso de Teste 3: Base Negativa com Expoente Par
* **Entradas:** Base ($x$) = -3, Expoente ($n$) = 2  
* **Resultado Esperado:** 9  
* **Motivação:** Valida a lei dos sinais na multiplicação cumulativa. Uma base negativa multiplicada por si mesma um número par de vezes deve obrigatoriamente produzir um produto estritamente positivo (ex: $-3 \times -3 = 9$).

#### Caso de Teste 4: Base Negativa com Expoente Ímpar
* **Entradas:** Base ($x$) = -2, Expoente ($n$) = 3  
* **Resultado Esperado:** -8  
* **Motivação:** Complementa o cenário anterior. Garante que se a base for negativa e o expoente for ímpar, o sinal negativo é conservado no visor final (ex: $-2 \times -2 \times -2 = -8$).

#### Caso de Teste 5: Expoente Negativo (Inversão de Base)
* **Entradas:** Base ($x$) = 4, Expoente ($n$) = -1  
* **Resultado Esperado:** 0.25  
* **Motivação:** Analisa o comportamento do sistema para limites abaixo de zero no expoente. Matematicamente, isso exige a inversão fracionária da base ($1 / x^n$), forçando o motor da calculadora a operar com saídas decimais de ponto flutuante.

#### Caso de Teste 6: Base Zero com Expoente Positivo
* **Entradas:** Base ($x$) = 0, Expoente ($n$) = 5  
* **Resultado Esperado:** 0  
* **Motivação:** Valida o comportamento da base nula. Zero multiplicado por si mesmo qualquer número n de vezes deve sempre retornar zero.

#### Caso de Teste 7: Base Decimal (Ponto Flutuante)
* **Entradas:** Base ($x$) = 1.5, Expoente ($n$) = 2  
* **Resultado Esperado:** 2.25  
* **Motivação:** Certifica que as caixas de entrada de texto e os seletores decimais do site suportam e processam corretamente números reais com separadores de ponto flutuante.

#### Caso de Teste 8: Base Unitária com Expoente de Magnitude Elevada
* **Entradas:** Base ($x$) = 1, Expoente ($n$) = 99  
* **Resultado Esperado:** 1  
* **Motivação:** Valida a propriedade de identidade da base 1. Além disso, a inserção de um expoente de valor alto (99) avalia a estabilidade de desempenho e se o laço de processamento do site não acarreta em estouro de memória (stack overflow).
