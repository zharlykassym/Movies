# API Kinopoisk Project
![kinopoisk_movies-min](https://github.com/zharlykassym/Movies/assets/80376028/bc06c5a0-4b9a-4b47-a3de-63c09e7b5a0c)

Kinopoisk Movie это функциональное user-friendly мобильное приложение, взаимодействующее с интернетом и представляющее собой
каталог фильмов с актуальной информацией о каждом фильме.
Приложение представляет наличие следующих экранов:
- Главный каталог с сеткой подгружаемых фильмов: постер, отображение рейтинга и наименование
- Страница фильма с последующей информацией: наименование, рейтинг, год выпуска, описание, кнопка сохранения фильма в избранное, трейлеры и отзывы.

В ходе разработки реализованы:
- Бесперебойная работа подгрузки данных с API Kinopoisk
В работе задействованы следующие зависимости:
- Retrofit для обработки JSON объектов.
- Glide: для работы с постерами фильмов
- RxJava: для работы с данными в фоновом потоке
- Room DB: для хранения определенных данных на устройстве
Реализованы следующие функции:
- Реализована возможность добавления фильма в папку “избранные” на память устройства в системную базу данных (Room)
для постоянного доступа к любимым фильмам в режиме оффлайн.
- Соблюдена реализация MVVM архитектуры. Соблюдены принципы SOLID и принцип чистого кода.
- Использованы паттерны проектирования: Singleton и FactoryMethod
