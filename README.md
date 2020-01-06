# Projeto Quiz
Este projeto tem como objetivo cadastrar e responder perguntas de quiz. Foi utilizado as linguagens React e Java (Spring Boot).

## Configurando o Backend (spring-quiz)

+ **Criar PostgreSQL database**

É necessário criar um banco no PostgreSQL com o nome "quiz"

+ **Configurar dados de conexão com o banco**

	```yml
	# spring-quiz/src/main/resources/application.yml
	spring:
        datasource:
            url: jdbc:postgresql://localhost:5432/quiz
            username: postgres
            password: admin
	```
+ **Configurar OAuth2 Provider ClientId's e ClientSecrets**

	```yml
    security:
      oauth2:
        client:
          registration:
            google:
              clientId: <GOOGLE_CLIENT_ID>
              clientSecret: <GOOGLE_CLIENT_SECRET>
              redirectUri: "{baseUrl}/oauth2/callback/{registrationId}"
              scope:
                - email
                - profile
            facebook:
              clientId: <FACEBOOK_CLIENT_ID>
              clientSecret: <FACEBOOK_CLIENT_SECRET>
              redirectUri: "{baseUrl}/oauth2/callback/{registrationId}"
              scope:
                - email
                - public_profile
            github:
              clientId: <GITHUB_CLIENT_ID>
              clientSecret: <GITHUB_CLIENT_SECRET>
              redirectUri: "{baseUrl}/oauth2/callback/{registrationId}"
              scope:
                - user:email
                - read:user
          provider:
            facebook:
              authorizationUri: https://www.facebook.com/v3.0/dialog/oauth
              tokenUri: https://graph.facebook.com/v3.0/oauth/access_token
              userInfoUri: https://graph.facebook.com/v3.0/me?fields=id,first_name,middle_name,last_name,name,email,verified,is_verified,picture.width(250).height(250)
	```

	Esta configuração é necessária altera-la para um sistema em produção. Para uma demo e um teste local as configurações já foram feitas.

+ **Rodar spring-quiz**

	```bash
    cd spring-quiz
	mvn spring-boot:run
	```
## Configurando o Frontend (react-quiz)

+ **Rodar react-quiz**

	```bash
    cd react-quiz
	npm install && npm start
	```
