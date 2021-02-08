# DatabaseAPI

<h1 align="center">DatabaseAPI</h1>

<p align="center">Crie conexões MYSQL e SQLite facilmente!</p>

## Como Utilizar

<h3> • Passo 1 </h3>
Antes de tudo, você deve gerar a sua config.yml e colocar as seguintes informações:

```yaml
Storage:
  TYPE: true # true para MySQL e false para SQLite
  # Apenas preencha as informações abaixo caso opte pelo MySQL! #
  Host: 'localhost'
  Port: 3306
  Username: 'root'
  Password: 'vertrigo'
  Database: 'storage'
```

<h3> • Passo 2 </h3>
Agora, o próximo passo é baixar o código do github e aplicar no seu código. Lembre-se de averiguar se está tudo OK!

<img src="https://i.imgur.com/uHKR5CD.png">

<h3> • Passo 3 </h3>
Já está quase chegando ao fim! Vá na sua classe principal (a que estende JavaPlugin) e instancie a classe ConnectionManager e também a sua Main.
Após a instância, crie o metodo Getter do ConnectionManager e da sua Main.
Em seu onEnable(), adicione: 

```yaml
instance = this;
saveDefaultConfig();
connectionManager = new ConnectionManager();
```
Este método irá fazer a conexão com o banco de dados e também salvar a sua config.yml.

<img src="https://imgur.com/ChXoiU3.png">

<h3> • Passo 4 </h3>
O último passo é fechar a conexão com o desligamento do plugin.
Em seu onDisable(), adicione:

```yaml
ConnectionManager.close();
```

<img src="https://imgur.com/3c7Bcjo.png">

## Extras

<h3> • Criação de Tabelas </h3>

Para criar uma tabela, basta você ir na classe ConnectionManager e ir no método createTables().
Lá, basta você digitar createTable("tableName", "columns").

<img src="https://imgur.com/dvR9sDN.png">

Após isso, todas as suas tabelas serão criadas.
