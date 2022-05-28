Программа для поиска, сортировки и склеивания содержимового текстовых файлов.
Для запуска требуется исполнить App.java, результат появится в корне в папке "result".

Решение:
1. Методом addTree строим дерево папок и через цикл for проверяем на наличе файлов.
2. Файлы заносим в HasMap, где ключ - название, значение - путь.
3. Сортируем HashMap по ключу.
4. По отсортированым ключам обращаемся к значениям мапы, получаем путь и по нему читаем файл и записываем в result/result.txt.