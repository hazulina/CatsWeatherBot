# CatsWeatherBot
## *Telegram webhook bot that provides you weather forecasts with cute cats photos :)*  

![interface_photo](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/1.png)  

---
## About  
---
Bot consists of two parts (repos):  

- ***CatsWeatherBot*** is the first one that:
    - receives and processes user requests from Telegram API,
    - communicates with backend,
    - sends the results back to the Telegram user.

- ***BotBackendRest*** is the second part that uses:
    - to get weather data from OpenWeatherMap API
    - to get cat pictures from Cloud Object Storage S3
    - to store and maintain the database PostgreSQL.

---
## How to start
---  

***To build bot on localhost you should:***
1. Clone CatsWeatherBot and BotBackendRest repositories from GitHub.
2. Obtain your own Telegram bot token to communicate with Telegram API. Step by step [guide](https://core.telegram.org/bots/features#creating-a-new-bot).
3. Obtain your own OpenWeatherMap token. [Sign up](https://home.openweathermap.org/users/sign_up) and your API key will be on your account page.
4. Create Object Cloud Storage S3. Yandex Cloud Object Storage is used in this project. How to create it read [here](https://cloud.yandex.com/en/docs/storage/quickstart).
5. Prepare bucket structure adhering to sample:

   ![sample](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/folders.png)
   
7. Upload any pictures you wish to Object Cloud Storage. Step by step [guide](https://cloud.yandex.com/en/docs/storage/quickstart#upload-files).
8. Prepare any data base you want. PostgreSQL is used in this project. PostgreSQL tutorial [here](https://www.postgresqltutorial.com/).  

![postgre](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/postgre.png)    

9. Create application.properties files and put it to CatsWeatherBot and BotBackendrest /resources folders.
    - [application.properties](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/application.properties%20for%20CatsWeatherBot) for CatsWeatherBot
    - [application.properties](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/application.properties%20for%20BotBackendRest) for BotBackendRest
10. Finaly you need [ngrok](https://ngrok.com/docs/getting-started/) to set webhook for Telegram API.
11. Also you may need [Postman](https://www.postman.com/downloads/) for debugging or configurate your app.  

---
### OK, I've survived and ready to go on!
---
## How to launch
---
1. Open ngrok client and [start](https://ngrok.com/docs/getting-started/#step-4-start-ngrok) the tunnel on 8443 port ```ngrok http 8443```.
Webhook url that you need will be there:

![webhook](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/ngrok.png)

2. Open Postman client to [set webhook](https://core.telegram.org/bots/api#setwebhook) to your telegram bot. Don't forget to add it to CatsWeatherBot's application.properties too.

![setWebHook](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/postman.png)

3. Run CatsWeatherBot and BotBackendRest applications in your development environment.

<p align="center">
  <img src="https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/ending.jpg" />
</p>
