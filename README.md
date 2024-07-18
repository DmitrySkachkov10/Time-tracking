# Запуск Микросервисов

Этот проект включает два микросервиса: `user-service` и `time-tracking-service`.

## Требования

- Свободные порты `8080` и `8081`
- Maven для сборки проектов
- Docker и Docker Compose для запуска баз данных и сервисов

## Инструкции по запуску

- Сборка каждого микросервиса:

   В корневой директории каждого микросервиса выполните команду:
   ```sh
   mvn package -Dtestskip
  ```

    **[API user-service](https://github.com/DmitrySkachkov10/Time-tracking/blob/main/user-service/README.md)**
  
    **[API time-tracking-service](https://github.com/DmitrySkachkov10/Time-tracking/blob/main/time-tracker-service/README.md)**

- Docker compose
   Выполните команду
    ```sh
   docker-compose up -d
