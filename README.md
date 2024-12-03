## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:

1. Разобрался со структурой проекта.
2. Удалил социальные сети vk, yandex.
3. Вынести чувствительную информацию в отдельный проперти файл:
  - application-prod.properties
  - application-dev.properties
5. Написал тесты для ProfileRestController.
6. Сделал рефакторинг FileUtil
7. Добавил REST для Тэгов.
 - get  /api/tag/{taskID}
 - post 1 тэг
 - patch замена тэгов [ "old_tag1", "new_tag" ]
 - put добавление нескольких тэгов [ "tag1", "tag2" ]
 - Delete
8. Добавил подсчет времени.
9. Написал Dockerfile для приложения jar.
 -
11. Добавил локализацию http://localhost:8080/?locale=en
12. Переделать механизм распознавания «свой-чужой» между фронтом и беком с JSESSIONID на JWT
 - регистрация /auth/sign-up
 - вход /auth/sign-in
 - в Swagger