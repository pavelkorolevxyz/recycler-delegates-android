# Список

Модуль, инкапсулирующий базовую логику работы со списками в приложении.

## Логика

Библиотека основана на подходе **AdapterDelegates**, суть которого в выносе из адаптера RecyclerView делегатов, ответственных за свой конкретный тип ячейки.

## Основные компоненты

### Модели

- `ListItem` - базовый интерфейс для всех моделей ячеек в списках.
- `Diffable` - базовый интерфейс для описания логики подсчёта diff модели ячейки.
- `DiffableListItem` - базовый интерфейс для всех моделей ячеек в списках с возможностью подсчёта diff (Объединяет первые два).

### Id

- `ListId` - тип идентификаторов элементов списка. Имеет ряд фабричных методов для упрощения создания на основе различных входных данных.

### Адаптер

- `ListItemViewHolder` - базовый `RecyclerView.ViewHolder` для адаптер-делегатов работающих с `ListItem`. (`ListItemContainerViewHolder` - реализация `ListItemViewHolder` для безопасного использования kotlin synthetic import)
- `ListItemDiffCallback` - реализация `DiffUtil.ItemCallback` для моделей типа `ListItem`.
- `ListItemAdapterDelegate` - базовый класс для делегатов типов, реализующих `ListItem`, кэширует последнее привязанное значение внутри `ListItemViewHolder` с которым работает.
- `ListItemAdapter` - базовая реализация `RecyclerView.Adapter`, работающая с `ListItemDiffCallback` в качестве логики diff.

### Декорации

- `VerticalDecoratedItem` - интерфейс, реализация которого позволяет в `ListItem` добавить описание декораций для верхнего и нижнего края ячейки. 
- `DelegatesDividerItemDecoration` - универсальный `RecyclerView.ItemDecoration`, который с помощью закешированного значения `ListItem` во `ViewHolder` ячейки делегирует логику отрисовки декораций описанных в интерфейсе `VerticalDecoratedItem`.
- `BaseDividerDecorationDelegate` - базовый класс для декораций, рисующих разделители с краёв ячейки
- `LineDividerDecorationDelegate` - реализация `BaseDividerDecorationDelegate`, рисующая линии сверху и снизу ячейки.

## Инструкция к использованию

Если подразумевается что ячейки в списке будут когда либо обновляться, то рекомендуется работать с `DiffableListItem`, а не чистым `ListItem`.

1. Нужно создать класс, реализующий `DiffableListItem`. Удобно для этого использовать в Kotlin `data class`, т.к. для них автоматически генерируются методы `equals`/`hashCode`, что избавляет их ручного переопределения, это необходимо для работы diff по умолчанию.
1. Переопределить `id` в `Diffable`. Для задания значения удобно воспользоваться фабричными методами `ListId`. Главное правило - чтобы в списке не было повторяющихся идентификаторов у ячеек одного типа, иначе `DiffUtils` будет работать некорректно. Для идентификаторов рекомендуется использовать созданные в ресурсах `R.id`. Для множества элементов одного типа рекомендуется использовать составные идентификаторы из `R.id` и какого-либо `id` модели в коде, это может быть идентификатор записи из базы или из серверной модели.
1. Создать класс, реализующий `ListItemAdapterDelegate`, работающий с созданным `ListItem` и использующий наследник `ListItemViewHolder`(`ListItemContainerViewHolder` для kotlin synthetic import).
1. В классе, описывающем экран, например `Fragment` или `Activity` создать `ListItemAdapter`, передать ему в конструктор делегаты всех необходимых на этом экране `ListItem`.
1. Если необходимы какие-либо декорации в списке, реализовать у соответствующих `ListItem` интерфейс `VerticalDecoratedItem`.
1. Провести настройку `RecyclerView` с помощью extension функции `RecyclerView.setup(RecyclerView.Adapter, LayoutManager, List<ItemDecoration>)`.

## Именование и расположение

### Модели

Рекомендуется держать модель, реализующую `ListItem`, внутри своего делегата под названием `Model`.

В случае внешней от делегата модели:
- Префикс `НазваниеФичи` или пустой в случае глобальной модели.
- Суффикс `ListItem`.

Например: `EmptyListItemDelegate.Model` или `FeatureSomeListItem`.

### Ресурсы

Рекомендуется идентификаторы создавать в ресурсах конкретной фичи.

Для идентификаторов в `res/values/` создать файл ids.xml
- Префикс `название_фичи` или пустой в случае глобальной модели.
- Суффикс `list_item`

Например: `ids_list_item.xml` или `feature_ids_list_item.xml`.

Сами идентификаторы именовать подобным образом, например `<item name="empty_list_item" type="id" />` или `<item name="feature_some_list_item" type="id" />`

Для вёрстки каждой ячейки в `res/layout` аналогично создать файл с названием ячейки, например `empty_list_item.xml` или `feature_some_list_item.xml`

### Адаптер

По возможности использовать базовый `ListItemAdapter` без наследников.

Делегаты именуются аналогично: 
- Префикс `НазваниеФичи` или пустой в случае глобальной модели.
- Суффикс `ListItem`.

Например: `EmptyListItemDelegate` или `FeatureSomeListItem.Delegate`

# Транзитивные зависимости

- RecyclerView
- AdapterDelegates

# Дополнительная информация

Что идейно повлияло на этот модуль:

- [AdapterDeleages](https://github.com/sockeqwe/AdapterDelegates)
- [Revolut RecyclerKit](https://github.com/revolut-mobile/RecyclerKit)
- [Airbnb Epoxy](https://github.com/airbnb/epoxy)
- [Доклад RecyclerView-based architecture, Revolut](https://www.youtube.com/watch?v=Moc6i7zIoH0)
- [Доклад Model-View-Revolut, Revolut](https://youtu.be/pv8ewHRVOKY?t=7490)
- [Интервью: RecyclerView-based архитектура](https://www.youtube.com/watch?v=PWpYBqr1u0w)
