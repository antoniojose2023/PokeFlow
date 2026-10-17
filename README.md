# PokeFlow

> Navegue e busque seus pokémons favoritos.

**PokeFlow** é um aplicativo Android nativo, escrito em **Kotlin**, que consome a [PokéAPI](https://pokeapi.co/) para listar pokémons com paginação infinita, permitir a busca por nome e exibir uma tela de detalhes com altura, peso, experiência base e stats.

![Android](https://img.shields.io/badge/Android-minSdk%2024-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin&logoColor=white)
![Arquitetura](https://img.shields.io/badge/Arquitetura-MVVM-orange)
![API](https://img.shields.io/badge/API-PokéAPI-red)

---

## Sumário

- [Screenshots](#screenshots)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Fluxo da aplicação](#fluxo-da-aplicação)
- [API utilizada](#api-utilizada)
- [Como executar](#como-executar)
- [Testes](#testes)
- [Melhorias futuras](#melhorias-futuras)
- [Autor](#autor)

---

## Screenshots

| Splash | Listagem | Detalhe |
|:------:|:--------:|:-------:|
| <img src="docs/screenshots/splash.png" width="240" alt="Tela de splash"/> | <img src="docs/screenshots/listagem.png" width="240" alt="Tela de listagem"/> | <img src="docs/screenshots/detalhe.png" width="240" alt="Tela de detalhe"/> |

---

## Funcionalidades

- **Splash screen** com a identidade do app e transição automática (2 s) para a listagem.
- **Listagem de pokémons** em cards com imagem oficial, nome, número (`#id`) e tipo.
- **Paginação infinita**: ao chegar ao fim da lista, a próxima página (20 itens) é carregada.
- **Busca por nome** (case-insensitive) pelo campo "Pesquise por nome".
- **Tela de detalhe** com artwork oficial, nome, número, tipo, **altura**, **peso**, **experiência base** e barras de progresso para cada **stat** (hp, attack, defense, etc.).
- Carregamento de imagens com cache via **Glide**.
- Tratamento de erros de rede sem derrubar o app.
- Testes unitários e instrumentados.

---

## Tecnologias

| Categoria | Tecnologia |
|---|---|
| Linguagem | Kotlin |
| UI | Views XML + ViewBinding, Material Components, ConstraintLayout, RecyclerView |
| Arquitetura | MVVM + Repository (camadas `data`, `domain`, `presentation`) |
| Estado reativo | ViewModel + LiveData (`lifecycle` 2.8.7) |
| Assíncrono | Kotlin Coroutines (`async`/`awaitAll` para buscar detalhes em paralelo) |
| Rede | Retrofit 3.0.0 + Gson Converter |
| Imagens | Glide 5.0.9 (Coil 3 também está nas dependências) |
| Testes unitários | JUnit 4, Truth, Mockito, kotlinx-coroutines-test, `androidx.arch.core:core-testing` |
| Testes de UI | Espresso (core, intents e contrib) |
| Build | Gradle 9.6 (Kotlin DSL), Android Gradle Plugin 9.4.1, Version Catalog |

**Configuração do SDK:** `minSdk 24` · `targetSdk 37` · `compileSdk 37` · Java 11.

---

## Arquitetura

O projeto segue **MVVM** com o padrão **Repository**, separado em três camadas:

```
┌──────────────────────────────────────────────────────────┐
│ presentation                                             │
│  Activities (Splash / Listagem / Detalhe)                │
│  PokemonAdapter  ·  PokemonViewModel (LiveData<States>)  │
└───────────────────────────┬──────────────────────────────┘
                            │ depende de
┌───────────────────────────▼──────────────────────────────┐
│ domain                                                   │
│  IPokemonRepository  ·  Pokemon / PokemonDTO             │
│  PaginaPokemon  ·  PokemonDetail (+ modelos aninhados)   │
└───────────────────────────▲──────────────────────────────┘
                            │ implementa
┌───────────────────────────┴──────────────────────────────┐
│ data                                                     │
│  PokemonRepositoryImpl  ·  ApiPokemon  ·  RetrofitHelper │
└──────────────────────────────────────────────────────────┘
```

**Estados da UI** (`States`), expostos pelo `PokemonViewModel` via `LiveData`:

- `Loader`: exibe a `ProgressBar`.
- `OnSeucesso(pokemons)`: entrega a lista (já filtrada pela busca) ao adapter.
- `OnError(mensagem)`: oculta o loader e registra o erro.

---

## Estrutura do projeto

```
PokeFlow/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/br/com/devmobileaj/pokeflow/
│       │   │   ├── data/
│       │   │   │   ├── PokemonRepositoryImpl.kt
│       │   │   │   └── remote/
│       │   │   │       ├── ApiPokemon.kt          # endpoints Retrofit
│       │   │   │       └── RetrofitHelper.kt      # instância do Retrofit
│       │   │   ├── domain/
│       │   │   │   ├── repository/IPokemonRepository.kt
│       │   │   │   ├── paginapokemon/PaginaPokemon.kt
│       │   │   │   ├── pokemon/                   # Pokemon e PokemonDTO
│       │   │   │   └── pokemondetail/             # modelos do JSON de detalhe
│       │   │   ├── presentation/
│       │   │   │   ├── PokemonAdapter.kt
│       │   │   │   ├── ui/
│       │   │   │   │   ├── SplashScreenActivity.kt
│       │   │   │   │   ├── ListagemPokemonActivity.kt
│       │   │   │   │   └── DetalhePokemonActivity.kt
│       │   │   │   └── viewmodel/PokemonViewModel.kt
│       │   │   └── util/LiveDataTestUtil.kt
│       │   └── res/                               # layouts, drawables, cores, temas
│       ├── test/                                  # testes unitários
│       └── androidTest/                           # testes instrumentados (Espresso)
├── gradle/libs.versions.toml                      # Version Catalog
├── build.gradle.kts
└── settings.gradle.kts
```

---

## Fluxo da aplicação

1. **`SplashScreenActivity`** é exibida por 2 segundos e abre a listagem.
2. **`ListagemPokemonActivity`** instancia o `PokemonViewModel`, que carrega a primeira página (`offset = 0`, `limit = 20`).
3. O **`PokemonRepositoryImpl`** busca a página na PokéAPI, extrai o `id` de cada `url` e consulta o detalhe de cada pokémon **em paralelo** com coroutines.
4. O `ViewModel` publica o resultado em `LiveData` e o `PokemonAdapter` renderiza os cards.
5. Ao rolar até o fim da lista, `getNextPage()` carrega a próxima página.
6. Ao digitar no campo de busca e confirmar no teclado, a lista é filtrada pelo nome.
7. Ao tocar em um card, o `PokemonDetail` é enviado via `Intent` (Serializable) para a **`DetalhePokemonActivity`**.

---

## API utilizada

Base URL: `https://pokeapi.co/api/v2/`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `pokemon?offset={n}&limit={n}` | Lista paginada de pokémons |
| `GET` | `pokemon/{id}` | Detalhes (tipos, stats, altura, peso, experiência base) |

As imagens vêm do repositório de sprites da PokéAPI:

```
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{id}.png
```

---

## Como executar

### Pré-requisitos

- [Android Studio](https://developer.android.com/studio) em versão recente (compatível com AGP 9.4.1)
- JDK 11 ou superior (o Android Studio já traz um JDK embutido)
- Dispositivo físico ou emulador com **Android 7.0 (API 24)** ou superior
- Conexão com a internet (o app consome a PokéAPI)

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/antoniojose2023/PokeFlow.git
cd PokeFlow

# 2. Abra no Android Studio (File > Open) e aguarde o sync do Gradle
#    ou compile pela linha de comando:
./gradlew assembleDebug

# 3. Instale em um dispositivo/emulador conectado
./gradlew installDebug
```

No Windows, use `gradlew.bat` no lugar de `./gradlew`.

O APK de debug é gerado em `app/build/outputs/apk/debug/`.

---

## Testes

**Testes unitários** (`app/src/test`): cobrem o `PokemonRepositoryImpl` e o `PokemonViewModel`, usando Mockito, Truth e `kotlinx-coroutines-test`.

```bash
./gradlew test
```

**Testes instrumentados** (`app/src/androidTest`): usam Espresso para validar as telas de Splash (abertura da listagem), Listagem e Detalhe. Exigem um emulador ou dispositivo conectado.

```bash
./gradlew connectedAndroidTest
```

---

## Melhorias futuras

- Injeção de dependências (Hilt ou Koin) no lugar da criação manual em `ListagemPokemonActivity`.
- Troca de `notifyDataSetChanged()` por `ListAdapter` + `DiffUtil`.
- Cache local com Room para uso offline.
- Migração da UI para Jetpack Compose.

---

## Autor

**Antônio José**
GitHub: [@antoniojose2023](https://github.com/antoniojose2023)

---

Dados e imagens dos pokémons fornecidos pela [PokéAPI](https://pokeapi.co/). Pokémon e seus personagens são marcas registradas da Nintendo, Game Freak e The Pokémon Company. Este é um projeto sem fins lucrativos, feito para estudo.
