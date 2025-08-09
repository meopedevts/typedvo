# TypedVO

**TypedVO** é uma biblioteca Kotlin criada para facilitar o trabalho com o JAPE (API de acesso ao banco de dados do ERP Sankhya).  
No modelo tradicional com `DynamicVO`, o acesso exige conhecimento prévio do banco e do dicionário de dados, além de lidar manualmente com nomes de colunas e casts.

----------

## 🎯 Objetivo

O TypedVO cria uma camada **tipada** sobre o `DynamicVO`, permitindo mapear colunas do banco diretamente no código.  
Com isso, você ganha:

-   **Segurança de tipos** — sem erros de conversão.

-   **Produtividade** — menos código repetitivo.

-   **Compatibilidade total** — continua usando tudo que o `DynamicVO` oferece.


----------

## 🚀 Criando um VO tipado

```kotlin
class CabecalhoNotaVO(val vo: DynamicVO) : DynamicVO by vo {
    var nuNota: BigDecimal by DelegateNotNull()
    var codParc: BigDecimal by DelegateNotNull()
}

```

**Como funciona:**

-   O `DynamicVO` original é recebido no construtor.

-   Usamos `by vo` para delegar todos os métodos originais.

-   As colunas do banco viram **variáveis tipadas**.

-   Os nomes das variáveis devem ser **iguais** aos das colunas no banco.


----------

## 🛠 DAO tipado

```kotlin
class CabecalhoNotaDAO : BaseDAO<CabecalhoNotaVO>("CabecalhoNota", ::CabecalhoNotaVO)

```

----------

## 💻 Uso com TypedVO

**Kotlin**

```kotlin
val cabecalhoVO = CabecalhoNotaDAO().findByPK(BigDecimal(100))
val codParc = cabecalhoVO.codParc
cabecalhoVO.codParc = BigDecimal(10)

```

**Java**

```java
CabecalhoNotaVO cabecalhoVO = new CabecalhoNotaDAO().findByPK(new BigDecimal(100));
BigDecimal codParc = cabecalhoVO.getCodParc();
cabecalhoVO.setCodParc(new BigDecimal(10));

```

----------

## 🔍 Comparando com o DynamicVO nativo

**DynamicVO (Kotlin)**

```kotlin
val cabecalhoVO: DynamicVO = JapeFactory.dao("CabecalhoNota").findByPK(BigDecimal(100))
val codParc: BigDecimal = cabecalhoVO.asBigDecimal("CODPARC")
cabecalhoVO.setProperty("CODPARC", BigDecimal(10))

```

**DynamicVO (Java)**

```java
DynamicVO cabecalhoVO = JapeFactory.dao("CabecalhoNota").findByPK(new BigDecimal(100));
BigDecimal codParc = cabecalhoVO.asBigDecimal("CODPARC");
cabecalhoVO.setProperty("CODPARC", new BigDecimal(10));

```

**TypedVO (Kotlin)**

```kotlin
val cabecalhoVO = CabecalhoNotaDAO().findByPK(BigDecimal(100))
val codParc = cabecalhoVO.codParc
cabecalhoVO.codParc = BigDecimal(10)

```

**TypedVO (Java)**

```java
CabecalhoNotaVO cabecalhoVO = new CabecalhoNotaDAO().findByPK(new BigDecimal(100));
BigDecimal codParc = cabecalhoVO.getCodParc();
cabecalhoVO.setCodParc(new BigDecimal(10));

```

**Principais diferenças com TypedVO:**

-   Não há strings de coluna espalhadas pelo código.

-   Não há casts manuais.

-   A tipagem é garantida pelo compilador.

-   Código mais conciso e expressivo.


----------

## ⚙️ Métodos nativos do DynamicVO

Por delegar para o `DynamicVO`, sua VO tipada mantém **todas as funcionalidades nativas**, incluindo **acesso relacional**:

```kotlin
class ItemNotaVO(val vo: DynamicVO) : DynamicVO by vo {
    var codProd: BigDecimal by DelegateNotNull()
    var descrProd: String by DelegateNotNull()
}

val itemNotaVO: ItemNotaVO = /* ... */
val descrProd = itemNotaVO.asString("Produto.DESCRPROD")

```

----------

## 📦 Delegates disponíveis

-   `Delegate` → para colunas **opcionais** (`null` permitido)

-   `DelegateNotNull` → para colunas **obrigatórias**

-   `DelegateBoolean` → converte campos `S/N` para `true/false`

-   `DelegateEnum` → para campos de lista de opções (nulos)

-   `DelegateEnumNotNull` → para campos de lista de opções (não nulos)


```kotlin
var codParcTransp: BigDecimal? by Delegate()
var statusNota: StatusNota by DelegateEnumNotNull(StatusNota::class.java)
var pendente: Boolean by DelegateBoolean()

```

----------

## 📌 Extensions para conversão

O TypedVO fornece um pacote de **extensions** para conversão entre `EntityVO`, `DynamicVO` e VOs tipados.

**Kotlin**

```kotlin
import org.meopedevts.typedvo.extensions.*

val dynamicVO: DynamicVO = /* ... */
val entityVO: EntityVO = dynamicVO.asEntityVO()

val typedVO: CabecalhoNotaVO = dynamicVO.asTypedVO()
val typedFromEntity: CabecalhoNotaVO = entityVO.asTypedVO()

val dynamicFromEntity: DynamicVO = entityVO.asDynamicVO()

```

**Java**

```java
import static org.meopedevts.typedvo.extensions.ExtensionsKt.*;

DynamicVO dynamicVO = /* ... */;
EntityVO entityVO = dynamicVO.asEntityVO();

CabecalhoNotaVO typedVO = dynamicVO.asTypedVO(CabecalhoNotaVO.class);
CabecalhoNotaVO typedFromEntity = entityVO.asTypedVO(CabecalhoNotaVO.class);

DynamicVO dynamicFromEntity = entityVO.asDynamicVO();

```

### Funções disponíveis

Função

Descrição

`DynamicVO.asEntityVO()`

Converte `DynamicVO` para `EntityVO`

`DynamicVO.asTypedVO<T>()`

Converte `DynamicVO` para o VO tipado informado

`EntityVO.asDynamicVO()`

Converte `EntityVO` para `DynamicVO`

`EntityVO.asTypedVO<T>()`

Converte `EntityVO` diretamente para o VO tipado informado

> **Importante:** `asTypedVO()` exige que o VO tipado tenha um construtor que receba `DynamicVO` como parâmetro.

----------

## 📚 Como usar

1.  Crie uma VO tipada herdando de `DynamicVO`.

2.  Mapeie os atributos da entidade com os delegates adequados.

3.  Crie um DAO herdando de `BaseDAO`.

4.  Use os métodos `find`, `findOne` e `findByPK` para buscar dados tipados.

5.  Utilize métodos nativos do `DynamicVO` diretamente no VO tipado.

6.  Use as extensions para conversões rápidas entre `EntityVO`, `DynamicVO` e VOs tipados.


----------

## 🤝 Contribuindo

Sugestões, melhorias e exemplos são bem-vindos para evoluir a biblioteca e sua documentação.

----------

## 📄 Licença

[Apache License 2.0](LICENSE)

---

**TypedVO** é para quem deseja produtividade, segurança e código idiomático ao integrar Kotlin com Sankhya JAPE.
