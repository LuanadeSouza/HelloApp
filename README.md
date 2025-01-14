# HelloApp

HelloApp é um aplicativo Android desenvolvido em Kotlin utilizando Jetpack Compose e Hilt. 
O objetivo do aplicativo é gerenciar uma lista de contatos, permitindo adicionar, editar, visualizar e excluir contatos, com persistência local usando Room.

## 🛠️ Funcionalidades

- **Splash Screen**: Tela de carregamento inicial que redireciona o usuário para a tela apropriada com base no estado de login.
- **Login**: Tela de autenticação para os usuários, com suporte a criação de contas.
- **Lista de Contatos**: Exibição de uma lista com contatos salvos no banco de dados local.
- **Formulário de Contato**: Tela para adicionar ou editar informações de contatos.
- **Detalhes do Contato**: Exibição de informações detalhadas sobre um contato.

## 🚀 Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Jetpack Compose**: Para criação de interfaces modernas e declarativas.
- **Hilt**: Injeção de dependência simplificada.
- **Room**: Persistência local para salvar contatos.
- **DataStore**: Para armazenar preferências de login.
- **Coil**: Para carregar imagens de perfis de contatos.
- **Coroutines e Flow**: Gerenciamento de fluxo de dados assíncronos.

## 📂 Estrutura do Projeto

```
app/
├── data/                      # Modelos de dados
├── database/                  # Configuração do banco de dados Room
├── di/                        # Módulos de injeção de dependências
├── extensions/                # Funções de extensão úteis
├── navigation/                # Navegação do app
├── preferences/               # Configuração do DataStore
├── sampleData/                # Dados de exemplo
├── ui/                        # Componentes de UI
│   ├── components/            # Componentes reutilizáveis
│   ├── details/               # Tela de detalhes do contato
│   ├── form/                  # Tela de formulário de contato
│   ├── home/                  # Tela de lista de contatos
│   ├── login/                 # Tela de login
│   ├── splashscreen/          # Tela de splash
└── util/                      # Constantes e utilitários gerais
```

## 🔧 Configuração do Ambiente

1. **Pré-requisitos**:
   - Android Studio Flamingo ou superior.
   - JDK 11 ou superior.

2. **Clone o repositório**:
   ```bash
   git clone [(https://github.com/LuanadeSouza/HelloApp.git)]
   ```

3. **Abra no Android Studio**:
   - File > Open > Selecione o diretório do projeto.

4. **Sincronize as dependências**:
   - Certifique-se de que o `Gradle` esteja configurado corretamente.

5. **Execute o aplicativo**:
   - Configure um dispositivo virtual ou conecte um dispositivo físico e clique em **Run**.

## 🧪 Testes

Testes automatizados estão configurados usando o AndroidX Test.
- Testes unitários podem ser encontrados em `src/test/java`.
- Testes instrumentados estão localizados em `src/androidTest/java`.

Execute os testes com:
```bash
./gradlew testDebug
```

## 📝 Licença

Este projeto é licenciado sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

### 📬 Contato

Se tiver dúvidas ou sugestões, entre em contato:
- **LinkedIn**: https://www.linkedin.com/in/seu-perfil/](https://www.linkedin.com/in/luanadesouza-desenvolvedora-android/
