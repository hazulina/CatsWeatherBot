# CatsWeatherBot
## *Telegram bot that provides you weather forecasts with cute cats photos :)*  

![interface_photo](https://github.com/hazulina/CatsWeatherBot/blob/master/readme_assets/1.png)  

---
## About  
Bot consists of two parts (repos):  

- ***CatsWeatherBot*** is the first one that:
    - receives and processes user requests from Telegram API,
    - communicates with backend,
    - sends the results back to the Telegram user.

- ***BotBackendRest*** is the second part that uses:
    - to get weather data from OpenWeatherMap API
    - to get cat pictures from Cloud Object Storage S3
    - to store and maintain the database PostgreSQL.






